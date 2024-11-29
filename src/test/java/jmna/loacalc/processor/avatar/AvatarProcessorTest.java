package jmna.loacalc.processor.avatar;

import jmna.loacalc.feign.client.armories.ArmoryAvatar;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.processor.armory.avatar.AvatarProcessor;
import jmna.loacalc.processor.armory.avatar.CharacterAvatar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AvatarProcessorTest {

    @Autowired
    private AvatarProcessor avatarProcessor;

    @Autowired
    private ArmoryClient armoryClient;

    @Test
    void inputAvatar() {
        List<ArmoryAvatar> armoryAvatars = armoryClient.getArmoryAvatar("레게머리뿌뿌뿡");
        System.out.println("armoryAvatars = " + armoryAvatars);
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar(armoryAvatars);

        System.out.println("characterAvatar = " + characterAvatar);
    }

}