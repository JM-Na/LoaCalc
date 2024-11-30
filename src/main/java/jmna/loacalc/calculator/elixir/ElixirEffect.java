package jmna.loacalc.calculator.elixir;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ElixirEffect {
    private String armoryType;
    private String setEffect;
    private int totalLvl;

    private int atkPower;
    private int weaponPower;
    private int mainStat;

    private double atkPowerPercent;

    private double addDmg;
    private double critDmg;
    private List<Double> outgoingDmg = new ArrayList<>();

    private double dmgReduction;
    private int phyDefense;
    private int magDefense;
    private int maxHP;
    private int hpRecovery;

    private double apBuffEfficiency;
    private double shieldEnhance;
    private double healingEnhance;
    // 엘릭서 세트 효과
    private int outgoingDmgWhenCrit;
    private int critRate;
    private double cooldownReduction;
    private int advanceEtherWeaponPower;

    public ElixirEffect(String armoryType, String setEffect, int atkPower, int weaponPower, int mainStat, double atkPowerPercent, double addDmg, double critDmg, List<Double> outgoingDmg, double dmgReduction, int phyDefense, int magDefense, int maxHP, int hpRecovery, double apBuffEfficiency, double shieldEnhance, double healingEnhance) {
        this.armoryType = armoryType;
        this.setEffect = setEffect;
        this.atkPower = atkPower;
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
        this.hpRecovery = hpRecovery;
        this.apBuffEfficiency = apBuffEfficiency;
        this.shieldEnhance = shieldEnhance;
        this.healingEnhance = healingEnhance;
    }

    public void addAtkPower(int increment) {
        this.atkPower += increment;
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
        this.outgoingDmg.add(increment);
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
        this.hpRecovery += increment;
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
    public void addOutGoingDmgWhenCrit(int increment) {
        this.outgoingDmgWhenCrit += increment;
    }
    public void addCritRate(int increment) {
        this.critRate += increment;
    }
    public void addCoolDownReduction(int increment) {
        this.cooldownReduction += increment;
    }
    public void addAdvanceEtherWeaponPower(int increment) {
        this.advanceEtherWeaponPower += increment;
    }

    public ElixirEffect merge(ElixirEffect other) {
        List<Double> newOutgoingDmg = this.outgoingDmg;
        newOutgoingDmg.addAll(other.outgoingDmg);
        String set = null;
        if (this.setEffect == null) {
            set = other.setEffect;
        } else if (other.setEffect == null) {
            set = this.setEffect;
        } else {
            String thisEffectPrefix = this.setEffect.split(" ")[0];
            String otherEffectPrefix = other.setEffect.split(" ")[0];
            if (thisEffectPrefix.equals(otherEffectPrefix)) {
                set = thisEffectPrefix;
            }
        }
        return new ElixirEffect(
                "SUM",
                set,
                this.atkPower + other.atkPower,
                this.weaponPower + other.weaponPower,
                this.mainStat + other.mainStat,
                this.atkPowerPercent + other.atkPowerPercent,
                this.addDmg + other.addDmg,
                this.critDmg + other.critDmg,
                newOutgoingDmg,
                this.dmgReduction + other.dmgReduction,
                this.phyDefense + other.phyDefense,
                this.magDefense + other.magDefense,
                this.maxHP + other.maxHP,
                this.hpRecovery + other.hpRecovery,
                this.apBuffEfficiency + other.apBuffEfficiency,
                this.shieldEnhance + other.shieldEnhance,
                this.healingEnhance + other.healingEnhance);
    }
}
