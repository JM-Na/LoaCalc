package jmna.loacalc.calculator.elixir;

import jmna.loacalc.processor.armory.equipment.armory.ElixirData;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;

public enum ElixirType {
    MAIN_STAT((elixirEffect, elixirData) -> elixirEffect.addMainStat(elixirData.getEffect().intValue()), "힘", "민첩", "지능"),
    WEAPON_POWER((elixirEffect, elixirData) -> elixirEffect.addWeaponPower(elixirData.getEffect().intValue()), "무기 공격력"),
    ATTACK_POWER((elixirEffect, elixirData) -> elixirEffect.addAttackPower(elixirData.getEffect().intValue()), "공격력"),
    BOSS_DAMAGE((elixirEffect, elixirData) -> elixirEffect.addOutgoingDmg(elixirData.getEffect()), "보스 피해"),
    ADD_DAMAGE((elixirEffect, elixirData) -> elixirEffect.addAddDmg(elixirData.getEffect()), "추가 피해"),
    CRIT_DAMAGE((elixirEffect, elixirData) -> elixirEffect.addCritDmg(elixirData.getEffect()), "치명타 피해"),
    DAMAGE_REDUCTION((elixirEffect, elixirData) -> elixirEffect.addDmgReduction(elixirData.getEffect()), "받는 피해 감소"),
    PHYSICAL_DEFENSE((elixirEffect, elixirData) -> elixirEffect.addPhyDefense(elixirData.getEffect().intValue()), "물리 방어력"),
    MAGIC_DEFENSE((elixirEffect, elixirData) -> elixirEffect.addMagDefense(elixirData.getEffect().intValue()), "마법 방어력"),
    MAX_HP((elixirEffect, elixirData) -> elixirEffect.addMaxHP(elixirData.getEffect().intValue()), "최대 생명력"),
    ATK_BUFF((elixirEffect, elixirData) -> elixirEffect.addAtkBuffEfficiency(elixirData.getEffect()), "아군 강화"),
    SHIELD_ENHANCE((elixirEffect, elixirData) -> elixirEffect.addShieldEnhance(elixirData.getEffect()), "보호막 강화"),
    HEALING_ENHANCE((elixirEffect, elixirData) -> elixirEffect.addHealingEnhance(elixirData.getEffect()), "회복 강화"),
    // 질서 효과
    ORDER_DEAL((elixirEffect, elixirData) -> {
        List<Double> statByLevel = ElixirSetDeal.findStatByLevel(elixirData.getLevel());
        elixirEffect.addAttackPowerPercent(statByLevel.get(1));
        elixirEffect.addPhyDefense(statByLevel.get(2).intValue());
        elixirEffect.setSetEffect(elixirData.getEffectName());
    }, "회심 (질서)", "달인 (질서)"),
    ORDER_SUP((elixirEffect, elixirData) -> {
        List<Double> statByLevel = ElixirSetSup.findStatByLevel(elixirData.getLevel());
        elixirEffect.addAtkBuffEfficiency(statByLevel.get(1));
        elixirEffect.addMaxHP(statByLevel.get(2).intValue());
        elixirEffect.setSetEffect(elixirData.getEffectName());
    }, "선각자 (질서)", "진군 (질서)"),

    // 혼돈 효과
    CHAOS_DEAL((elixirEffect, elixirData) -> {
        List<Double> statByLevel = ElixirSetDeal.findStatByLevel(elixirData.getLevel());
        elixirEffect.addOutgoingDmg(statByLevel.get(0));
        elixirEffect.addMagDefense(statByLevel.get(2).intValue());
        elixirEffect.setSetEffect(elixirData.getEffectName());
    }, "회심 (혼돈)", "달인 (혼돈)"),
    CHAOS_SUP((elixirEffect, elixirData) -> {
        List<Double> statByLevel = ElixirSetSup.findStatByLevel(elixirData.getLevel());
        elixirEffect.addHealingEnhance(statByLevel.get(0));
        elixirEffect.addShieldEnhance(statByLevel.get(0));
        elixirEffect.addHPRegen(statByLevel.get(3).intValue());
        elixirEffect.setSetEffect(elixirData.getEffectName());
    }, "선각자 (혼돈)", "진군 (혼돈)");
    private final List<String> effectNames;
    private final BiConsumer<ElixirEffect, ElixirData> effectApplier;

    ElixirType(BiConsumer<ElixirEffect, ElixirData> effectApplier, String... effectNames) {
        this.effectApplier = effectApplier;
        this.effectNames = Arrays.asList(effectNames);
    }

    public static void applyEffect(String effectName, ElixirEffect elixirEffect, ElixirData elixirData) {
        for (ElixirType type : values()) {
            if (type.effectNames.contains(effectName)) {
                type.effectApplier.accept(elixirEffect, elixirData);
                return;
            }
        }
    }
}
