package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TotalEffect {

    private int attackPower;
    private int weaponPower;
    private int mainStat;
    private double atkPowerPercent;
    private double addDmg;
    private double critDmg;
    private double outgoingDmg;
    private double dmgReduction;
    private int phyDefense;
    private int magDefense;
    private int maxHP; // 최대 생명력
    private int hpRecovery;
    private double shieldEnhance;
    private double healingEnhance;
    private int vitality; // 체력
    private int vigor; // 생명 활성력
    private double apBuffEfficiency;
    private int brandPower; //낙인력
    private int successorStrength;
    // 엘릭서 세트 효과
    private int outgoingDmgWhenCrit;
    private int critRate;
    private int cooldownReduction;
    private int advanceEtherWeaponPower;

    public void merge(ArmoryEffect armoryEffect, ElixirEffect elixirEffect, TranscendenceEffect transcEffect) {
        this.attackPower = elixirEffect.getAttackPower() + transcEffect.getAttackPower();
        this.weaponPower = armoryEffect.getWeaponPower() + elixirEffect.getWeaponPower() + transcEffect.getWeaponPower();
        this.mainStat = armoryEffect.getMainStat() + elixirEffect.getMainStat() + transcEffect.getMainStat();
        this.atkPowerPercent = elixirEffect.getAtkPowerPercent();
        this.addDmg = armoryEffect.getAddDmg() + elixirEffect.getAddDmg();
        this.critDmg = elixirEffect.getCritDmg();
        this.outgoingDmg = elixirEffect.getOutgoingDmg() + transcEffect.getOutgoingDmg();
        this.dmgReduction = elixirEffect.getDmgReduction() + transcEffect.getDmgReduction();
        this.phyDefense = armoryEffect.getPhyDefense() + elixirEffect.getPhyDefense();
        this.magDefense = armoryEffect.getMagDefense() + elixirEffect.getMagDefense();
        this.maxHP = elixirEffect.getMaxHP() + transcEffect.getMaxHP();
        this.hpRecovery = elixirEffect.getHpRecovery();
        this.shieldEnhance = elixirEffect.getShieldEnhance();
        this.healingEnhance = elixirEffect.getHealingEnhance();
        this.vitality = armoryEffect.getVitality() + transcEffect.getVitality();
        this.vigor = armoryEffect.getVigor();
        this.apBuffEfficiency = elixirEffect.getApBuffEfficiency() + transcEffect.getApBuffEfficiency();
        this.brandPower = transcEffect.getBrandPower();
        this.successorStrength = transcEffect.getSuccessorStrength();
        this.outgoingDmgWhenCrit = elixirEffect.getOutgoingDmgWhenCrit();
        this.critRate = elixirEffect.getCritRate();
        this.cooldownReduction = elixirEffect.getCooldownReduction();
        this.advanceEtherWeaponPower = elixirEffect.getAdvanceEtherWeaponPower();
    }
}
