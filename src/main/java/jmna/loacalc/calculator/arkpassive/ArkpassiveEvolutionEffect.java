package jmna.loacalc.calculator.arkpassive;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ArkpassiveEvolutionEffect {
    private int crit;
    private int specialization;
    private int domination;
    private int swiftness;
    private int endurance;
    private int expertise;
    private List<Double> manaSkillCooldown = new ArrayList<>(); // 마나 스킬 쿨타임 감소
    private double manaConsumption; // 마나 소모량 감소
    private double evolutionDmg; // 진화형 피해
    private double manaEvolutionDmg; // 마나 스킬 진화형 피해
    private double critRate; // 치명타 적중률
    private List<Double> skillCooldown = new ArrayList<>(); // 각성기 제외 쿨타임 감소
    private double atkSpeed; // 공격 속도
    private double moveSpeed; // 이동 속도
    private double directionalSkillCritDmg; // 방향성 스킬 크리티컬 데미지
    private double identityGain; // 아덴 게이지 획득량
    private double brandPower; // 낙인력
    private int bluntSpike; // 뭉툭한 가시
    private int sonicBreakThrough; // 음속 돌파
    private int manaForge; // 마나 용광로

    public void addManaSkillCooldown(double increment) {
        this.manaSkillCooldown.add(increment);
    }

    public void addManaConsumption(double increment) {
        this.manaConsumption += increment;
    }

    public void addEvolutionDmg(double increment) {
        this.evolutionDmg += increment;
    }

    public void addManaEvolutionDmg(double increment) {
        this.manaEvolutionDmg += increment;
    }

    public void addCritRate(double increment) {
        this.critRate += increment;
    }

    public void addSkillCooldown(double increment) {
        this.skillCooldown.add(increment);
    }

    public void addAtkSpeed(double increment) {
        this.atkSpeed += increment;
    }

    public void addMoveSpeed(double increment) {
        this.moveSpeed += increment;
    }

    public void addDirectionalSkillCritDmg(double increment) {
        this.directionalSkillCritDmg += increment;
    }

    public void addIdentityGain(double increment) {
        this.identityGain += increment;
    }

    public void addBrandPower(double increment) {
        this.brandPower += increment;
    }
}
