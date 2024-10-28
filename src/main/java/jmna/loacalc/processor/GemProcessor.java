package jmna.loacalc.processor;

import jmna.loacalc.feign.client.armories.gems.Gem;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GemProcessor {

    public double getGemBasicAttackPowerIncrease(List<Gem> gems) {
        double sum = 0;

        for (Gem gem : gems) {
            JSONObject jsonObject = new JSONObject(gem.getTooltip());
            String effect = (String) jsonObject.getJSONObject("Element_006").getJSONObject("value").get("Element_001");
            if (effect.contains("추가 효과")) {
                String s = effect.split("</FONT><BR>")[1];
                // 부동소수점 오류를 피하기 위해 정수로 변환한 후 계산한다.
                int result = Integer.parseInt(s.replace("기본 공격력 0.", "").replace("% 증가", ""));
                sum += result;
            }
        }
        return sum / 100;
    }
}
