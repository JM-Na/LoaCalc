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
}
