package jmna.loacalc.calculator;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class EngravingEffect {

    public EngravingEffect(double awakeningCooldown, int awakeningCast, List<Double> outgoingDmg, double addBackHeadDmg, double masterTenacity, double raidCaptain, double mpRecovery, double cooldownReduction, double mpEfficiency, double holdCastSpeed, double chargeSpeed, double atkPowerPercent, double critRate, double defensePercent, double critDmg, Boolean keenBluntWeapon, Boolean grudge, Boolean cursedDoll, double healShieldEfficiency, double atkSpeed, double movementSpeed, Boolean heavyArmor, Boolean hitMaster) {
        this.awakeningCooldown = awakeningCooldown;
        this.awakeningCast = awakeningCast;
        this.outgoingDmg = outgoingDmg;
        this.addBackHeadDmg = addBackHeadDmg;
        this.masterTenacity = masterTenacity;
        this.raidCaptain = raidCaptain;
        this.mpRecovery = mpRecovery;
        this.cooldownReduction = cooldownReduction;
        this.mpEfficiency = mpEfficiency;
        this.holdCastSpeed = holdCastSpeed;
        this.chargeSpeed = chargeSpeed;
        this.atkPowerPercent = atkPowerPercent;
        this.critRate = critRate;
        this.defensePercent = defensePercent;
        this.critDmg = critDmg;
        this.keenBluntWeapon = keenBluntWeapon;
        this.grudge = grudge;
        this.cursedDoll = cursedDoll;
        this.healShieldEfficiency = healShieldEfficiency;
        this.atkSpeed = atkSpeed;
        this.movementSpeed = movementSpeed;
        this.heavyArmor = heavyArmor;
        this.hitMaster = hitMaster;
    }

    private double awakeningCooldown;
    private int awakeningCast;

    // 적추피는 곱연산이기 때문에 리스트로 받아온다.
    private List<Double> outgoingDmg = new ArrayList<>();
    private double addBackHeadDmg;

    // 달인의 저력
    private double masterTenacity;
    // 돌격대장
    private double raidCaptain;

    private double mpRecovery;
    private double cooldownReduction;

    // 마효증
    private double mpEfficiency;
    private double holdCastSpeed;
    private double chargeSpeed;

    private double atkPowerPercent;
    private double critRate;
    private double defensePercent;
    private double critDmg;
    private Boolean keenBluntWeapon = false;
    private Boolean grudge = false;
    private Boolean cursedDoll = false;
    private double healShieldEfficiency;

    private double atkSpeed;
    private double movementSpeed;
    private Boolean heavyArmor = false;
    private Boolean hitMaster = false;

    public void addOutgoingDmg(double increment) {
        this.outgoingDmg.add(increment);
    }

    public void addAtkPowerPercent(double increment) {
        this.atkPowerPercent += increment;
    }

    public void addCritRate(double increment) {
        this.critRate += critRate;
    }

    public void addDefensePercent(double increment) {
        this.defensePercent += increment;
    }

    public void addCritDmg(double increment) {
        this.critDmg += increment;
    }

    public void addAtkSpeed(double increment) {
        this.atkSpeed += increment;
    }

    public void addMovementSpeed(double increment) {
        this.movementSpeed += increment;
    }

    public EngravingEffect merge(EngravingEffect other) {
        List<Double> newOutgoingDmg = this.outgoingDmg;
        newOutgoingDmg.addAll(other.outgoingDmg);
        return new EngravingEffect(
                this.awakeningCooldown += other.awakeningCooldown,
                this.awakeningCast += other.awakeningCast,
                outgoingDmg,
                this.addBackHeadDmg += other.addBackHeadDmg,
                this.masterTenacity += other.masterTenacity,
                this.raidCaptain += other.raidCaptain,
                this.mpRecovery += other.mpRecovery,
                this.cooldownReduction += other.cooldownReduction,
                this.mpEfficiency += other.mpEfficiency,
                this.holdCastSpeed += other.holdCastSpeed,
                this.chargeSpeed += other.chargeSpeed,
                this.atkPowerPercent += other.atkPowerPercent,
                this.critRate += other.critRate,
                this.defensePercent += other.defensePercent,
                this.critDmg += other.critDmg,
                this.keenBluntWeapon || other.keenBluntWeapon,
                this.grudge || other.grudge,
                this.cursedDoll || other.cursedDoll,
                this.healShieldEfficiency += other.healShieldEfficiency,
                this.atkSpeed += other.atkSpeed,
                this.movementSpeed += other.movementSpeed,
                this.heavyArmor || other.heavyArmor,
                this.hitMaster || other.hitMaster
        );
    }
}
