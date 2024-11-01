package jmna.loacalc.calculator.elixir;

import lombok.Data;

@Data
public class ElixirEffect {
    private int attackPower;
    private int weaponPower;
    private int mainStat;

    private double atkPowerPercent;

    private double bossDmg;
    private double addDmg;
    private double critDmg;
    private double outgoingDmg;

    private double dmgReduction;
    private int phyDefense;
    private int magDefense;
    private int maxHP;
    private int hpRegen;

    private double atkBuffEfficiency;
    private double shieldEnhance;
    private double healingEnhance;

    public void addAttackPower(int increment) {
        this.attackPower += increment;
    }
    public void addWeaponPower(int increment) {
        this.weaponPower += increment;
    }
    public void addMainStat(int increment) {
        this.mainStat += increment;
    }
    public void addBossDmg(double increment) {
        this.bossDmg += increment;
    }
    public void addAttackPowerPercent(double increment) {
        this.atkPowerPercent += increment;
    }
    public void addOutgoingDmg(double increment) {
        this.outgoingDmg += increment;
    }
    public void addAddDmg(double increment) {
        this.addDmg += increment;
    }
    public void addCritDmg(double increment) {
        this.critDmg += increment;
    }
    public void addDmgReduction(double increment) {
        this.dmgReduction += increment;
    }
    public void addPhyDefense(int increment) {
        this.phyDefense += increment;
    }
    public void addMagDefense(int increment) {
        this.magDefense += increment;
    }
    public void addMaxHP(int increment) {
        this.maxHP += increment;
    }
    public void addHPRegen(int increment) {
        this.hpRegen += increment;
    }
    public void addAtkBuffEfficiency(double increment) {
        this.atkBuffEfficiency += increment;
    }
    public void addShieldEnhance(double increment) {
        this.shieldEnhance += increment;
    }
    public void addHealingEnhance(double increment) {
        this.healingEnhance += increment;
    }
}
