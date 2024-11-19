package jmna.loacalc.processor.armory.engraving;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CharacterEngraving {

    private String name;
    private int lvl;

    private Boolean isTier4;
    private String grade;
    private int abilityStoneLvl;

    public CharacterEngraving(String name, int lvl) {
        this.name = name;
        this.lvl = lvl;
        this.isTier4 = false;
    }

    public CharacterEngraving(String name, Integer lvl, String grade, Integer abilityStoneLevel) {
        this.name = name;
        this.lvl = lvl;
        this.isTier4 = true;
        this.grade = grade;
        this.abilityStoneLvl = abilityStoneLevel;
    }
}
