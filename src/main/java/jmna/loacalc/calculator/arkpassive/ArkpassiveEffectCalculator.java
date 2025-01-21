package jmna.loacalc.calculator.arkpassive;

import jmna.loacalc.calculator.arkpassive.enlightenment.ArkpassiveEnlightenment;
import jmna.loacalc.calculator.arkpassive.enlightenment.ArkpassiveEnlightenmentEffect;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.arkpassive.CharacterArkpassive;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArkpassiveEffectCalculator {


    public ArkpassiveEffect calculateArkpassiveEffect(List<CharacterArkpassive> characterArkpassiveList, CharacterProfile characterProfile) {
        ArkpassiveEvolutionEffect evolutionEffect = new ArkpassiveEvolutionEffect();
        ArkpassiveEnlightenmentEffect enlightenmentEffect = new ArkpassiveEnlightenmentEffect();
        for (CharacterArkpassive characterArkpassive : characterArkpassiveList) {
            if (characterArkpassive.getType().equals("진화")) {
                calculateEvolutionEffect(evolutionEffect, characterArkpassive);
            } else if (characterArkpassive.getType().equals("깨달음")) {
                calculateEnlightenmentEffect(enlightenmentEffect, characterArkpassive, characterProfile);
            }
        }
        return new ArkpassiveEffect(evolutionEffect, enlightenmentEffect);
    }

    public ArkpassiveEvolutionEffect calculateEvolutionEffect(ArkpassiveEvolutionEffect evolutionEffect, CharacterArkpassive characterArkpassive) {
        String name = characterArkpassive.getName();
        int lvl = characterArkpassive.getLvl();

        ArkpassiveEvolution.applyEffect(name, lvl, evolutionEffect);

        return evolutionEffect;
    }

    private void calculateEnlightenmentEffect(ArkpassiveEnlightenmentEffect enlightenmentEffect,
                                              CharacterArkpassive characterArkpassive,
                                              CharacterProfile characterProfile){
        String className = characterProfile.getClassName();
        String nodeName = characterArkpassive.getName();
        int nodeLvl = characterArkpassive.getLvl();

        ArkpassiveEnlightenment.applyEffect(className, nodeName, nodeLvl, enlightenmentEffect);
    }

    private void calculateLeapEffect() {

    }
}
