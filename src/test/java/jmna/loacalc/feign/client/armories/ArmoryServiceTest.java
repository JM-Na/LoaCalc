package jmna.loacalc.feign.client.armories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArmoryServiceTest {

    @Autowired
    private ArmoryService armoryService;

    @Test
    void getArmoriesProfiles() {
        ArmoryProfiles armoryProfiles = armoryService.getArmoryProfiles("레게머리뿌뿌뿡");

        System.out.println("armoriesProfiles = " + armoryProfiles);

        assertThat(armoryProfiles).isNotNull();
    }

    @Test
    void getArmoriesEquipment() {
        List<ArmoryEquipment> armoryEquipments = armoryService.getArmoryEquipment("이의동영혼수확기");

        System.out.println("armoriesEquipments = " + armoryEquipments);

        assertThat(armoryEquipments).isNotNull();
    }

    @Test
    void getArmoriesAvatar() {
        List<ArmoryAvatar> armoryAvatars = armoryService.getArmoryAvatars("레게머리뿌뿌뿡");

        System.out.println("armoriesAvatars = " + armoryAvatars);

        assertThat(armoryAvatars).isNotNull();
    }

    @Test
    void getArmoriesCombatSkills() {
        List<ArmoryCombatSkill> armoryCombatSkills = armoryService.getArmoryCombatSkills("레게머리뿌뿌뿡");

        System.out.println("armoriesCombatSkills = " + armoryCombatSkills);

        assertThat(armoryCombatSkills).isNotNull();
    }

    @Test
    void getArmoriesEngravings() {
        ArmoryEngravings armoryEngravings = armoryService.getArmoryEngravings("레게머리뿌뿌뿡");

        System.out.println("armoriesEngravings = " + armoryEngravings);

        assertThat(armoryEngravings).isNotNull();
    }

    @Test
    void getArmoriesCards() {
        ArmoryCards armoryCards = armoryService.getArmoryCards("레게머리뿌뿌뿡");

        System.out.println("armoriesCards = " + armoryCards);

        assertThat(armoryCards).isNotNull();
    }

    @Test
    void getArmoriesGems() {
        ArmoryGems armoryGems = armoryService.getArmoryGems("카드찾는아이");

        System.out.println("armoriesGems = " + armoryGems);

        assertThat(armoryGems).isNotNull();
    }

    @Test
    void getArmoriesColosseums() {
        ArmoryColosseums armoryColosseums = armoryService.getArmoryColosseums("레게머리뿌뿌뿡");

        System.out.println("armoriesColosseums = " + armoryColosseums);

        assertThat(armoryColosseums).isNotNull();
    }

    @Test
    void getArmoriesCollectibles() {
        List<ArmoryCollectible> armoryCollectibles = armoryService.getArmoryCollectibles("레게머리뿌뿌뿡");

        System.out.println("armoriesCollectibles = " + armoryCollectibles);

        assertThat(armoryCollectibles).isNotNull();
    }

    @Test
    void getArmoriesArkPassive() {
        ArmoryArkPassive armoryArkPassive = armoryService.getArmoryArkPassive("레게머리뿌뿌뿡");

        System.out.println("armoriesArkPassive = " + armoryArkPassive);

        assertThat(armoryArkPassive).isNotNull();
    }
}