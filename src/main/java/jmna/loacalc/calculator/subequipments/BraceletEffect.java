package jmna.loacalc.calculator.subequipments;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class BraceletEffect {
    private int mainStat;
    private int crit;
    private int swift;
    private int specialize;

    private List<Double> outgoingDmg;
    private double critDmg;
    private double backDmg;
    private double headDmg;
    private double critRate;
    private double identityGain;
    private double defReduction;
    private double addDmg;
    private int weaponPower;
    private int vitality;  //체력
    public void addOutgoingDmg(double increment) {
        this.outgoingDmg.add(increment);
    }

    public void addCritDmg(double increment) {
        this.crit += increment;
    }

    public void addCrtRate(double increment) {
        this.critRate += increment;
    }

    public void addWeaponPower(int increment) {
        this.weaponPower += increment;
    }
}
