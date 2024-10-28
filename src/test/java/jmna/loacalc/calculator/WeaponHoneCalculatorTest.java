package jmna.loacalc.calculator;

import jmna.loacalc.calculator.hone.T4WeaponHone;
import jmna.loacalc.feign.client.armories.*;
import jmna.loacalc.processor.ArkpassiveProcessor;
import jmna.loacalc.processor.GemProcessor;
import jmna.loacalc.processor.avatar.AvatarProcessor;
import jmna.loacalc.processor.avatar.CharacterAvatar;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.EquipmentProcessor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import jmna.loacalc.processor.equipment.armory.Weapon;
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
    private ArkpassiveProcessor arkpassiveProcessor;
    @Autowired
    private GemProcessor gemProcessor;

    @Test
    void getExpectedWeaponSpecUp() {

        List<ArmoryEquipment> armoryEquipment = armoryClient.getArmoryEquipment("레게머리뿌뿌뿡");
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<ArmoryAvatar> armoryAvatars = armoryClient.getArmoryAvatar("레게머리뿌뿌뿡");
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryAvatars);
        ArmoryArkPassive arkpassiveData = arkpassiveProcessor.getArkpassiveData("레게머리뿌뿌뿡");

        int finalMainStat = mainStatCalculator.calculateMainStat(characterEquipment, characterAvatar);
        System.out.println("finalMainStat = " + finalMainStat);

        WeaponPowerDto weaponPowerDto = weaponPowerCalculator.calculateBaseWeaponPowerAndPercent(characterEquipment, arkpassiveData);
        Integer baseWeaponPower = weaponPowerDto.getBaseWeaponPower();
        Double weaponPowerPercent = weaponPowerDto.getWeaponPowerPercent();
        int finalWeaponPower = weaponPowerCalculator.calculateFinalWeaponPower(new WeaponPowerDto(baseWeaponPower, weaponPowerPercent));
        System.out.println("finalWeaponPower = " + finalWeaponPower);

        ArmoryGems armoryGems = armoryClient.getArmoryGems("레게머리뿌뿌뿡");
        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease(armoryGems.getGems());

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

}