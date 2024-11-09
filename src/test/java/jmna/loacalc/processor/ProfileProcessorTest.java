package jmna.loacalc.processor;

import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryProfiles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProfileProcessorTest {

    @Autowired
    private ArmoryClient armoryClient;
    @Autowired
    private ProfileProcessor profileProcessor;

    @Test
    void processProfiles() {
        ArmoryProfiles armoryProfiles = armoryClient.getArmoryProfiles("레게머리뿌뿌뿡");
        CharacterProfile characterProfile = profileProcessor.processProfiles(armoryProfiles);

        System.out.println("characterProfile = " + characterProfile);
    }

}