package jmna.loacalc.calculator.transcendence;

import lombok.Data;

@Data
public class TranscendenceEffect {
    private String armoryType;

    private int mainStat;
    private int weaponPower;
    private int attackPower;
    private double outgoingDmg;

    private int hp;
    private int phyDefense;
    private int magDefense;
    private int vitality;
    private int dmgReduction;

    private double apBuffEfficiency;
    private int brandPower;

    private int successorStrength;

    public void addMainStat(int increment) {
        this.mainStat += increment;
    }
    public void addWeaponPower(int increment) {
        this.weaponPower += increment;
    }
    public void addAttackPower(int increment) {
        this.attackPower += increment;
    }
    public void addOutgoingDmg(double increment) {
        this.outgoingDmg += increment;
    }
    public void addHp(int increment) {
        this.hp += increment;
    }
    public void addPhyDefense(int increment) {
        this.phyDefense += increment;
    }
    public void addMagDefense(int increment) {
        this.magDefense += increment;
    }
    public void addVitality(int increment) {
        this.vitality += increment;
    }
    public void addDmgReduction(int increment) {
        this.dmgReduction += increment;
    }
    public void addApBuffEfficiency(double increment) {
        this.apBuffEfficiency += increment;
    }
    public void addBrandPower(int increment) {
        this.brandPower += increment;
    }
    public void addSuccessorStrength(int increment) {
        this.successorStrength += increment;
    }
}
