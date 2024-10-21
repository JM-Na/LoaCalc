package jmna.loacalc.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BaseStatCalculatorTest {

    @Autowired
    private BaseStatCalculator baseStatCalculator;

    @Test
    void calculateMainStat() {
        baseStatCalculator.calculateBaseStat("레게머리뿌뿌뿡");
    }


}