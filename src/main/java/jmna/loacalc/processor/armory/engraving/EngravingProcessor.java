package jmna.loacalc.processor.armory.engraving;

import jmna.loacalc.feign.client.armories.ArmoryEngravings;
import jmna.loacalc.feign.client.armories.engravings.ArkPassiveEffect;
import jmna.loacalc.feign.client.armories.engravings.Effect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class EngravingProcessor {

    // 각인의 자세한 효과는 레벨과 각인 이름을 Enum을 통해 받아온다.
    public List<CharacterEngraving> parseEngravingEffect(ArmoryEngravings armoryEngravings) {

        List<CharacterEngraving> characterEngravings = new ArrayList<>();

        if (armoryEngravings.getEngravings() != null) {
            // 3티어 각인, 악세서리
            List<Effect> effects = armoryEngravings.getEffects();
            for (Effect effect : effects) {
                String[] nameAndLvl = effect.getName().split("Lv\\.");
                String name = nameAndLvl[0].trim();
                int lvl = Integer.parseInt(nameAndLvl[1].trim());
                characterEngravings.add(new CharacterEngraving(name, lvl));
            }
        } else if (armoryEngravings.getArkPassiveEffects() != null) {
            // 4티어 각인, 악세서리
            List<ArkPassiveEffect> arkPassiveEffects = armoryEngravings.getArkPassiveEffects();
            for (ArkPassiveEffect arkPassiveEffect : arkPassiveEffects) {
                String name = arkPassiveEffect.getName();
                int lvl = Optional.ofNullable(arkPassiveEffect.getLevel()).orElse(0);
                String grade = arkPassiveEffect.getGrade();
                int abilityStoneLevel = Optional.ofNullable(arkPassiveEffect.getAbilityStoneLevel()).orElse(0);
                characterEngravings.add(new CharacterEngraving(name, lvl, grade, abilityStoneLevel));
            }
        }

        return characterEngravings;
    }


}
