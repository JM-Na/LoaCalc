package jmna.loacalc.calculator.subequipments;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BraceletEffect {
    public BraceletEffect(int mainStat, int crit, int swift, int specialize, List<Double> outgoingDmg, double critDmg, double backDmg, double headDmg, double critRate, double identityGain, double addDmg, int weaponPower, int vitality, List<Double> outgoingDmgWhenCrit, List<Double> staggerDmg, List<Double> daemonDmg, List<Double> cooldown, double speed, double hitDmg, double armorReductionSynergy, double critRateSynergy, double critDmgSynergy, double outgoingDmgSynergy, double shieldHeal, double apBuff, double dmgBuff) {
        this.mainStat = mainStat;
        this.crit = crit;
        this.swift = swift;
        this.specialize = specialize;
        this.outgoingDmg = outgoingDmg;
        this.critDmg = critDmg;
        this.backDmg = backDmg;
        this.headDmg = headDmg;
        this.critRate = critRate;
        this.identityGain = identityGain;
        this.addDmg = addDmg;
        this.weaponPower = weaponPower;
        this.vitality = vitality;
        this.outgoingDmgWhenCrit = outgoingDmgWhenCrit;
        this.staggerDmg = staggerDmg;
        this.daemonDmg = daemonDmg;
        this.cooldown = cooldown;
        this.speed = speed;
        this.hitDmg = hitDmg;
        this.armorReductionSynergy = armorReductionSynergy;
        this.critRateSynergy = critRateSynergy;
        this.critDmgSynergy = critDmgSynergy;
        this.outgoingDmgSynergy = outgoingDmgSynergy;
        this.shieldHeal = shieldHeal;
        this.apBuff = apBuff;
        this.dmgBuff = dmgBuff;
    }

    private int mainStat;
    private int crit;
    private int swift;
    private int specialize;

    private List<Double> outgoingDmg = new ArrayList<>();
    private double critDmg;
    private double backDmg;
    private double headDmg;
    private double critRate;
    private double identityGain;
    private double addDmg;
    private int weaponPower;
    private int vitality;  //체력

    private List<Double> outgoingDmgWhenCrit = new ArrayList<>();
    private List<Double> staggerDmg = new ArrayList<>();
    private List<Double> daemonDmg = new ArrayList<>();
    private List<Double> cooldown = new ArrayList<>();
    private double speed;
    private double hitDmg;

    private double armorReductionSynergy;
    private double critRateSynergy;
    private double critDmgSynergy;
    private double outgoingDmgSynergy;
    private double shieldHeal;
    private double apBuff;
    private double dmgBuff;

    public void addOutgoingDmg(double increment) {
        this.outgoingDmg.add(increment);
    }

    public void addCritDmg(double increment) {
        this.critDmg += increment;
    }

    public void addCrtRate(double increment) {
        this.critRate += increment;
    }

    public void addWeaponPower(int increment) {
        this.weaponPower += increment;
    }

    public void addOutgoingDmgWhenCrit(double increment) {
        this.outgoingDmgWhenCrit.add(increment);
    }

    public void addStaggerDmg(double increment) {
        this.staggerDmg.add(increment);
    }

    public void addAddDmg(double increment) {
        this.addDmg += increment;
    }
    public void addDaemonDmg(double increment) {
        this.daemonDmg.add(increment);
    }
    public void addCooldown(double increment) {
        this.cooldown.add(increment);
    }
    public void addSpeed(double increment) {
        this.speed += increment;
    }

    public void addShieldHeal(double increment) {
        this.shieldHeal += increment;
    }
    public void addApBuff(double increment) {
        this.apBuff += increment;
    }
    public void addDmgBuff(double increment) {
        this.dmgBuff += increment;
    }

    public BraceletEffect merge(BraceletEffect other) {
        List<Double> newOutgoingDmg = this.outgoingDmg;
        newOutgoingDmg.addAll(other.outgoingDmg);

        List<Double> newOutgoingDmgWhenCrit = this.outgoingDmgWhenCrit;
        newOutgoingDmgWhenCrit.addAll(other.outgoingDmgWhenCrit);

        List<Double> newStaggerDmg = this.staggerDmg;
        newStaggerDmg.addAll(other.staggerDmg);

        List<Double> newDaemonDmg = this.daemonDmg;
        newDaemonDmg.addAll(other.daemonDmg);

        List<Double> newCooldown = this.cooldown;
        newCooldown.addAll(other.cooldown);
        return new BraceletEffect(
                this.crit + other.crit,
                this.mainStat + other.mainStat,
                this.swift + other.swift,
                this.specialize + other.specialize,
                newOutgoingDmg,
                this.critDmg + other.critDmg,
                this.backDmg + other.backDmg,
                this.headDmg + other.headDmg,
                this.critRate + other.critRate,
                this.identityGain + other.identityGain,
                this.addDmg + other.addDmg,
                this.weaponPower + other.weaponPower,
                this.vitality + other.vitality,  //체력
                newOutgoingDmgWhenCrit,
                newStaggerDmg,
                newDaemonDmg,
                newCooldown,
                this.speed + other.speed,
                this.hitDmg + other.hitDmg,
                this.armorReductionSynergy + other.armorReductionSynergy,
                this.critRateSynergy + other.critRateSynergy,
                this.critDmgSynergy + other.critDmgSynergy,
                this.outgoingDmgSynergy + other.outgoingDmgSynergy,
                this.shieldHeal + other.shieldHeal,
                this.apBuff + other.apBuff,
                this.dmgBuff + other.dmgBuff
        );
    }
}
