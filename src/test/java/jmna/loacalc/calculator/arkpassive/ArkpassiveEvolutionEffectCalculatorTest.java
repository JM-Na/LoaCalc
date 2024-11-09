package jmna.loacalc.calculator.arkpassive;

import jmna.loacalc.feign.client.armories.ArmoryArkPassive;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.processor.arkpassive.ArkpassiveProcessor;
import jmna.loacalc.processor.arkpassive.CharacterArkpassive;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ArkpassiveEvolutionEffectCalculatorTest {
    @Autowired
    private ArmoryClient armoryClient;
    @Autowired
    private ArkpassiveProcessor arkpassiveProcessor;
    @Autowired
    private ArkpassiveEffectCalculator arkpassiveEffectCalculator;

    @Test
    void ArkpassiveProcess() {
        ArmoryArkPassive armoryArkPassive = armoryClient.getArmoryArkPassive("레게머리뿌뿌뿡");

        List<CharacterArkpassive> characterArkpassiveList = arkpassiveProcessor.processArkpassiveData(armoryArkPassive);

        ArkpassiveEvolutionEffect arkpassiveEvolutionEffect = new ArkpassiveEvolutionEffect();

        for (CharacterArkpassive characterArkpassive : characterArkpassiveList) {
            if (characterArkpassive.getType().equals("진화")) {
                int tier = characterArkpassive.getTier();
                String name = characterArkpassive.getName();
                int lvl = characterArkpassive.getLvl();

                Map<String, Double> effect = ArkpassiveEvolution.findEffect(tier, name, lvl);
                for (String s : effect.keySet()) {
                    Double increment = effect.get(s);
                    switch (s) {
                        case "치명" -> arkpassiveEvolutionEffect.setCrit(increment.intValue());
                        case "특화" -> arkpassiveEvolutionEffect.setSpecialization(increment.intValue());
                        case "제압" -> arkpassiveEvolutionEffect.setDomination(increment.intValue());
                        case "신속" -> arkpassiveEvolutionEffect.setSwiftness(increment.intValue());
                        case "인내" -> arkpassiveEvolutionEffect.setEndurance(increment.intValue());
                        case "숙련" -> arkpassiveEvolutionEffect.setExpertise(increment.intValue());
                        case "Mana Skill Cooldown" -> arkpassiveEvolutionEffect.addManaSkillCooldown(increment);
                        case "Mana Consumption" -> arkpassiveEvolutionEffect.addManaConsumption(increment);
                        case "Evolution Damage" -> arkpassiveEvolutionEffect.addEvolutionDmg(increment);
                        case "Evolution Damage for Mana Skill" -> arkpassiveEvolutionEffect.addManaEvolutionDmg(increment);
                        case "Crit Rate" -> arkpassiveEvolutionEffect.addCritRate(increment);
                        case "Skill Cooldown" -> arkpassiveEvolutionEffect.addSkillCooldown(increment);
                        case "Attack and Move Speed" -> {
                            arkpassiveEvolutionEffect.addAtkSpeed(increment);
                            arkpassiveEvolutionEffect.addMoveSpeed(increment);
                        }
                        case "Directional Skill Crit Damage" -> arkpassiveEvolutionEffect.addDirectionalSkillCritDmg(increment);
                        case "Attack Speed" -> arkpassiveEvolutionEffect.addAtkSpeed(increment);
                        case "Identity Gain" -> arkpassiveEvolutionEffect.addIdentityGain(increment);
                        case "Brand Power" -> arkpassiveEvolutionEffect.addBrandPower(increment);
                        case "Blunt Spike" -> arkpassiveEvolutionEffect.setBluntSpike(lvl);
                        case "Sonic BreakThrough" -> arkpassiveEvolutionEffect.setSonicBreakThrough(lvl);
                        case "Mana Forge" -> arkpassiveEvolutionEffect.setManaForge(lvl);
                    }
                }
            }
        }
        System.out.println("arkpassiveEffect = " + arkpassiveEvolutionEffect);
    }

}