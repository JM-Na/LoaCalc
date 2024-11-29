package jmna.loacalc.processor.armory.avatar;

import lombok.Data;

import java.util.List;

@Data
public class CharacterAvatar {
    private List<Avatar> avatars;
    private Integer legendaryCount;
    private Integer epicCount;
    private Boolean weapon;
    private Boolean head;
    private Boolean chest;
    private Boolean pants;

    public CharacterAvatar(List<Avatar> avatars, int legendaryCount, int epicCount) {
        this.avatars = avatars;
        this.legendaryCount = legendaryCount;
        this.epicCount = epicCount;
    }

    public void checkParts() {
        for (Avatar avatar : avatars) {
            String type = avatar.getType();
            if (avatar.getGrade().equals("전설")) {
                switch (type) {
                    case "무기 아바타" -> this.setWeapon(true);
                    case "머리 아바타" -> this.setHead(true);
                    case "상의 아바타" -> this.setChest(true);
                    case "하의 아바타" -> this.setPants(true);
                }
            }
        }
    }
}
