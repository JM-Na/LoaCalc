package jmna.loacalc.processor;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CharacterProfile {
    private String name;
    private String className;
    private double lvl;
    private double itemLvl;
    private String characterImage;
    private String serverName;

    private int crit;
    private int specialization;
    private int domination;
    private int swiftness;
    private int endurance;
    private int expertise;

    private int maxHP;
    private int atkPower;

    public CharacterProfile(String name, String className, String lvl, String itemLvl, String characterImage, String serverName) {
        this.name = name;
        this.className = className;
        this.lvl = Double.parseDouble(lvl);
        this.itemLvl = Double.parseDouble(itemLvl.replace(",", ""));
        this.characterImage = characterImage;
        this. serverName = serverName;
    }
}
