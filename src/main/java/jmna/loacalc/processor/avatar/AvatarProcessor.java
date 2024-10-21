package jmna.loacalc.processor.avatar;

import jmna.loacalc.feign.client.armories.ArmoryAvatar;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AvatarProcessor {

    private final ArmoryClient armoryClient;

    public AvatarProcessor(ArmoryClient armoryClient) {
        this.armoryClient = armoryClient;
    }

    public CharacterAvatar parseAvatar(String name) {
        List<ArmoryAvatar> armoryAvatars = armoryClient.getArmoryAvatar(name);

        List<Avatar> avatars = new ArrayList<>();

        // 아바타는 최소 덧입기 제외 무기/머리/상의/하의 모두 장착했다고 가정함.
        int count = 0;
        int legendaryCount = 0;
        int epicCount = 0;

        for (ArmoryAvatar armoryAvatar : armoryAvatars) {
            Avatar avatar = new Avatar(armoryAvatar);
            int setCount = 0;
            if (count == 2 && armoryAvatar.getIsSet()) {
                count++;
                setCount++;
            }
            if (avatar.getGrade().equals("전설"))
                legendaryCount = legendaryCount + setCount + 1;
            if (avatar.getGrade().equals("영웅") && count < 4)
                epicCount = epicCount + setCount + 1;
            avatars.add(avatar);
            count++;
        }

        return new CharacterAvatar(avatars, legendaryCount, epicCount);
    }
}
