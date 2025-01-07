package jmna.loacalc.calculator.arkpassive;

import jmna.loacalc.calculator.arkpassive.enlightenment.ArkpassiveEnlightenment;
import jmna.loacalc.calculator.arkpassive.enlightenment.ArkpassiveEnlightenmentEffect;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.arkpassive.CharacterArkpassive;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

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
        int tier = characterArkpassive.getTier();
        String name = characterArkpassive.getName();
        int lvl = characterArkpassive.getLvl();

        Map<String, Double> effect = ArkpassiveEvolution.findEffect(tier, name, lvl);
        for (String s : effect.keySet()) {
            Double increment = effect.get(s);
            switch (s) {
                case "치명" -> evolutionEffect.setCrit(increment.intValue());
                case "특화" -> evolutionEffect.setSpecialization(increment.intValue());
                case "제압" -> evolutionEffect.setDomination(increment.intValue());
                case "신속" -> evolutionEffect.setSwiftness(increment.intValue());
                case "인내" -> evolutionEffect.setEndurance(increment.intValue());
                case "숙련" -> evolutionEffect.setExpertise(increment.intValue());
                case "Mana Skill Cooldown" -> evolutionEffect.addManaSkillCooldown(increment);
                case "Mana Consumption" -> evolutionEffect.addManaConsumption(increment);
                case "Evolution Damage" -> evolutionEffect.addEvolutionDmg(increment);
                case "Evolution Damage for Mana Skill" -> evolutionEffect.addManaEvolutionDmg(increment);
                case "Crit Rate" -> evolutionEffect.addCritRate(increment);
                case "Skill Cooldown" -> evolutionEffect.addSkillCooldown(increment);
                case "Attack and Move Speed" -> {
                    evolutionEffect.addAtkSpeed(increment);
                    evolutionEffect.addMoveSpeed(increment);
                }
                case "Directional Skill Crit Damage" -> evolutionEffect.addDirectionalSkillCritDmg(increment);
                case "Attack Speed" -> evolutionEffect.addAtkSpeed(increment);
                case "Identity Gain" -> evolutionEffect.addIdentityGain(increment);
                case "Brand Power" -> evolutionEffect.addBrandPower(increment);
                case "Blunt Spike" -> evolutionEffect.setBluntSpike(lvl);
                case "Sonic BreakThrough" -> evolutionEffect.setSonicBreakThrough(lvl);
                case "Mana Forge" -> evolutionEffect.setManaForge(lvl);
            }
        }
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
