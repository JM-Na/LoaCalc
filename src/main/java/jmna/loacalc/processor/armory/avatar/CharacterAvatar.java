package jmna.loacalc.processor.armory.avatar;

import lombok.Data;

import java.util.List;

@Data
public class CharacterAvatar {
    private List<Avatar> avatars;
    private Integer legendaryCount;
    private Integer epicCount;

    public CharacterAvatar(List<Avatar> avatars, int legendaryCount, int epicCount) {
        this.avatars = avatars;
        this.legendaryCount = legendaryCount;
        this.epicCount = epicCount;
    }
}
