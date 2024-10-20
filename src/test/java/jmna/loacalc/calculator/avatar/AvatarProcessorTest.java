package jmna.loacalc.calculator.avatar;

import jmna.loacalc.processor.avatar.AvatarProcessor;
import jmna.loacalc.processor.avatar.CharacterAvatar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AvatarProcessorTest {

    @Autowired
    private AvatarProcessor avatarProcessor;

    @Test
    void inputAvatar() {
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar("일말상초는과학이야");

        System.out.println("characterAvatar = " + characterAvatar);
    }

}