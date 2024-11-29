package jmna.loacalc.calculator;

import jmna.loacalc.calculator.hone.HoneIngredients;
import jmna.loacalc.calculator.hone.T4WeaponHone;
import jmna.loacalc.calculator.v1.AttackPowerCalculator;
import jmna.loacalc.calculator.v1.MainStatCalculator;
import jmna.loacalc.calculator.v1.WeaponPowerCalculator;
import jmna.loacalc.calculator.v1.WeaponPowerDto;
import jmna.loacalc.feign.client.armories.*;
import jmna.loacalc.processor.armory.GemProcessor;
import jmna.loacalc.processor.armory.avatar.AvatarProcessor;
import jmna.loacalc.processor.armory.avatar.CharacterAvatar;
import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import jmna.loacalc.processor.armory.equipment.EquipmentProcessor;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import jmna.loacalc.processor.armory.equipment.armory.Weapon;
import jmna.loacalc.processor.market.MarketProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WeaponHoneCalculatorTest {
    @Autowired
    private MainStatCalculator mainStatCalculator;
    @Autowired
    private WeaponPowerCalculator weaponPowerCalculator;
    @Autowired
    private AttackPowerCalculator attackPowerCalculator;
    @Autowired
    private ArmoryClient armoryClient;
    @Autowired
    private EquipmentProcessor equipmentProcessor;
    @Autowired
    private AvatarProcessor avatarProcessor;
    @Autowired
    private GemProcessor gemProcessor;
    @Autowired
    private MarketProcessor marketProcessor;

    @Test
    void getExpectedWeaponSpecUp() {

        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("레게머리뿌뿌뿡", null);

        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<ArmoryAvatar> armoryAvatars = armoryTotal.getArmoryAvatars();
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryAvatars);
        ArmoryArkPassive arkpassiveData = armoryTotal.getArmoryArkPassive();

        int finalMainStat = mainStatCalculator.calculateMainStat(characterEquipment, characterAvatar);
        System.out.println("finalMainStat = " + finalMainStat);

        WeaponPowerDto weaponPowerDto = weaponPowerCalculator.calculateBaseWeaponPowerAndPercent(characterEquipment, arkpassiveData);
        Integer baseWeaponPower = weaponPowerDto.getBaseWeaponPower();
        Double weaponPowerPercent = weaponPowerDto.getWeaponPowerPercent();
        int finalWeaponPower = weaponPowerCalculator.calculateFinalWeaponPower(new WeaponPowerDto(baseWeaponPower, weaponPowerPercent));
        System.out.println("finalWeaponPower = " + finalWeaponPower);

        ArmoryGem armoryGem = armoryTotal.getArmoryGem();
        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease(armoryGem.getGems());

        int attackPower = attackPowerCalculator.calculateFinalAttackPower(characterEquipment, finalMainStat, finalWeaponPower, gemBasicAttackPowerIncrease);
        System.out.println("attackPower = " + attackPower);

        BaseArmory weapon = characterEquipment.getBaseArmories().get(0);

        // 일반 재련 20 -> 21 강화 시 공격력 상승량
        if (weapon.getClass().equals(Weapon.class)) {
            Integer weaponPower1 = ((Weapon) weapon).getWeaponPower();
            int honeLvl = Integer.parseInt(weapon.getName().split(" ")[0].replace("+", ""));
            System.out.println("honeLvl = " + honeLvl);

            int incrementByTargetLevel = T4WeaponHone.findIncrementByTargetLevel(honeLvl + 1);
            System.out.println("incrementByTargetLevel = " + incrementByTargetLevel);

            int finalWeaponPower2 = weaponPowerCalculator.calculateFinalWeaponPower(new WeaponPowerDto(baseWeaponPower + incrementByTargetLevel, weaponPowerPercent));
            int attackPower1 = attackPowerCalculator.calculateFinalAttackPower(characterEquipment, finalMainStat, finalWeaponPower2, gemBasicAttackPowerIncrease);

            System.out.println((finalWeaponPower2 - finalWeaponPower) / (double) finalWeaponPower * 100.0);
            System.out.println((attackPower1 - attackPower) / (double) attackPower * 100.0);
        }

    }

    @Test
    void 강화_가격_확인() {
        marketProcessor.initPrice();

        double costByTargetLevel = T4WeaponHone.findCostByTargetLevel(21, true);

        System.out.println("costByTargetLevel = " + costByTargetLevel);

    }

}