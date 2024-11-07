package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.transcendence.TranscendenceEffect;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class TotalEffect {

    private int attackPower;
    private int weaponPower;
    private int mainStat;
    private double atkPowerPercent;
    private double addDmg;
    private double critDmg;
    private List<Double> outgoingDmg;
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
    private double brandPower; //낙인력
    private int successorStrength;
    // 엘릭서 세트 효과
    private int outgoingDmgWhenCrit;
    private double critRate;
    private double cooldownReduction;
    private int advanceEtherWeaponPower;
    
    // 각인 효과
    private double awakeningCooldown;
    private int awakeningCast;
    private double addBackHeadDmg;
    private double masterTenacity;
    private double raidCaptain;
    private double mpRecovery;
    private double mpEfficiency;
    private double holdCastSpeed;
    private double chargeSpeed;
    private double defensePercent;
    private Boolean keenBluntWeapon = false;
    private Boolean grudge;
    private Boolean cursedDoll;
    private double atkSpeed;
    private double movementSpeed;
    private Boolean heavyArmor;
    private Boolean hitMaster;
    // 악세서리 효과
    private int maxMP;
    private double statusEffectDuration;
    private double supIdentityGain;
    private double dmgBuffEfficiency;
    private double weaponPowerPercent;

    public void merge(ArmoryEffect armoryEffect, ElixirEffect elixirEffect, TranscendenceEffect transcEffect, EngravingEffect engravingEffect, AccessoryEffect accessoryEffect) {
        this.attackPower = elixirEffect.getAttackPower() + transcEffect.getAttackPower() + accessoryEffect.getAtkPower();
        this.weaponPower = armoryEffect.getWeaponPower() + elixirEffect.getWeaponPower() + transcEffect.getWeaponPower() + accessoryEffect.getWeaponPower();
        this.mainStat = armoryEffect.getMainStat() + elixirEffect.getMainStat() + transcEffect.getMainStat() + accessoryEffect.getMainStat();
        this.atkPowerPercent = elixirEffect.getAtkPowerPercent() + engravingEffect.getAtkPowerPercent() + accessoryEffect.getAtkPowerPercent();
        this.addDmg = armoryEffect.getAddDmg() + elixirEffect.getAddDmg() + accessoryEffect.getAddDmg();
        this.critDmg = elixirEffect.getCritDmg() + engravingEffect.getCritDmg();
        this.outgoingDmg.addAll(elixirEffect.getOutgoingDmg());
        this.outgoingDmg.addAll(transcEffect.getOutgoingDmg());
        this.outgoingDmg.addAll(engravingEffect.getOutgoingDmg());
        this.outgoingDmg.add(accessoryEffect.getOutgoingDmg());
        this.dmgReduction = elixirEffect.getDmgReduction() + transcEffect.getDmgReduction();
        this.phyDefense = armoryEffect.getPhyDefense() + elixirEffect.getPhyDefense();
        this.magDefense = armoryEffect.getMagDefense() + elixirEffect.getMagDefense();
        this.maxHP = elixirEffect.getMaxHP() + transcEffect.getMaxHP() + accessoryEffect.getMaxHP();
        this.hpRecovery = elixirEffect.getHpRecovery() + accessoryEffect.getHpRecovery();
        this.shieldEnhance = elixirEffect.getShieldEnhance() + engravingEffect.getHealShieldEfficiency() + accessoryEffect.getShieldEnhance();
        this.healingEnhance = elixirEffect.getHealingEnhance() + engravingEffect.getHealShieldEfficiency() + accessoryEffect.getHealingEnhance();
        this.vitality = armoryEffect.getVitality() + transcEffect.getVitality() + accessoryEffect.getVitality();
        this.vigor = armoryEffect.getVigor();
        this.apBuffEfficiency = elixirEffect.getApBuffEfficiency() + transcEffect.getApBuffEfficiency() + accessoryEffect.getApBuffEfficiency();
        this.brandPower = transcEffect.getBrandPower() + accessoryEffect.getBrandPower();
        this.successorStrength = transcEffect.getSuccessorStrength();
        this.outgoingDmgWhenCrit = elixirEffect.getOutgoingDmgWhenCrit();
        this.critRate = elixirEffect.getCritRate() + engravingEffect.getCritRate() + accessoryEffect.getCritRate();
        this.cooldownReduction = elixirEffect.getCooldownReduction() + engravingEffect.getCooldownReduction();
        this.advanceEtherWeaponPower = elixirEffect.getAdvanceEtherWeaponPower();
        this.awakeningCooldown = engravingEffect.getAwakeningCooldown();
        this.awakeningCast = engravingEffect.getAwakeningCast();
        this.masterTenacity = engravingEffect.getMasterTenacity();
        this.raidCaptain = engravingEffect.getRaidCaptain();
        this.mpRecovery = engravingEffect.getMpRecovery();
        this.mpEfficiency = engravingEffect.getMpEfficiency();
        this.holdCastSpeed = engravingEffect.getHoldCastSpeed();
        this.chargeSpeed = engravingEffect.getChargeSpeed();
        this.defensePercent = engravingEffect.getDefensePercent();
        this.keenBluntWeapon = engravingEffect.getKeenBluntWeapon();
        this.grudge = engravingEffect.getGrudge();
        this.cursedDoll = engravingEffect.getCursedDoll();
        this.atkSpeed = engravingEffect.getAtkSpeed();
        this.movementSpeed = engravingEffect.getMovementSpeed();
        this.heavyArmor = engravingEffect.getHeavyArmor();
        this.hitMaster = engravingEffect.getHitMaster();
        this.maxMP = accessoryEffect.getMaxMP();
        this.statusEffectDuration = accessoryEffect.getStatusEffectDuration();
        this.supIdentityGain = accessoryEffect.getSupIdentityGain();
        this.weaponPowerPercent = accessoryEffect.getWeaponPowerPercent();
        this.dmgBuffEfficiency = accessoryEffect.getDmgBuffEfficiency();
    }
}
