package jmna.loacalc.calculator.arkpassive.enlightenment;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArkpassiveEnlightenmentEffect {

    private double critRate;
    private int critDmg;
    private List<Double> outGoingDmg = new ArrayList<>();
    private int mpRecovery;
    private int defenseStateShield; // 방어태세 보호막
    private double atkSpeed;
    private double moveSpeed;

    public void addCritRate(double increment) {
        this.critRate += increment;
    }
    public void addCritDmg(int increment) {
        this.critDmg += increment;
    }
    public void addOutgoingDmg(double increment) {
        this.outGoingDmg.add(increment);
    }
    public void removeOutgoingDmg(double increment) {
        this.outGoingDmg.remove(increment);
    }
    public void addMpRecovery(int increment) {
        this.mpRecovery += increment;
    }
    public void addDefenseStateShield(int increment) {
        this.defenseStateShield += increment;
    }
    public void addAtkSpeed(double increment) {
        this.atkSpeed += increment;
    }
    public void addMoveSpeed(double increment) {
        this.moveSpeed += increment;
    }
}
