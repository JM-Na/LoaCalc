package jmna.loacalc.calculator;

import jmna.loacalc.calculator.arkpassive.ArkpassiveEffect;
import jmna.loacalc.calculator.arkpassive.ArkpassiveEffectCalculator;
import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.engraving.EngravingEffect;
import jmna.loacalc.calculator.engraving.EngravingEffectCalculator;
import jmna.loacalc.calculator.hone.T4WeaponHone;
import jmna.loacalc.calculator.subequipments.AccessoryEffect;
import jmna.loacalc.calculator.transcendence.TranscEffect;
import jmna.loacalc.calculator.v1.AttackPowerCalculator;
import jmna.loacalc.calculator.v1.MainStatCalculator;
import jmna.loacalc.calculator.v1.WeaponPowerCalculator;
import jmna.loacalc.calculator.v1.WeaponPowerDto;
import jmna.loacalc.calculator.v2.AccessorySpecUpCalculator;
import jmna.loacalc.calculator.v2.GemEngravingCalculator;
import jmna.loacalc.calculator.v2.WeaponHoneCalculator;
import jmna.loacalc.feign.client.armories.*;
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
import jmna.loacalc.processor.armory.equipment.armory.Weapon;
import jmna.loacalc.processor.auction.AuctionProcessor;
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
    @Autowired
    private TotalArmoryEffectCalculator totalArmoryEffectCalculator;
    @Autowired
    private EngravingEffectCalculator engravingEffectCalculator;
    @Autowired
    private ArmoryEffectCalculator armoryEffectCalculator;
    @Autowired
    private EngravingProcessor engravingProcessor;
    @Autowired
    private WeaponHoneCalculator weaponHoneCalculator;
    @Autowired
    private AccessorySpecUpCalculator accessorySpecUpCalculator;
    @Autowired
    private GemEngravingCalculator gemEngravingCalculator;
    @Autowired
    private ProfileProcessor profileProcessor;
    @Autowired
    private ArkpassiveProcessor arkpassiveProcessor;
    @Autowired
    private ArkpassiveEffectCalculator arkpassiveEffectCalculator;
    @Autowired
    private AuctionProcessor auctionProcessor;


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
    void 무기_강화_공격력_상승률_확인() {
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("레게머리뿌뿌뿡", null);

        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<ArmoryAvatar> armoryAvatars = armoryTotal.getArmoryAvatars();
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryAvatars);
        ArmoryArkPassive arkpassiveData = armoryTotal.getArmoryArkPassive();
        ArmoryEngravings armoryEngravings = armoryTotal.getArmoryEngravings();

        int finalMainStat = mainStatCalculator.calculateMainStat(characterEquipment, characterAvatar);
        System.out.println("finalMainStat = " + finalMainStat);

        WeaponPowerDto weaponPowerDto = weaponPowerCalculator.calculateBaseWeaponPowerAndPercent(characterEquipment, arkpassiveData);
        Integer baseWeaponPower = weaponPowerDto.getBaseWeaponPower();
        Double weaponPowerPercent = weaponPowerDto.getWeaponPowerPercent();
        int finalWeaponPower = weaponPowerCalculator.calculateFinalWeaponPower(new WeaponPowerDto(baseWeaponPower, weaponPowerPercent));
        System.out.println("finalWeaponPower = " + finalWeaponPower);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        TranscEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);
        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);

        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease( armoryTotal.getArmoryGem().getGems());

        TotalArmoryEffect totalArmoryEffect = totalArmoryEffectCalculator.calculateTotalArmoryEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        totalArmoryEffect.setCharacterAvatar(characterAvatar);
        totalArmoryEffect.setGemAttackPowerPercent(gemBasicAttackPowerIncrease);

        double v = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        weaponHoneCalculator.calculateExpectedValue(totalArmoryEffect, 20);
    }

    @Test
    void 각인서_스펙업_확인() {
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("이의동영혼수확기", null);

        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<ArmoryAvatar> armoryAvatars = armoryTotal.getArmoryAvatars();
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryAvatars);
        ArmoryArkPassive arkpassiveData = armoryTotal.getArmoryArkPassive();
        ArmoryEngravings armoryEngravings = armoryTotal.getArmoryEngravings();


        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        TranscEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);
        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);

        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease( armoryTotal.getArmoryGem().getGems());

        TotalArmoryEffect totalArmoryEffect = totalArmoryEffectCalculator.calculateTotalArmoryEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        totalArmoryEffect.setCharacterAvatar(characterAvatar);
        totalArmoryEffect.setGemAttackPowerPercent(gemBasicAttackPowerIncrease);

        ArmoryProfile armoryProfile = armoryTotal.getArmoryProfile();
        CharacterProfile characterProfile = profileProcessor.processProfiles(armoryProfile);


        marketProcessor.initEngravingBookPrice();


        gemEngravingCalculator.calculateExpectedValueByRelicEngravingBook(totalArmoryEffect, characterEngravings, characterProfile);
    }

    @Test
    void 방어구_강화_공격력_상승률_확인() throws Exception {
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("레게머리뿌뿌뿡", null);

        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<ArmoryAvatar> armoryAvatars = armoryTotal.getArmoryAvatars();
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryAvatars);
        ArmoryArkPassive arkpassiveData = armoryTotal.getArmoryArkPassive();
        ArmoryEngravings armoryEngravings = armoryTotal.getArmoryEngravings();

        int finalMainStat = mainStatCalculator.calculateMainStat(characterEquipment, characterAvatar);
        System.out.println("finalMainStat = " + finalMainStat);

        WeaponPowerDto weaponPowerDto = weaponPowerCalculator.calculateBaseWeaponPowerAndPercent(characterEquipment, arkpassiveData);
        Integer baseWeaponPower = weaponPowerDto.getBaseWeaponPower();
        Double weaponPowerPercent = weaponPowerDto.getWeaponPowerPercent();
        int finalWeaponPower = weaponPowerCalculator.calculateFinalWeaponPower(new WeaponPowerDto(baseWeaponPower, weaponPowerPercent));
        System.out.println("finalWeaponPower = " + finalWeaponPower);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        TranscEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);
        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);

        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease( armoryTotal.getArmoryGem().getGems());

        TotalArmoryEffect totalArmoryEffect = totalArmoryEffectCalculator.calculateTotalArmoryEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        totalArmoryEffect.setCharacterAvatar(characterAvatar);
        totalArmoryEffect.setGemAttackPowerPercent(gemBasicAttackPowerIncrease);

        double v = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        marketProcessor.initPrice();

        weaponHoneCalculator.calculateArmorExpectedValue(totalArmoryEffect, "머리 방어구", 18);
    }

    @Test
    void 상급_재련_공격력_상승률_확인() {
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("레게머리뿌뿌뿡", null);

        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<ArmoryAvatar> armoryAvatars = armoryTotal.getArmoryAvatars();
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryAvatars);
        ArmoryArkPassive arkpassiveData = armoryTotal.getArmoryArkPassive();
        ArmoryEngravings armoryEngravings = armoryTotal.getArmoryEngravings();

        int finalMainStat = mainStatCalculator.calculateMainStat(characterEquipment, characterAvatar);
        System.out.println("finalMainStat = " + finalMainStat);

        WeaponPowerDto weaponPowerDto = weaponPowerCalculator.calculateBaseWeaponPowerAndPercent(characterEquipment, arkpassiveData);
        Integer baseWeaponPower = weaponPowerDto.getBaseWeaponPower();
        Double weaponPowerPercent = weaponPowerDto.getWeaponPowerPercent();
        int finalWeaponPower = weaponPowerCalculator.calculateFinalWeaponPower(new WeaponPowerDto(baseWeaponPower, weaponPowerPercent));
        System.out.println("finalWeaponPower = " + finalWeaponPower);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        TranscEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);
        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);

        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease( armoryTotal.getArmoryGem().getGems());

        TotalArmoryEffect totalArmoryEffect = totalArmoryEffectCalculator.calculateTotalArmoryEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        totalArmoryEffect.setCharacterAvatar(characterAvatar);
        totalArmoryEffect.setGemAttackPowerPercent(gemBasicAttackPowerIncrease);

        double v = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);


        marketProcessor.initPrice();
        
        weaponHoneCalculator.calculateAdvancedHoneExpectedValue(totalArmoryEffect, "머리", 20);
    }

    @Test
    void 재련_단계_확인() {
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("일말상초는과학이야", null);

        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<ArmoryAvatar> armoryAvatars = armoryTotal.getArmoryAvatars();
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryAvatars);
        ArmoryEngravings armoryEngravings = armoryTotal.getArmoryEngravings();

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        TranscEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);
        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);

        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease( armoryTotal.getArmoryGem().getGems());

        TotalArmoryEffect totalArmoryEffect = totalArmoryEffectCalculator.calculateTotalArmoryEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        totalArmoryEffect.setCharacterAvatar(characterAvatar);
        totalArmoryEffect.setGemAttackPowerPercent(gemBasicAttackPowerIncrease);


        marketProcessor.initPrice();

        weaponHoneCalculator.checkHoneArmory(baseArmories, totalArmoryEffect);
    }

    @Test
    void checkAccessory() {
        // 여러개의 데이터 요청을 통채로 처리하는 API
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("이의동영혼수확기", null);


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


//        marketProcessor.initPrice();
//        marketProcessor.initEngravingBookPrice();
//        auctionProcessor.initPrice();

//        weaponHoneCalculator.checkAccessory(subEquipments, totalArmoryEffect, characterProfile);
//        weaponHoneCalculator.calculateExpectedValueByRelicEngravingBook(totalArmoryEffect, characterEngravings, characterProfile);
//        weaponHoneCalculator.checkHoneArmory(baseArmories, totalArmoryEffect);
//        weaponHoneCalculator.checkWeaponHone(baseArmories, totalArmoryEffect);
        gemEngravingCalculator.calculateGemSpecUp(totalArmoryEffect);
    }

    @Test
    void 강화_효율_V2() {
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("레게머리뿌뿌뿡", null);

        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);
        List<ArmoryAvatar> armoryAvatars = armoryTotal.getArmoryAvatars();
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryAvatars);
        ArmoryArkPassive arkpassiveData = armoryTotal.getArmoryArkPassive();
        ArmoryEngravings armoryEngravings = armoryTotal.getArmoryEngravings();

        List<CharacterArkpassive> characterArkpassiveList = arkpassiveProcessor.processArkpassiveData(arkpassiveData);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        ArmoryEffect armoryEffect = armoryEffectCalculator.calculateArmoryEffect(baseArmories);
        TranscEffect transcEffect = armoryEffectCalculator.calculateTranscEffect(baseArmories, totalTranscendence);
        ElixirEffect elixirEffect = armoryEffectCalculator.calculateElixirEffect(baseArmories);
        AccessoryEffect accessoryEffect = armoryEffectCalculator.calculateAccessoryEffect(subEquipments);

        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);
        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);

        double gemBasicAttackPowerIncrease = gemProcessor.getGemBasicAttackPowerIncrease( armoryTotal.getArmoryGem().getGems());

        TotalArmoryEffect totalArmoryEffect = totalArmoryEffectCalculator.calculateTotalArmoryEffect(armoryEffect, elixirEffect, transcEffect, engravingEffect, accessoryEffect);
        totalArmoryEffect.setCharacterAvatar(characterAvatar);
        totalArmoryEffect.setGemAttackPowerPercent(gemBasicAttackPowerIncrease);

        double v = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        weaponHoneCalculator.test(baseArmories, totalArmoryEffect);
        System.out.println("characterArkpassiveList = " + characterArkpassiveList);
    }
}
