package jmna.loacalc.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WeaponHoneCalculatorTest {

    @Autowired
    private WeaponPowerCalculator weaponPowerCalculator;

    @Autowired
    private AttackPowerCalculator attackPowerCalculator;


    @Test
    void getExpectedWeaponSpecUp() {

    }

}