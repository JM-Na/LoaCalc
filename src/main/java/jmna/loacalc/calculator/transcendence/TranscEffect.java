package jmna.loacalc.calculator.transcendence;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class TranscEffect {
    private String armoryType;

    private int mainStat;
    private int weaponPower;
    private int atkPower;
    private List<Double> outgoingDmg = new ArrayList<>();

    private int maxHP;
    private int phyDefense;
    private int magDefense;
    private int vitality;
    private int dmgReduction;

    private double apBuffEfficiency;
    private int brandPower;

    private int successorStrength;

    public TranscEffect(String armoryType, int mainStat, int weaponPower, int atkPower, List<Double> outgoingDmg, int maxHP, int phyDefense, int magDefense, int vitality, int dmgReduction, double apBuffEfficiency, int brandPower, int successorStrength) {
        this.armoryType = armoryType;
        this.mainStat = mainStat;
        this.weaponPower = weaponPower;
        this.atkPower = atkPower;
        this.outgoingDmg = outgoingDmg;
        this.maxHP = maxHP;
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
    public void addAtkPower(int increment) {
        this.atkPower += increment;
    }
    public void addOutgoingDmg(double increment) {
        this.outgoingDmg.add(increment);
    }
    public void addHp(int increment) {
        this.maxHP += increment;
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

    public TranscEffect merge(TranscEffect other) {
        List<Double> newOutgoingDmg = this.outgoingDmg;
        newOutgoingDmg.addAll(other.outgoingDmg);
        return new TranscEffect(
                "SUM",
                this.mainStat + other.mainStat,
                this.weaponPower + other.weaponPower,
                this.atkPower + other.atkPower,
                newOutgoingDmg,
                this.maxHP + other.maxHP,
                this.phyDefense + other.phyDefense,
                this.magDefense + other.magDefense,
                this.vitality + other.vitality,
                this.dmgReduction + other.dmgReduction,
                this.apBuffEfficiency + other.apBuffEfficiency,
                this.brandPower + other.brandPower,
                this.successorStrength + other.successorStrength);
    }
}
