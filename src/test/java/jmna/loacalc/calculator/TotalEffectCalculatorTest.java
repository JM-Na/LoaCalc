package jmna.loacalc.calculator;

import jmna.loacalc.calculator.arkpassive.ArkpassiveEffectCalculator;
import jmna.loacalc.calculator.arkpassive.ArkpassiveEvolutionEffect;
import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.engraving.EngravingEffect;
import jmna.loacalc.calculator.engraving.EngravingEffectCalculator;
import jmna.loacalc.calculator.subequipments.AccessoryEffect;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import jmna.loacalc.feign.client.armories.*;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.ProfileProcessor;
import jmna.loacalc.processor.armory.arkpassive.ArkpassiveProcessor;
import jmna.loacalc.processor.armory.arkpassive.CharacterArkpassive;
import jmna.loacalc.processor.armory.engraving.CharacterEngraving;
import jmna.loacalc.processor.armory.engraving.EngravingProcessor;
import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import jmna.loacalc.processor.armory.equipment.EquipmentProcessor;
import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
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

        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("레게머리뿌뿌뿡", null);

        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        TranscendenceEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        ArmoryEngravings armoryEngravings = armoryTotal.getArmoryEngravings();
        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);

        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);


        TotalEffect totalEffect = totalEffectCalculator.calculateTotalEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        System.out.println("totalEffect = " + totalEffect);

        ArmoryArkPassive armoryArkPassive = armoryTotal.getArmoryArkPassive();
        List<CharacterArkpassive> characterArkpassives = arkpassiveProcessor.processArkpassiveData(armoryArkPassive);
        ArkpassiveEvolutionEffect evolutionEffect = arkpassiveEffectCalculator.calculateEvolutionEffect(characterArkpassives);

        ArmoryProfile armoryProfile = armoryTotal.getArmoryProfile();
        CharacterProfile characterProfile = profileProcessor.processProfiles(armoryProfile);


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