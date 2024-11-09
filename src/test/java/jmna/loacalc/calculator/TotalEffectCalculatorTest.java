package jmna.loacalc.calculator;

import jmna.loacalc.calculator.arkpassive.ArkpassiveEffectCalculator;
import jmna.loacalc.calculator.arkpassive.ArkpassiveEvolutionEffect;
import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.feign.client.armories.*;
import jmna.loacalc.processor.CharacterProfile;
import jmna.loacalc.processor.ProfileProcessor;
import jmna.loacalc.processor.arkpassive.ArkpassiveProcessor;
import jmna.loacalc.processor.arkpassive.CharacterArkpassive;
import jmna.loacalc.processor.engraving.CharacterEngraving;
import jmna.loacalc.processor.engraving.EngravingProcessor;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.EquipmentProcessor;
import jmna.loacalc.processor.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TotalEffectCalculatorTest {

    @Autowired
    private EngravingEffectCalculator engravingEffectCalculator;
    @Autowired
    private ArmoryEffectCalculator armoryEffectCalculator;
    @Autowired
    private ArmoryClient armoryClient;
    @Autowired
    private EquipmentProcessor equipmentProcessor;
    @Autowired
    private EngravingProcessor engravingProcessor;
    @Autowired
    private TotalEffectCalculator totalEffectCalculator;
    @Autowired
    private ProfileProcessor profileProcessor;
    @Autowired
    private StatEffectCalculator statEffectCalculator;
    @Autowired
    private ArkpassiveProcessor arkpassiveProcessor;
    @Autowired
    private ArkpassiveEffectCalculator arkpassiveEffectCalculator;


    @Test
    void totalEffect() {
        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        TranscendenceEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        ArmoryEngravings armoryEngravings = armoryClient.getArmoryEngravings("레게머리뿌뿌뿡");
        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);

        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);


        TotalEffect totalEffect = totalEffectCalculator.calculateTotalEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        System.out.println("totalEffect = " + totalEffect);

        ArmoryArkPassive armoryArkPassive = armoryClient.getArmoryArkPassive("레게머리뿌뿌뿡");
        List<CharacterArkpassive> characterArkpassives = arkpassiveProcessor.processArkpassiveData(armoryArkPassive);
        ArkpassiveEvolutionEffect evolutionEffect = arkpassiveEffectCalculator.calculateEvolutionEffect(characterArkpassives);

        ArmoryProfiles armoryProfiles = armoryClient.getArmoryProfiles("레게머리뿌뿌뿡");
        CharacterProfile characterProfile = profileProcessor.processProfiles(armoryProfiles);


        double critByStat = statEffectCalculator.calculateStatCrit(characterProfile.getCrit());
        double critRate = totalEffect.getCritRate();
        double critRate1 = evolutionEffect.getCritRate();

        double totalCrit = critByStat + critRate + critRate1;
        System.out.println("totalCrit = " + totalCrit);

        double evolutionDmg = evolutionEffect.getEvolutionDmg();
        double manaEvolutionDmg = evolutionEffect.getManaEvolutionDmg();

        double evolutionDmgByBluntSpike = (totalCrit + 15 - 80) * 1.4 + 15;
        System.out.println("evolutionDmgByBluntSpike = " + evolutionDmgByBluntSpike);
    }

}