package jmna.loacalc.calculator;

import jmna.loacalc.processor.equipment.armory.Weapon;
import org.springframework.stereotype.Component;

@Component
public class WeaponHoneCalculator {

    public void calculateExpectedValue(Weapon weapon) {
        String weaponLevel = weapon.getName().split(" ")[1].replace("\\+", "");

        System.out.println("weaponLevel = " + weaponLevel);
    }

}
