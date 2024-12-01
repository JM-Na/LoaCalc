package jmna.loacalc.calculator;

import jmna.loacalc.calculator.elixir.ElixirEffect;
import jmna.loacalc.calculator.engraving.EngravingEffect;
import jmna.loacalc.calculator.subequipments.AccessoryEffect;
import jmna.loacalc.calculator.transcendence.TranscEffect;
import jmna.loacalc.processor.armory.avatar.CharacterAvatar;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class TotalArmoryEffect {

    private int atkPower; // 공격력
    private int weaponPower; // 무기 공격력
    private int mainStat; // 힘, 민첩, 지능
    private double atkPowerPercent; // 공격력 %
    private double weaponPowerPercent; // 무기공격력 %

    private double critRate; // 치명타 확률

    private double addDmg; // 추가 피해
    private double critDmg; // 치명타 피해
    private List<Double> outgoingDmg = new ArrayList<>(); // 적에게 주는 피해
    private double addBackHeadDmg; // 헤드어택, 백에택 시 주는 피해 증가
    private List<Double> outgoingDmgWhenCrit = new ArrayList<>(); // 치명타 시 적에게 주는 피해 증가

    private int maxHP; // 최대 생명력
    private int vitality; // 체력
    private int vigor; // 생명 활성력
    private int hpRecovery; // 전투 중 생명력 회복
    private double dmgReduction; // 받는 피해 감소
    private double defensePercent; // 방어력 증가 %
    private int phyDefense; // 물리 방어력
    private int magDefense; // 마법 방어력
    private int maxMP; // 최대 마나 증가
    private double mpRecovery; // 마나 회복 속도 증가

    private double supIdentityGain; // 서폿 아덴 획득량 증가
    private double shieldEnhance; // 보호막 효과 증가
    private double healingEnhance; // 회복 효과 증가
    private double apBuffEfficiency; // 공격력 버프 효과 증가
    private double dmgBuffEfficiency; //피해 증가 버프 효과 증가
    private double brandPower; // 낙인력


    private double atkSpeed; // 공격 속도 증가
    private double movementSpeed; // 이동 속도 증가
    private double holdCastSpeed; // 홀딩, 캐스트 속도 증가
    private double chargeSpeed; // 차징 속도 증가
    private List<Double> cooldownReduction = new ArrayList<>(); // 쿨타임 감소

    private double awakeningCooldown; // 각성기 쿨타임 감소
    private int awakeningCast; // 각성기 사용 횟수 증가

    private double statusEffectDuration; // 상태 이상 효과 지속시간
    private int successorStrength; // 전승자의 힘 (초월 하의)
    private int advanceEtherWeaponPower; // 진군 에테르 (엘릭서)

    private double raidCaptain; // 돌격대장
    private Boolean masterTenacity = false; // 달인의 저력 (각인 유무)
    private Boolean mpEfficiency = false; // 마나 효율 증가 (각인 유무)
    private Boolean keenBluntWeapon = false; // 예리한 둔기 (각인 유무)
    private Boolean grudge = false; // 원한 (각인 유무)
    private Boolean cursedDoll = false; // 저주받은 인형 (각인 유무)
    private Boolean heavyArmor = false; // 중갑 착용 (각인 유무)
    private Boolean hitMaster = false; // 타격의 대가 (각인 유무)

    private CharacterAvatar characterAvatar; // 아바타 정보
    private double gemAttackPowerPercent; // 보석으로 증가하는 기본 공격력 수치

    public void merge(ArmoryEffect armoryEffect, ElixirEffect elixirEffect, TranscEffect transcEffect, EngravingEffect engravingEffect, AccessoryEffect accessoryEffect) {
        this.atkPower = elixirEffect.getAtkPower() + transcEffect.getAtkPower() + accessoryEffect.getAtkPower();
        this.weaponPower = armoryEffect.getWeaponPower() + elixirEffect.getWeaponPower() + transcEffect.getWeaponPower() + accessoryEffect.getWeaponPower();
        this.mainStat = armoryEffect.getMainStat() + elixirEffect.getMainStat() + transcEffect.getMainStat() + accessoryEffect.getMainStat();
        this.atkPowerPercent = elixirEffect.getAtkPowerPercent() + engravingEffect.getAtkPowerPercent() + accessoryEffect.getAtkPowerPercent();
        this.weaponPowerPercent = accessoryEffect.getWeaponPowerPercent();
        this.critRate = elixirEffect.getCritRate() + engravingEffect.getCritRate() + accessoryEffect.getCritRate();
        this.addDmg = armoryEffect.getAddDmg() + elixirEffect.getAddDmg() + accessoryEffect.getAddDmg();
        this.critDmg = elixirEffect.getCritDmg() + engravingEffect.getCritDmg() + accessoryEffect.getCritDmg();
        this.outgoingDmg.addAll(elixirEffect.getOutgoingDmg());
        this.outgoingDmg.addAll(transcEffect.getOutgoingDmg());
        this.outgoingDmg.addAll(engravingEffect.getOutgoingDmg());
        this.outgoingDmg.addAll(accessoryEffect.getOutgoingDmg());
        this.addBackHeadDmg = engravingEffect.getAddBackHeadDmg();
        this.outgoingDmgWhenCrit.add((double) elixirEffect.getOutgoingDmgWhenCrit());
        this.outgoingDmgWhenCrit.addAll(accessoryEffect.getOutgoingDmgWhenCrit());
        this.maxHP = elixirEffect.getMaxHP() + transcEffect.getMaxHP() + accessoryEffect.getMaxHP();
        this.vitality = armoryEffect.getVitality() + transcEffect.getVitality() + accessoryEffect.getVitality();
        this.vigor = armoryEffect.getVigor();
        this.hpRecovery = elixirEffect.getHpRecovery() + accessoryEffect.getHpRecovery();
        this.dmgReduction = elixirEffect.getDmgReduction() + transcEffect.getDmgReduction();
        this.defensePercent = engravingEffect.getDefensePercent();
        this.phyDefense = armoryEffect.getPhyDefense() + elixirEffect.getPhyDefense() + transcEffect.getPhyDefense();
        this.magDefense = armoryEffect.getMagDefense() + elixirEffect.getMagDefense() + transcEffect.getMagDefense();
        this.maxMP = accessoryEffect.getMaxHP();
        this.mpRecovery = engravingEffect.getMpRecovery();
        this.supIdentityGain = accessoryEffect.getSupIdentityGain();
        this.shieldEnhance = elixirEffect.getShieldEnhance() + accessoryEffect.getShieldEnhance();
        this.healingEnhance = elixirEffect.getHealingEnhance() + accessoryEffect.getHealingEnhance();
        this.apBuffEfficiency = elixirEffect.getApBuffEfficiency() + transcEffect.getApBuffEfficiency() + accessoryEffect.getApBuffEfficiency();
        this.dmgBuffEfficiency = accessoryEffect.getDmgBuffEfficiency();
        this.brandPower = transcEffect.getBrandPower() + accessoryEffect.getBrandPower();
        this.atkSpeed = engravingEffect.getAtkSpeed() + accessoryEffect.getSpeed();
        this.movementSpeed = engravingEffect.getMovementSpeed() + accessoryEffect.getSpeed();
        this.holdCastSpeed = engravingEffect.getHoldCastSpeed();
        this.chargeSpeed = engravingEffect.getChargeSpeed();
        this.cooldownReduction.add(elixirEffect.getCooldownReduction());
        this.cooldownReduction.add(engravingEffect.getCooldownReduction());
        this.cooldownReduction.addAll(accessoryEffect.getCooldownReduction());
        this.awakeningCooldown = engravingEffect.getAwakeningCooldown();
        this.awakeningCast = engravingEffect.getAwakeningCast();
        this.statusEffectDuration = accessoryEffect.getStatusEffectDuration();
        this.successorStrength = transcEffect.getSuccessorStrength();
        this.advanceEtherWeaponPower = elixirEffect.getAdvanceEtherWeaponPower();
        this.raidCaptain = engravingEffect.getRaidCaptain();
        this.masterTenacity = engravingEffect.getMasterTenacity();
        this.mpEfficiency = engravingEffect.getMpEfficiency();
        this.keenBluntWeapon = engravingEffect.getKeenBluntWeapon();
        this.grudge = engravingEffect.getGrudge();
        this.cursedDoll = engravingEffect.getCursedDoll();
        this.heavyArmor = engravingEffect.getHeavyArmor();
        this.hitMaster = engravingEffect.getHitMaster();
    }

    public void addWeaponPower(int increment) {
        this.weaponPower += increment;
    }
}
