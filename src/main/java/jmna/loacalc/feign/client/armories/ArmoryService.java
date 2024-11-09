package jmna.loacalc.feign.client.armories;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmoryService {
    private final ArmoryClient armoryClient;

    public ArmoryService(ArmoryClient armoryClient) {
        this.armoryClient = armoryClient;
    }

    public ArmoryProfile getArmoryProfiles(String characterName) {
        return armoryClient.getArmoryProfiles(characterName);
    }

    public List<ArmoryEquipment> getArmoryEquipment(String characterName) {
        return armoryClient.getArmoryEquipment(characterName);
    }

    public List<ArmoryAvatar> getArmoryAvatars(String characterName) {
        return armoryClient.getArmoryAvatar(characterName);
    }

    public List<ArmoryCombatSkill> getArmoryCombatSkills(String characterName) {
        return armoryClient.getArmoryCombatSkills(characterName);
    }

    public ArmoryEngravings getArmoryEngravings(String characterName) {
        return armoryClient.getArmoryEngravings(characterName);
    }

    public ArmoryCards getArmoryCards(String characterName) {
        return armoryClient.getArmoryCards(characterName);
    }

    public ArmoryGem getArmoryGems(String characterName) {
        return armoryClient.getArmoryGems(characterName);
    }

    public ArmoryColosseums getArmoryColosseums(String characterName) {
        return armoryClient.getArmoryColosseums(characterName);
    }

    public List<ArmoryCollectible> getArmoryCollectibles(String characterName) {
        return armoryClient.getArmoryCollectibles(characterName);
    }

    public ArmoryArkPassive getArmoryArkPassive(String characterName) {
        return armoryClient.getArmoryArkPassive(characterName);
    }
}
