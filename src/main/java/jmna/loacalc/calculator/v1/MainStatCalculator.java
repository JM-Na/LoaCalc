package jmna.loacalc.calculator.v1;

import jmna.loacalc.calculator.transcendence.MainStatByTranscendence;
import jmna.loacalc.processor.armory.avatar.CharacterAvatar;
import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import jmna.loacalc.processor.armory.equipment.accessory.Accessory;
import jmna.loacalc.processor.armory.equipment.accessory.Bracelet;
import jmna.loacalc.processor.armory.equipment.accessory.BraceletData;
import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.armory.equipment.armory.Armor;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import jmna.loacalc.processor.armory.equipment.armory.ElixirData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MainStatCalculator {

    //TODO 카드, 원정대 수집을 통한 수치 상승은 아직 구현되지 않음.
    public int calculateMainStat(CharacterEquipment characterEquipment, CharacterAvatar characterAvatar) {

        List<BaseArmory> baseArmories = characterEquipment.getBaseArmories();
        int totalTranscendence = characterEquipment.getTotalTranscendence();
        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        int sum = 0;

        sum += calculateArmoryMainStat(baseArmories);
        sum += calculateSubEquipmentMainStat(subEquipments);
        sum += calculateTranscendenceMainStat(baseArmories, totalTranscendence);
        sum += calculateElixirMainStat(baseArmories);

        int avatarPercent = getAvatarPercent(characterAvatar);

        return (int) ((sum+ 477+1430+210) * ((100 + avatarPercent + 1) / 100.0));
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
                List<BraceletData> braceletDataList = ((Bracelet) subEquipment).getBraceletData();
                for (BraceletData braceletData : braceletDataList) {
                    if (braceletData.getName().equals("힘")
                            || braceletData.getName().equals("민첩")
                            || braceletData.getName().equals("지능")) {
                        int effect = Integer.parseInt(braceletData.getEffect());
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
                sum += MainStatByTranscendence.findMainStatByLevel(lvl);
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
            if (baseArmory.getClass().equals(Armor.class) && ((Armor) baseArmory).getElixirData() != null) {
                List<ElixirData> elixirDataList = ((Armor) baseArmory).getElixirData();
                for (ElixirData elixirData : elixirDataList) {
                    if (elixirData.getEffectName().equals("힘") ||
                            elixirData.getEffectName().equals("민첩") ||
                            elixirData.getEffectName().equals("지능")) {
                        sum += elixirData.getEffect();
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
