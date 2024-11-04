package jmna.loacalc.processor;

import jmna.loacalc.feign.client.armories.ArmoryEngravings;
import jmna.loacalc.feign.client.armories.engravings.ArkPassiveEffect;
import jmna.loacalc.feign.client.armories.engravings.Effect;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EngravingProcessor {

    // 각인의 자세한 효과는 레벨과 각인 이름을 Enum을 통해 받아온다.
    public void parseEngravingEffect(ArmoryEngravings armoryEngravings) throws Exception {
        if (armoryEngravings.getEngravings() != null) {
            // 3티어 각인, 악세서리
            List<Effect> effects = armoryEngravings.getEffects();
            for (Effect effect : effects) {
                String icon = effect.getIcon();
                String[] nameAndLvl = effect.getName().split("Lv\\.");
                String name = nameAndLvl[0].trim();
                String lvl = nameAndLvl[1].trim();
            }
        } else if (armoryEngravings.getArkPassiveEffects() != null) {
            // 4티어 각인, 악세서리
            List<ArkPassiveEffect> arkPassiveEffects = armoryEngravings.getArkPassiveEffects();
            for (ArkPassiveEffect arkPassiveEffect : arkPassiveEffects) {
                String grade = arkPassiveEffect.getGrade();
                Integer level = arkPassiveEffect.getLevel();
                String name = arkPassiveEffect.getName();
                Integer abilityStoneLevel = arkPassiveEffect.getAbilityStoneLevel();
            }
        } else {
            throw new Exception("잘못된 데이터입니다.");
        }
    }


}
