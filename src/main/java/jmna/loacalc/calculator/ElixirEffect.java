package jmna.loacalc.calculator;

import lombok.Data;

@Data
public class ElixirEffect {
    private int attackPower;
    private int weaponPower;
    private int mainStat;
    private double bossDmg;
    private double addDmg;
    private double critDmg;
    private double outgoingDmg;

    private double dmgReduction;
    private int phyDefense;
    private int magDefense;
    private int maxHP;

    private double allyEnhance;
    private double shieldEnhance;
    private double healingEnhance;
}
