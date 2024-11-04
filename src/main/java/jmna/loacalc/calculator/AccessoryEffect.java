package jmna.loacalc.calculator;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccessoryEffect {
    public AccessoryEffect(int mainStat, int vitality, int maxHP, int maxMP, int atkPower, int weaponPower, int hpRecovery, double statusEffectDuration, double outgoingDmg, double addDmg, double brandPower, double supIdentitiyGain, double atkPowerPercent, double weaponPowerPercent, double shieldEnhance, double healingEnhance, double critRate, double critDmg, double apBuffEfficiency, double dmgBuffEfficiency) {
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
        this.supIdentitiyGain = supIdentitiyGain;
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
    private double outgoingDmg;
    private double addDmg;
    private double brandPower; // 낙인력
    private double supIdentitiyGain; // 서폿 아덴 획득
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

    public AccessoryEffect merge(AccessoryEffect other) {
        return new AccessoryEffect(
                this.mainStat += other.mainStat,
                this.vitality += other.vitality,
                this.maxHP += other.maxHP,
                this.maxMP += other.maxMP,
                this.atkPower += other.atkPower,
                this.weaponPower += other.weaponPower,
                this.hpRecovery += other.hpRecovery,
                this.statusEffectDuration += other.statusEffectDuration,
                this.outgoingDmg += other.outgoingDmg,
                this.addDmg += other.addDmg,
                this.brandPower += other.brandPower,
                this.supIdentitiyGain += other.supIdentitiyGain,
                this.atkPowerPercent += other.atkPowerPercent,
                this.weaponPowerPercent += other.weaponPowerPercent,
                this.shieldEnhance += other.shieldEnhance,
                this.healingEnhance += other.healingEnhance,
                this.critRate += other.critRate,
                this.critDmg += other.critDmg,
                this.apBuffEfficiency += other.apBuffEfficiency,
                this.dmgBuffEfficiency += other.dmgBuffEfficiency);
    }
}
