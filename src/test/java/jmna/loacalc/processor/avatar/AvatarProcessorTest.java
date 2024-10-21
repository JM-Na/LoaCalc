package jmna.loacalc.processor.avatar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AvatarProcessorTest {

    @Autowired
    private AvatarProcessor avatarProcessor;

    @Test
    void inputAvatar() {
        CharacterAvatar characterAvatar = avatarProcessor.parseAvatar("일상이억까인사나이");

        System.out.println("characterAvatar = " + characterAvatar);
    }

}