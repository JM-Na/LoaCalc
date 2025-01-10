package jmna.loacalc.service;

import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryTotalForEffect;
import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.GemProcessor;
import jmna.loacalc.processor.armory.ProfileProcessor;
import jmna.loacalc.processor.armory.arkpassive.ArkpassiveProcessor;
import jmna.loacalc.processor.armory.arkpassive.CharacterArkpassive;
import jmna.loacalc.processor.armory.avatar.AvatarProcessor;
import jmna.loacalc.processor.armory.avatar.CharacterAvatar;
import jmna.loacalc.processor.armory.engraving.CharacterEngraving;
import jmna.loacalc.processor.armory.engraving.EngravingProcessor;
import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import jmna.loacalc.processor.armory.equipment.EquipmentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {

    private final ArmoryClient armoryClient;
    private final EquipmentProcessor equipmentProcessor;
    private final AvatarProcessor avatarProcessor;
    private final EngravingProcessor engravingProcessor;
    private final GemProcessor gemProcessor;
    private final ArkpassiveProcessor arkpassiveProcessor;
    private final ProfileProcessor profileProcessor;

    public CharacterEquipment test(String characterName) {
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect(characterName, null);

        // 장비 정보를 담고있는 CharacterEquipment
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryTotal.getArmoryEquipments());
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryTotal.getArmoryAvatars());
        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryTotal.getArmoryEngravings());
        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease(armoryTotal.getArmoryGem().getGems());
        List<CharacterArkpassive> characterArkpassiveList = arkpassiveProcessor.processArkpassiveData(armoryTotal.getArmoryArkPassive());
        CharacterProfile characterProfile = profileProcessor.processProfiles(armoryTotal.getArmoryProfile());

        return characterEquipment;
    }
}
