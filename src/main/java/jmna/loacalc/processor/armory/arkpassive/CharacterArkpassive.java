package jmna.loacalc.processor.armory.arkpassive;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CharacterArkpassive {
    private String type; // 진화, 깨달음, 도약
    private int tier; // 아크패시브 노드의 티어
    private String name;
    private int lvl;
    private String icon;

    public CharacterArkpassive(String type, int tier, String name, int lvl, String icon) {
        this.type = type;
        this.tier = tier;
        this.name = name;
        this.lvl = lvl;
        this.icon = icon;
    }
}
