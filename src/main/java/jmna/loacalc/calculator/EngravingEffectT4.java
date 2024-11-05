package jmna.loacalc.calculator;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class EngravingEffectT4 {

    private int awakeningCooldown;
    private int awakeningCast;

    // 적추피는 곱연산이기 때문에 리스트로 받아온다.
    private List<Double> outgoingDmg = new ArrayList<>();
    private int addBackHeadDmg;

    // 달인의 저력
    private double masterTenacity;
    // 돌격대장
    private double raidCaptain;

    private double mpRecovery;
    private double cooldown;

    // 마효증
    private double mpEfficiency;
    private double holdCastSpeed;
    private double chargeSpeed;

    private double atkPowerPercent;
    private int critRate;
    private double defensePercent;
    private int critDmg;
    private Boolean keenBluntWeapon;
    private Boolean grudge;
    private Boolean cursedDoll;
    private double healShieldEfficiency;

    private int atkSpeed;
    private int movementSpeed;
    private Boolean heavyArmor;
    private Boolean hitMaster;
    public void addOutgoingDmg(double increment) {
        this.outgoingDmg.add(increment);
    }

    public void addAtkPowerPercent(double increment) {
        this.atkPowerPercent += increment;
    }

    public void addCritRate(int increment) {
        this.critRate += critRate;
    }

    public void addDefensePercent(double increment) {
        this.defensePercent += increment;
    }

    public void addCritDmg(int increment) {
        this.critDmg += increment;
    }

    public void addAtkSpeed(int increment) {
        this.atkSpeed += increment;
    }

    public void addMovementSpeed(int increment) {
        this.movementSpeed += increment;
    }
}
