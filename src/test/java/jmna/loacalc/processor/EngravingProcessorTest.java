package jmna.loacalc.processor;

import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryEngravings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EngravingProcessorTest {

    @Autowired
    private ArmoryClient armoryClient;
    @Autowired
    private EngravingProcessor engravingProcessor;

    @Test
    void parseEngraving() {
        ArmoryEngravings armoryEngravings = armoryClient.getArmoryEngravings("레게머리뿌뿌뿡");
        System.out.println("armoryEngravings = " + armoryEngravings);

    }


}