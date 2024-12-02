package jmna.loacalc.calculator;

import jmna.loacalc.calculator.hone.T4WeaponHone;
import jmna.loacalc.calculator.v1.AttackPowerCalculator;
import jmna.loacalc.processor.armory.equipment.armory.Weapon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeaponHoneCalculator {

    private final TotalArmoryEffectCalculator totalArmoryEffectCalculator;

    public void calculateExpectedValue(TotalArmoryEffect totalArmoryEffect, int level) {
        System.out.println("weaponLevel = " + level);

        if (level==25) {
            log.info("무기 강화 수치가 이미 최대입니다.");
        }

        int increment = T4WeaponHone.findIncrementByTargetLevel(level);
        log.info(level + "강 도달로 증가하는 무기 공격력 수치: " + increment);
        double cost = T4WeaponHone.findCostByTargetLevel(level, true);
        log.info((level+1) + "강을 위한 소모 골드: " + cost);

        // 기존 무기에서의 공격력 수치
        double preAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        // +1 강화된 상태에서의 공격력 수치
        totalArmoryEffect.addWeaponPower(increment);
        double nextAtkPower = totalArmoryEffectCalculator.calculateAtkPower(totalArmoryEffect);

        double incrementOnAtkPower = (nextAtkPower - preAtkPower) / preAtkPower;
        System.out.println("incrementOnAtkPower = " + incrementOnAtkPower * 100 + "%");
    }

    public void calculateExpectedValueByRelicEngravingBook() {

    }
}
