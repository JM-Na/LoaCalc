package jmna.loacalc.processor;

import jmna.loacalc.feign.client.armories.ArmoryArkPassive;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.processor.armory.arkpassive.ArkpassiveProcessor;
import jmna.loacalc.processor.armory.arkpassive.CharacterArkpassive;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ArkpassiveProcessorTest {

    @Autowired
    private ArmoryClient armoryClient;
    @Autowired
    private ArkpassiveProcessor arkpassiveProcessor;

    @Test
    void ArkpassiveProcess() {
        ArmoryArkPassive armoryArkPassive = armoryClient.getArmoryArkPassive("레게머리뿌뿌뿡");

        System.out.println("armoryArkPassive = " + armoryArkPassive);

        List<CharacterArkpassive> characterArkpassives = arkpassiveProcessor.processArkpassiveData(armoryArkPassive);

        for (CharacterArkpassive characterArkpassive : characterArkpassives) {
            if (characterArkpassive.getType().equals("깨달음")) {
                System.out.println("characterArkpassive = " + characterArkpassive);
            }
        }

//        System.out.println("characterArkpassives = " + characterArkpassives);

    }
}