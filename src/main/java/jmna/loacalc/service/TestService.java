package jmna.loacalc.service;

import jmna.loacalc.calculator.*;
import jmna.loacalc.calculator.arkpassive.ArkpassiveEffect;
import jmna.loacalc.calculator.arkpassive.ArkpassiveEffectCalculator;
import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.engraving.EngravingEffect;
import jmna.loacalc.calculator.engraving.EngravingEffectCalculator;
import jmna.loacalc.calculator.subequipments.AccessoryEffect;
import jmna.loacalc.calculator.transcendence.TranscEffect;
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
import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import jmna.loacalc.processor.auction.AuctionProcessor;
import jmna.loacalc.processor.market.MarketProcessor;
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

    private final AuctionProcessor auctionProcessor;
    private final MarketProcessor marketProcessor;

    private final ArmoryEffectCalculator armoryEffectCalculator;
    private final EngravingEffectCalculator engravingEffectCalculator;
    private final TotalArmoryEffectCalculator totalArmoryEffectCalculator;
    private final ArkpassiveEffectCalculator arkpassiveEffectCalculator;
    private final StatEffectCalculator statEffectCalculator;

    private final WeaponHoneCalculator weaponHoneCalculator;

    public TestDto test(String characterName) {
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect(characterName, null);

        // 장비 정보를 담고있는 CharacterEquipment
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryTotal.getArmoryEquipments());
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryTotal.getArmoryAvatars());
        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryTotal.getArmoryEngravings());
        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease(armoryTotal.getArmoryGem().getGems());
        List<CharacterArkpassive> characterArkpassiveList = arkpassiveProcessor.processArkpassiveData(armoryTotal.getArmoryArkPassive());
        CharacterProfile characterProfile = profileProcessor.processProfiles(armoryTotal.getArmoryProfile());

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTranscendence = characterEquipment.getTotalTranscendence();

        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        TranscEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);
        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);

        TotalArmoryEffect totalArmoryEffect = totalArmoryEffectCalculator.calculateTotalArmoryEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        totalArmoryEffect.setCharacterAvatar(characterAvatar);
        totalArmoryEffect.setGemAttackPowerPercent(gemBasicAttackPowerIncrease);

        ArkpassiveEffect arkpassiveEffect = arkpassiveEffectCalculator.calculateArkpassiveEffect(characterArkpassiveList, characterProfile);
        totalArmoryEffect.mergeArkpassiveEffect(arkpassiveEffect);

        int crit = characterProfile.getCrit();
        double statCrit = statEffectCalculator.calculateStatCrit(crit);
        totalArmoryEffect.addCrit(statCrit);

        auctionProcessor.initPrice();
        marketProcessor.initPrice();
        marketProcessor.initEngravingBookPrice();

        weaponHoneCalculator.checkAccessory(subEquipments, totalArmoryEffect, characterProfile);
        weaponHoneCalculator.calculateExpectedValueByRelicEngravingBook(totalArmoryEffect, characterEngravings, characterProfile);
        weaponHoneCalculator.checkHoneArmory(baseArmories, totalArmoryEffect);
        weaponHoneCalculator.calculateExpectedValue(totalArmoryEffect, 20);

        return new TestDto(characterProfile, characterEquipment, totalArmoryEffect);
    }
}
