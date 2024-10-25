package jmna.loacalc.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeaponPowerCalculatorTest {

    @Autowired
    private WeaponPowerCalculator weaponPowerCalculator;

    @Test
    void calculateWeaponPower() {
        weaponPowerCalculator.calculateTotalWeaponPower("이의동영혼수확기");

    }

}