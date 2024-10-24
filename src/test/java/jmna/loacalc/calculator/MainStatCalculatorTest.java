package jmna.loacalc.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainStatCalculatorTest {

    @Autowired
    private MainStatCalculator mainStatCalculator;

    @Test
    void calculateMainStat() {
        mainStatCalculator.calculateMainStat("XIEL");
    }


}