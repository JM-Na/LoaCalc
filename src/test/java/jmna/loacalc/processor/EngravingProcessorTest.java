package jmna.loacalc.processor;

import jmna.loacalc.calculator.EngravingEffect;
import jmna.loacalc.calculator.EngravingEffectCalculator;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryEngravings;
import jmna.loacalc.processor.engraving.CharacterEngraving;
import jmna.loacalc.processor.engraving.EngravingProcessor;
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
        ArmoryEngravings armoryEngravings = armoryClient.getArmoryEngravings("레게머리뿌뿌뿡");

        List<CharacterEngraving> characterEngravings = engravingProcessor.parseEngravingEffect(armoryEngravings);

        System.out.println("characterEngravings = " + characterEngravings);

        EngravingEffect engravingEffect = engravingEffectCalculator.calculateEngravingEffect(characterEngravings);
        System.out.println("engravingEffects = " + engravingEffect);

    }


}