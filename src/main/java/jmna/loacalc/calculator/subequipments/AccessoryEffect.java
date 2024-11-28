package jmna.loacalc.calculator.subequipments;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class AccessoryEffect {
    public AccessoryEffect(int mainStat, int vitality, int maxHP, int maxMP, int atkPower, int weaponPower, int hpRecovery, double statusEffectDuration, List<Double> outgoingDmg, double addDmg, double brandPower, double supIdentityGain, double atkPowerPercent, double weaponPowerPercent, double shieldEnhance, double healingEnhance, double critRate, double critDmg, double apBuffEfficiency, double dmgBuffEfficiency) {
        this.mainStat = mainStat;
        this.vitality = vitality;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.atkPower = atkPower;
        this.weaponPower = weaponPower;
        this.hpRecovery = hpRecovery;
        this.statusEffectDuration = statusEffectDuration;
        this.outgoingDmg = outgoingDmg;
        this.addDmg = addDmg;
        this.brandPower = brandPower;
        this.supIdentityGain = supIdentityGain;
        this.atkPowerPercent = atkPowerPercent;
        this.weaponPowerPercent = weaponPowerPercent;
        this.shieldEnhance = shieldEnhance;
        this.healingEnhance = healingEnhance;
        this.critRate = critRate;
        this.critDmg = critDmg;
        this.apBuffEfficiency = apBuffEfficiency;
        this.dmgBuffEfficiency = dmgBuffEfficiency;
    }

    private int mainStat;
    private int vitality; // 체력
    // 공용 옵션
    private int maxHP;
    private int maxMP;
    private int atkPower;
    private int weaponPower;
    private int hpRecovery;
    private double statusEffectDuration;
    // 목걸이
    private List<Double> outgoingDmg = new ArrayList<>();
    private double addDmg;
    private double brandPower; // 낙인력
    private double supIdentityGain; // 서폿 아덴 획득
    // 귀걸이
    private double atkPowerPercent;
    private double weaponPowerPercent;
    private double shieldEnhance;
    private double healingEnhance;
    // 반지
    private double critRate;
    private double critDmg;
    private double apBuffEfficiency;
    private double dmgBuffEfficiency;

    //특성
    private int crit;
    private int swift;
    private int specialize;
    private double backDmg;
    private double headDmg;

    private double identityGain;
    private List<Double> outgoingDmgWhenCrit = new ArrayList<>();
    private List<Double> staggerDmg = new ArrayList<>();
    private List<Double> daemonDmg = new ArrayList<>();
    private List<Double> cooldownReduction = new ArrayList<>();
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

    public void addAtkPowerPercent(double increment) {
        this.atkPowerPercent += increment;
    }

    public void addWeaponPowerPercent(double increment) {
        this.weaponPowerPercent += increment;
    }

    public void addCritRate(double increment) {
        this.critRate += increment;
    }

    public void addCritDmg(double increment) {
        this.critDmg += increment;
    }
    public void addShieldEnhance(double increment) {
        this.shieldEnhance += increment;
    }
    public void addHealingEnhance(double increment) {
        this.healingEnhance += increment;
    }

    public void addApBuffEfficiency(double increment) {
        this.apBuffEfficiency += increment;
    }

    public void addDmgBuffEfficiency(double increment) {
        this.dmgBuffEfficiency += increment;
    }

    public void addAtkPower(double increment) {
        this.atkPower += increment;
    }
    public void addWeaponPower(double increment) {
        this.weaponPower += increment;
    }
    public void addMaxHP(double increment) {
        this.maxHP += increment;
    }
    public void addMaxMP(double increment) {
        this.maxMP += increment;
    }
    public void addHpRecovery(double increment) {
        this.hpRecovery += increment;
    }


    public AccessoryEffect merge(AccessoryEffect other) {
        List<Double> newOutgoingDmg = this.outgoingDmg;
        newOutgoingDmg.addAll(other.outgoingDmg);
        return new AccessoryEffect(
                this.mainStat += other.mainStat,
                this.vitality += other.vitality,
                this.maxHP += other.maxHP,
                this.maxMP += other.maxMP,
                this.atkPower += other.atkPower,
                this.weaponPower += other.weaponPower,
                this.hpRecovery += other.hpRecovery,
                this.statusEffectDuration += other.statusEffectDuration,
                newOutgoingDmg,
                this.addDmg += other.addDmg,
                this.brandPower += other.brandPower,
                this.supIdentityGain += other.supIdentityGain,
                this.atkPowerPercent += other.atkPowerPercent,
                this.weaponPowerPercent += other.weaponPowerPercent,
                this.shieldEnhance += other.shieldEnhance,
                this.healingEnhance += other.healingEnhance,
                this.critRate += other.critRate,
                this.critDmg += other.critDmg,
                this.apBuffEfficiency += other.apBuffEfficiency,
                this.dmgBuffEfficiency += other.dmgBuffEfficiency);
    }

    public void addBracelet(BraceletEffect other) {
        this.mainStat += other.getMainStat();
        this.crit += other.getCrit();
        this.swift += other.getSwift();
        this.specialize += other.getSpecialize();
        this.outgoingDmg.addAll(other.getOutgoingDmg());
        this.critDmg += other.getCritDmg();
        this.backDmg += other.getBackDmg();
        this.headDmg += other.getHeadDmg();
        this.critRate += other.getCritRate();
        this.identityGain += other.getIdentityGain();
        this.addDmg += other.getAddDmg();
        this.weaponPower += other.getWeaponPower();
        this.vitality += other.getVitality();
        this.outgoingDmgWhenCrit.addAll(other.getOutgoingDmgWhenCrit());
        this.staggerDmg.addAll(other.getStaggerDmg());
        this.daemonDmg.addAll(other.getDaemonDmg());
        this.cooldownReduction.addAll(other.getCooldown());
        this.speed += other.getSpeed();
        this.hitDmg += other.getHitDmg();
        this.armorReductionSynergy += other.getArmorReductionSynergy();
        this.critRateSynergy += other.getCritRateSynergy();
        this.critDmgSynergy += other.getCritDmgSynergy();
        this.outgoingDmgSynergy += other.getOutgoingDmgSynergy();
        this.shieldHeal += other.getShieldHeal();
        this.apBuff += other.getApBuff();
        this.dmgBuff += other.getDmgBuff();
    }
}
