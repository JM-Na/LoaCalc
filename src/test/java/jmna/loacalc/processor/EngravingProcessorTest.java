package jmna.loacalc.processor;

import jmna.loacalc.calculator.EngravingEffectCalculator;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryEngravings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@SpringBootTest
class EngravingProcessorTest {

    @Autowired
    private ArmoryClient armoryClient;
    @Autowired
    private EngravingProcessor engravingProcessor;
    @Autowired
    private EngravingEffectCalculator engravingEffectCalculator;

    @Test
    void parseEngraving() {
        ArmoryEngravings armoryEngravings = armoryClient.getArmoryEngravings("일말상초는과학이야");

        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);

        engravingEffectCalculator.calculateEngravingEffect(characterEngravings);

    }


}