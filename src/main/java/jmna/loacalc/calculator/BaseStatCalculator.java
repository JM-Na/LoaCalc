package jmna.loacalc.calculator;

import jmna.loacalc.calculator.transcendence.MainStatByTranscendence;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.processor.avatar.AvatarProcessor;
import jmna.loacalc.processor.avatar.CharacterAvatar;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.EquipmentProcessor;
import jmna.loacalc.processor.equipment.accessory.*;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BaseStatCalculator {

    private final EquipmentProcessor equipmentProcessor;
    private final AvatarProcessor avatarProcessor;

    public void calculateBaseStat(String characterName) {

        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(characterName);

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        Integer totalTranscendence = characterEquipment.getTotalTranscendence();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        int armoryMainStat = calculateArmoryMainStat(baseArmories);
        System.out.println("armoryMainStat = " + armoryMainStat);
        int subEquipmentMainStat = calculateSubEquipmentMainStat(subEquipments);
        System.out.println("subEquipmentMainStat = " + subEquipmentMainStat);
        int transcendenceMainStat = calculateTranscendenceMainStat(baseArmories, totalTranscendence);
        System.out.println("transcendenceMainStat = " + transcendenceMainStat);
        int elixirMainStat = calculateElixirMainStat(baseArmories);
        System.out.println("elixirMainStat = " + elixirMainStat);

        int avatarPercent = getAvatarPercent(characterName);
        System.out.println("avatarPercent = " + avatarPercent);
    }

    public int calculateArmoryMainStat(List<BaseArmory> baseArmories) {

        int sum = 0;

        for (BaseArmory baseArmory : baseArmories) {
            // 방어구의 경우에만 주스텟 계산
            if (baseArmory.getClass().equals(Armor.class)) {
                Integer mainStat = ((Armor) baseArmory).getMainStat();
                sum += mainStat;
            }
        }

        return sum;
    }

    public int calculateSubEquipmentMainStat(List<SubEquipment> subEquipments) {

        int sum = 0;

        for (SubEquipment subEquipment : subEquipments) {
            if (subEquipment.getClass().equals(Accessory.class)) {
                Integer mainStat = ((Accessory) subEquipment).getBasicEffect().getMainStat();
                sum += mainStat;
            }
            if (subEquipment.getClass().equals(Bracelet.class)) {
                List<BraceletEffect> braceletEffects = ((Bracelet) subEquipment).getBraceletEffects();
                for (BraceletEffect braceletEffect : braceletEffects) {
                    if (braceletEffect.getName().equals("힘")
                            || braceletEffect.getName().equals("민첩")
                            || braceletEffect.getName().equals("지능")) {
                        int effect = Integer.parseInt(braceletEffect.getEffect());
                        sum += effect;
                    }
                }
            }
        }

        return sum;
    }

    public int calculateTranscendenceMainStat(List<BaseArmory> baseArmories, int totalGrade) {

        int sum = 0;

        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Armor.class)) {
                Integer lvl = baseArmory.getTranscendenceLvl();
                Integer grade = baseArmory.getTranscendenceGrade();
                Integer mainStatByLevel = MainStatByTranscendence.findMainStatByLevel(lvl);
                if (mainStatByLevel != null)
                    sum += mainStatByLevel;
                // 장갑일 경우 효과를 통한 추가적인 스탯 상승이 존재함.
                if (baseArmory.getType().contains("장갑")) {
                    if (grade >= 5)
                        sum += 4200;
                    if (grade >= 15)
                        sum += 4200;
                    if (grade >= 20)
                        sum += 4200;
                }

                // 투구의 경우 전체 초월 레벨의 합을 통한 스탯 상승이 존재함.
                if (baseArmory.getType().contains("머리")) {
                    if (grade >= 10)
                        sum += (55 * totalGrade);
                }
            }
        }
        return sum;
    }

    public int calculateElixirMainStat(List<BaseArmory> baseArmories) {
        int sum = 0;
        for (BaseArmory baseArmory : baseArmories) {
            if (baseArmory.getClass().equals(Armor.class)) {
                List<ElixirEffect> elixirEffects = ((Armor) baseArmory).getElixirEffects();
                for (ElixirEffect elixirEffect : elixirEffects) {
                    if (elixirEffect.getEffectName().contains("힘") ||
                            elixirEffect.getEffectName().contains("민첩") ||
                            elixirEffect.getEffectName().contains("지능")) {
                        sum += elixirEffect.getEffect();
                    }
                }
            }
        }

        return sum;
    }

    public int getAvatarPercent(String characterName) {
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(characterName);
        Integer epicCount = characterAvatar.getEpicCount();
        Integer legendaryCount = characterAvatar.getLegendaryCount();

        return (legendaryCount * 2) + epicCount;
    }
}
