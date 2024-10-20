package jmna.loacalc.calculator;

import jmna.loacalc.processor.equipment.armory.Armor;
import jmna.loacalc.processor.equipment.armory.BaseArmory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BaseStatCalculator {

    public void calculateBaseStat() {

    }

    public void calculateArmoryMainStat(List<BaseArmory> baseArmories) {
        for (BaseArmory baseArmory : baseArmories) {
            // 방어구의 경우에만 주스텟 계산
            if (baseArmory.getClass().equals(Armor.class)) {

            }
        }
    }

    public void calculateSubEquipmentMainStat() {

    }

    public void calculateTranscendenceMainStat() {

    }
}
