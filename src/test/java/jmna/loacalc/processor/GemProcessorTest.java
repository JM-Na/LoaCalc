package jmna.loacalc.processor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GemProcessorTest {

    @Autowired
    private GemProcessor gemProcessor;

    @Test
    void getWPByGem() {
        gemProcessor.getGemBasicWeaponPowerIncrease("카드찾는아이");
    }

}