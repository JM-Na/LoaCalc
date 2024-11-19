package jmna.loacalc.calculator.engraving;

import jmna.loacalc.processor.armory.engraving.CharacterEngraving;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EngravingEffectCalculator {

    public EngravingEffect calculateEngravingEffect(List<CharacterEngraving> characterEngravings) {
        if (characterEngravings.get(0).getIsTier4()) {
            return parseEngravingToEffectT4(characterEngravings);
        } else {
            return parseEngravingToEffectT3(characterEngravings);
        }
    }

    public EngravingEffect parseEngravingToEffectT4(List<CharacterEngraving> characterEngravings) {
        List<EngravingEffect> engravingEffects = new ArrayList<>();
        for (CharacterEngraving characterEngraving : characterEngravings) {
            double incrementByStone = EngravingAbilityStone.getIncrementValue(characterEngraving);
            engravingEffects.add(EngravingIncrementT4.applyEngravingEffect(characterEngraving, incrementByStone));
        }
        return engravingEffects.stream().reduce(new EngravingEffect(), EngravingEffect::merge);
    }

    public EngravingEffect parseEngravingToEffectT3(List<CharacterEngraving> characterEngravings) {
        List<EngravingEffect> engravingEffects = new ArrayList<>();
        for (CharacterEngraving characterEngraving : characterEngravings) {
            engravingEffects.add(EngravingIncrementT3.applyEngravingEffect(characterEngraving));
        }
        return engravingEffects.stream().reduce(new EngravingEffect(), EngravingEffect::merge);
    }
}
