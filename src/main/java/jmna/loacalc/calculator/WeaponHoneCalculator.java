package jmna.loacalc.calculator;

import jmna.loacalc.calculator.v1.AttackPowerCalculator;
import jmna.loacalc.processor.armory.equipment.armory.Weapon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeaponHoneCalculator {

    private final AttackPowerCalculator attackPowerCalculator;

    public void calculateExpectedValue(Weapon weapon) {
        String weaponLevel = weapon.getName().split(" ")[1].replace("\\+", "");

        System.out.println("weaponLevel = " + weaponLevel);
    }

}
