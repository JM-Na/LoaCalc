package jmna.loacalc.processor;

import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryGems;
import jmna.loacalc.feign.client.armories.gems.Gem;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GemProcessor {

    private final ArmoryClient armoryClient;

    public double getGemBasicWeaponPowerIncrease(String characterName) {
        List<Gem> gems = armoryClient.getArmoryGems(characterName).getGems();

        double sum = 0;

        for (Gem gem : gems) {
            JSONObject jsonObject = new JSONObject(gem.getTooltip());
            String effect = (String) jsonObject.getJSONObject("Element_006").getJSONObject("value").get("Element_001");
            if (effect.contains("추가 효과")) {
                String s = effect.split("</FONT><BR>")[1];
                // 부동소수점 오류를 피하기 위해 정수로 변환한 후 계산한다.
                int result = Integer.parseInt(s.replace("기본 공격력 0.", "").replace("% 증가", ""));
                System.out.println("result = " + result);
                sum += result;
                System.out.println("sum = " + sum);
            }
        }

        System.out.println("sum = " + sum / 100);
        return sum / 100;
    }
}
