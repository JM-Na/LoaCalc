package jmna.loacalc.calculator.subequipments;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BraceletEffect {
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
}
