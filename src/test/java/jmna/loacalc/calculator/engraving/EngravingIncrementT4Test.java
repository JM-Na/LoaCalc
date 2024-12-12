package jmna.loacalc.calculator.engraving;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EngravingIncrementT4Test {

    @Test
    void 각인_등급에_따른_상승값() {
        EngravingLvlIncrement incrementByGrade = EngravingIncrementT4.getIncrementByGrade("원한", "유물");
        System.out.println("incrementByGrade = " + incrementByGrade);
    }

}