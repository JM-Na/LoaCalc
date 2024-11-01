package jmna.loacalc.calculator.transcendence;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
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

    public TranscendenceEffect(String armoryType, int mainStat, int weaponPower, int attackPower, double outgoingDmg, int hp, int phyDefense, int magDefense, int vitality, int dmgReduction, double apBuffEfficiency, int brandPower, int successorStrength) {
        this.armoryType = armoryType;
        this.mainStat = mainStat;
        this.weaponPower = weaponPower;
        this.attackPower = attackPower;
        this.outgoingDmg = outgoingDmg;
        this.hp = hp;
        this.phyDefense = phyDefense;
        this.magDefense = magDefense;
        this.vitality = vitality;
        this.dmgReduction = dmgReduction;
        this.apBuffEfficiency = apBuffEfficiency;
        this.brandPower = brandPower;
        this.successorStrength = successorStrength;
    }

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

    public TranscendenceEffect merge(TranscendenceEffect other) {
        return new TranscendenceEffect(
                "SUM",
                this.mainStat + other.mainStat,
                this.weaponPower + other.weaponPower,
                this.attackPower + other.attackPower,
                this.outgoingDmg + other.outgoingDmg,
                this.hp + other.hp,
                this.phyDefense + other.phyDefense,
                this.magDefense + other.magDefense,
                this.vitality + other.vitality,
                this.dmgReduction + other.dmgReduction,
                this.apBuffEfficiency + other.apBuffEfficiency,
                this.brandPower + other.brandPower,
                this.successorStrength + other.successorStrength);
    }
}
