package jmna.loacalc.calculator;

import jmna.loacalc.calculator.transcendence.MainStatByTranscendence;
import jmna.loacalc.processor.avatar.CharacterAvatar;
import jmna.loacalc.processor.equipment.CharacterEquipment;
import jmna.loacalc.processor.equipment.accessory.*;
import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MainStatCalculator {

    //TODO 카드, 원정대 수집을 통한 수치 상승은 아직 구현되지 않음.
    public int calculateMainStat(CharacterEquipment characterEquipment, CharacterAvatar characterAvatar) {

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        Integer totalTranscendence = characterEquipment.getTotalTranscendence();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        int sum = 0;

        sum += calculateArmoryMainStat(baseArmories);
        sum += calculateSubEquipmentMainStat(subEquipments);
        sum += calculateTranscendenceMainStat(baseArmories, totalTranscendence);
        sum += calculateElixirMainStat(baseArmories);

        int avatarPercent = getAvatarPercent(characterAvatar);

        int finalMainStat = (int) ((sum+ 477+1430+210) * ((100 + avatarPercent + 1) / 100.0));
        System.out.println("finalMainStat = " + finalMainStat);

        return finalMainStat;
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
            if (baseArmory.getClass().equals(Armor.class) && baseArmory.getTranscendenceLvl() != null) {
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
            if (baseArmory.getClass().equals(Armor.class) && ((Armor) baseArmory).getElixirEffects() != null) {
                List<ElixirEffect> elixirEffects = ((Armor) baseArmory).getElixirEffects();
                for (ElixirEffect elixirEffect : elixirEffects) {
                    if (elixirEffect.getEffectName().equals("힘") ||
                            elixirEffect.getEffectName().equals("민첩") ||
                            elixirEffect.getEffectName().equals("지능")) {
                        sum += elixirEffect.getEffect();
                    }
                }
            }
        }
        return sum;
    }

    public int getAvatarPercent(CharacterAvatar characterAvatar) {
        Integer epicCount = characterAvatar.getEpicCount();
        Integer legendaryCount = characterAvatar.getLegendaryCount();

        return (legendaryCount * 2) + epicCount;
    }
}
