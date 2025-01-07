package jmna.loacalc.calculator.arkpassive.enlightenment;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ArkpassiveEnlightenmentEffect {

    private double critRate;
    private double critDmg;
    private List<Double> outGoingDmg = new ArrayList<>();
    private int mpRecovery;
    private int defenseStateShield; // 방어태세 보호막
    private double atkSpeed;
    private double moveSpeed;
    private double awakeningDmg;
    private double frontalDmg;
    private double backDmg;
    private double atkPowerPercent;
    private double cooldownReduction;

    // 인파이터
    private int staminaSkillCritDmg; // 인파이터 기력 스킬
    private int shockSkillCritRate; // 인파이터 충격 스킬
    private int staminaSkillDmg; // 인파이터 기력 스킬
    private int shockSkillDmg; // 인파이터 충격 스킬

    // 창술사
    private int flurryMoveSpeed;
    private int focusAtkSpeed;
    private int flurryCritDmg;
    private int flurryCritRate;
    private int flurryDmg;
    private double focusDmg;
    private double yeonMark;

    // 브레이커
    private int dmgByCritRate;

    // 데빌헌터
    private int piercing;

    // 호크아이
    private int silverHawkBasicDmg;
    private int silverHawkSkillDmg;

    // 스카우터
    private List<Double> jointSkillDmg = new ArrayList<>();
    private List<Double> syncSkillDmg = new ArrayList<>();

    // 소서리스
    private int arcaneCritRate;
    private int arcaneCritDmg;
    private List<Double> arcaneOutgoingDmg = new ArrayList<>();
    private List<Double> mainSkillDmg = new ArrayList<>();

    // 아르카나
    private List<Integer> emperorDmg = new ArrayList<>();
    private List<Double> ruinSkillDmg = new ArrayList<>();
    private int ruinStack4Dmg;
    private List<Double> normalSkillDmg = new ArrayList<>();
    private double normalSkillCritRate;

    // 서머너
    private int phoenixDmg;

    // 블레이드
    private List<Double> surgeDmg = new ArrayList<>();

    // 기상술사
    private double bonusCritDmg;
    private double bonusCritRate;

    public void addCritRate(double increment) {
        this.critRate += increment;
    }

    public void addCritDmg(double increment) {
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

    public void addAwakeningDmg(double increment) {
        this.awakeningDmg += increment;
    }

    public void addFlurryCritDmg(double increment) {
        this.flurryCritDmg += increment;
    }

    public void addFlurryDmg(double increment) {
        this.flurryDmg += increment;
    }

    public void addFocusDmg(double increment) {
        this.focusDmg += increment;
    }

    public void addFrontalDmg(double increment) {
        this.frontalDmg += increment;
    }

    public void addBackDmg(double increment) {
        this.backDmg += increment;
    }

    public void addSyncSkillDmg(double increment) {
        this.syncSkillDmg.add(increment);
    }

    public void addJointSkillDmg(double increment) {
        this.jointSkillDmg.add(increment);
    }

    public void addArcaneDmg(double increment) {
        this.arcaneOutgoingDmg.add(increment);
    }

    public void addMainSkillDmg(double increment) {
        this.mainSkillDmg.add(increment);
    }

    public void addEmperorDmg(int increment) {
        this.emperorDmg.add(increment);
    }

    public void addRuinSkillDmg(double increment) {
        this.ruinSkillDmg.add(increment);
    }

    public void addRuinStack4Dmg(double increment) {
        this.ruinStack4Dmg += increment;
    }

    public void addNormalSkillDmg(double increment) {
        this.normalSkillDmg.add(increment);
    }

    public void addNormalSkillCritRate(double increment) {
        this.normalSkillCritRate += increment;
    }

    public void addSurgeDmg(double increment) {
        this.surgeDmg.add(increment);
    }
}
