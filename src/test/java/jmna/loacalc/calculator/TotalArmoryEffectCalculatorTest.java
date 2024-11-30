package jmna.loacalc.calculator;

import jmna.loacalc.calculator.arkpassive.ArkpassiveEffectCalculator;
import jmna.loacalc.calculator.arkpassive.ArkpassiveEvolutionEffect;
import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.engraving.EngravingEffect;
import jmna.loacalc.calculator.engraving.EngravingEffectCalculator;
import jmna.loacalc.calculator.subequipments.AccessoryEffect;
import jmna.loacalc.calculator.transcendence.TranscEffect;
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
class TotalArmoryEffectCalculatorTest {

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
    private TotalArmoryEffectCalculator totalArmoryEffectCalculator;
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
        // 여러개의 데이터 요청을 통채로 처리하는 API
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("레게머리뿌뿌뿡", null);

        // 장비 정보를 담고있는 CharacterEquipment
        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);

        // CharacterEquipment의 field 추출
        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        // 초월, 엘릭서, 장비, 악세서리 효과 계산
        TranscEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        // 각인 정보를 담고 있는 CharacterEngraving
        ArmoryEngravings armoryEngravings = armoryTotal.getArmoryEngravings();
        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);

        // 각인 효과 계산
        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);

        // 아크패시브 깨달음 효과 (진화형 피해 등)
        ArmoryArkPassive armoryArkPassive = armoryTotal.getArmoryArkPassive();
        List<CharacterArkpassive> characterArkpassives = arkpassiveProcessor.processArkpassiveData(armoryArkPassive);
        ArkpassiveEvolutionEffect arkpassiveEvolutionEffect = arkpassiveEffectCalculator.calculateEvolutionEffect(characterArkpassives);

        // 초월, 엘릭서, 장비, 악세서리, 각인 효과를 합산
        TotalArmoryEffect totalArmoryEffect = totalArmoryEffectCalculator.calculateTotalArmoryEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        System.out.println("totalEffect = " + totalArmoryEffect);


        // 캐릭터 정보
        ArmoryProfile armoryProfile = armoryTotal.getArmoryProfile();
        CharacterProfile characterProfile = profileProcessor.processProfiles(armoryProfile);


        double critByStat = statEffectCalculator.calculateStatCrit(characterProfile.getCrit());
        double critRate = totalArmoryEffect.getCritRate();
        double critRateByArkpassive = arkpassiveEvolutionEffect.getCritRate();

        // 치명타 확률 총합
        double totalCrit = critByStat + critRate + critRateByArkpassive;
        System.out.println("totalCrit = " + totalCrit);

        // 진화형 피해
        double evolutionDmg = arkpassiveEvolutionEffect.getEvolutionDmg();
        double manaEvolutionDmg = arkpassiveEvolutionEffect.getManaEvolutionDmg();
        if (arkpassiveEvolutionEffect.getBluntSpike() != 0) {
            double evolutionDmgByBluntSpike = (totalCrit + 15 - 80) * 1.4 + 15;
            System.out.println("evolutionDmgByBluntSpike = " + evolutionDmgByBluntSpike);
        }

        double totalEvolutionDmg = evolutionDmg + manaEvolutionDmg;
    }

}