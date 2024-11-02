package jmna.loacalc.calculator.elixir;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ElixirEffect {
    private String armoryType;

    private int attackPower;
    private int weaponPower;
    private int mainStat;

    private double atkPowerPercent;

    private double addDmg;
    private double critDmg;
    private double outgoingDmg;

    private double dmgReduction;
    private int phyDefense;
    private int magDefense;
    private int maxHP;
    private int hpRegen;

    private double apBuffEfficiency;
    private double shieldEnhance;
    private double healingEnhance;


    public ElixirEffect(String armoryType, int attackPower, int weaponPower, int mainStat, double atkPowerPercent, double addDmg, double critDmg, double outgoingDmg, double dmgReduction, int phyDefense, int magDefense, int maxHP, int hpRegen, double apBuffEfficiency, double shieldEnhance, double healingEnhance) {
        this.armoryType = armoryType;
        this.attackPower = attackPower;
        this.weaponPower = weaponPower;
        this.mainStat = mainStat;
        this.atkPowerPercent = atkPowerPercent;
        this.addDmg = addDmg;
        this.critDmg = critDmg;
        this.outgoingDmg = outgoingDmg;
        this.dmgReduction = dmgReduction;
        this.phyDefense = phyDefense;
        this.magDefense = magDefense;
        this.maxHP = maxHP;
        this.hpRegen = hpRegen;
        this.apBuffEfficiency = apBuffEfficiency;
        this.shieldEnhance = shieldEnhance;
        this.healingEnhance = healingEnhance;
    }

    public void addAttackPower(int increment) {
        this.attackPower += increment;
    }
    public void addWeaponPower(int increment) {
        this.weaponPower += increment;
    }
    public void addMainStat(int increment) {
        this.mainStat += increment;
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
        this.apBuffEfficiency += increment;
    }
    public void addShieldEnhance(double increment) {
        this.shieldEnhance += increment;
    }
    public void addHealingEnhance(double increment) {
        this.healingEnhance += increment;
    }

    public ElixirEffect merge(ElixirEffect other) {
        return new ElixirEffect(
                "SUM",
                this.attackPower + other.attackPower,
                this.weaponPower + other.weaponPower,
                this.mainStat + other.mainStat,
                this.atkPowerPercent + other.atkPowerPercent,
                this.addDmg + other.addDmg,
                this.critDmg + other.critDmg,
                this.outgoingDmg + other.outgoingDmg,
                this.dmgReduction + other.dmgReduction,
                this.phyDefense + other.phyDefense,
                this.magDefense + other.magDefense,
                this.maxHP + other.maxHP,
                this.hpRegen + other.hpRegen,
                this.apBuffEfficiency + other.apBuffEfficiency,
                this.shieldEnhance + other.shieldEnhance,
                this.healingEnhance + other.healingEnhance);
    }
}
