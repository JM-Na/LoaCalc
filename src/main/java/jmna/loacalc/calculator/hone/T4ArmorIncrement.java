package jmna.loacalc.calculator.hone;

import lombok.Data;

@Data
public class T4ArmorIncrement {
    private int mainStat; // 주 능력치
    private int vitality; // 체력
    private int phyDef; // 물리 방어력
    private int magDef; // 마법 방어력

    public T4ArmorIncrement(int mainStat, int vitality, int phyDef, int magDef) {
        this.mainStat = mainStat;
        this.vitality = vitality;
        this.phyDef = phyDef;
        this.magDef = magDef;
    }
}
