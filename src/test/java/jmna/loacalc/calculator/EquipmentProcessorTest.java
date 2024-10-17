package jmna.loacalc.calculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EquipmentProcessorTest {

    @Autowired
    private EquipmentProcessor equipmentProcessor;

    @Test
    void testEquipmentObject() {
        equipmentProcessor.parseEquipmentInfo("이의동영혼수확기");
    }

}