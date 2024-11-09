package jmna.loacalc.calculator.arkpassive;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum ArkpassiveEvolution {
    CRIT(1, "치명", 0, new HashMap<>()),
    SPECIALIZATION(1, "특화", 0, new HashMap<>()),
    DOMINATION(1, "제압", 0, new HashMap<>()),
    SWIFTNESS(1, "신속", 0, new HashMap<>()),
    ENDURANCE(1, "인내", 0, new HashMap<>()),
    EXPERTISE(1, "숙련", 0, new HashMap<>()),
    BOUNDLESS_MANA_1(2, "끝없는 마나", 1, new HashMap<>(){{put("Mana Skill Cooldown", 7.0);put("Mana Consumption", 10.0);}}),
    BOUNDLESS_MANA_2(2, "끝없는 마나", 2, new HashMap<>(){{put("Mana Skill Cooldown", 14.0);put("Mana Consumption", 20.0);}}),
    FORBIDDEN_SPELL_1(2, "금단의 주문", 1, new HashMap<>(){{put("Evolution Damage", 5.0);put("Evolution Damage for Mana Skill", 5.0);put("Mana Consumption", 6.0);}}),
    FORBIDDEN_SPELL_2(2, "금단의 주문", 2, new HashMap<>(){{put("Evolution Damage", 10.0);put("Evolution Damage for Mana Skill", 10.0);put("Mana Consumption", 12.0);}}),
    KEEN_SENSE_1(2, "예리한 감각", 1, new HashMap<>(){{put("Evolution Damage", 5.0);put("Crit Rate", 5.0);}}),
    KEEN_SENSE_2(2, "예리한 감각", 2, new HashMap<>(){{put("Evolution Damage", 10.0);put("Crit Rate", 10.0);}}),
    LIMIT_BREAKING_1(2, "한계 돌파", 1, new HashMap<>(){{put("Evolution Damage", 10.0);}}),
    LIMIT_BREAKING_2(2, "한계 돌파", 2, new HashMap<>(){{put("Evolution Damage", 20.0);}}),
    LIMIT_BREAKING_3(2, "한계 돌파", 3, new HashMap<>(){{put("Evolution Damage", 30.0);}}),
    OPTIMIZATION_TRAINING_1(2, "최적화 훈련", 1, new HashMap<>(){{put("Evolution Damage", 5.0);put("Skill Cooldown", 4.0);}}),
    OPTIMIZATION_TRAINING_2(2, "최적화 훈련", 2, new HashMap<>(){{put("Evolution Damage", 10.0);put("Skill Cooldown", 8.0);}}),
    GODDESS_OF_BLESSING_1(2, "축복의 여신", 1, new HashMap<>(){{put("Attack and Move Speed", 3.0);}}),
    GODDESS_OF_BLESSING_2(2, "축복의 여신", 2, new HashMap<>(){{put("Attack and Move Speed", 6.0);}}),
    GODDESS_OF_BLESSING_3(2, "축복의 여신", 3, new HashMap<>(){{put("Attack and Move Speed", 9.0);}}),
    LIMITLESS_MAGICK_1(3, "무한한 마력", 1, new HashMap<>(){{put("Evolution Damage", 8.0);put("Mana SKill Cooldown", 7.0);put("Mana Comsumption", 8.0);}}),
    LIMITLESS_MAGICK_2(3, "무한한 마력", 2, new HashMap<>(){{put("Evolution Damage", 16.0);put("Mana SKill Cooldown", 8.0);put("Mana Comsumption", 16.0);}}),
    FULL_FORCE_SMITE_1(3, "혼신의 강타", 1, new HashMap<>(){{put("Crit Rate", 12.0);put("Evolution Damage", 2.0);}}),
    FULL_FORCE_SMITE_2(3, "혼신의 강타", 1, new HashMap<>(){{put("Crit Rate", 12.0);put("Evolution Damage", 2.0);}}),
    STRIKE_1(3, "일격", 1, new HashMap<>(){{put("Crit Rate", 10.0);put("Directional Skill Crit Damage", 16.0);}}),
    STRIKE_2(3, "일격", 2, new HashMap<>(){{put("Crit Rate", 20.0);put("Directional Skill Crit Damage", 32.0);}}),
    JUGGERNAUT_1(3, "파괴전차", 1, new HashMap<>(){{put("Evolution Damage", 12.0);put("Attack Speed", 4.0);}}),
    JUGGERNAUT_2(3, "파괴전차", 2, new HashMap<>(){{put("Evolution Damage", 24.0);put("Attack Speed", 8.0);}}),
    TIME_DOMINATOR_1(3, "타이밍 지배", 1, new HashMap<>(){{put("Skill Cooldown", 5.0);put("Evolution Damage", 8.0);}}),
    TIME_DOMINATOR_2(3, "타이밍 지배", 2, new HashMap<>(){{put("Skill Cooldown", 10.0);put("Evolution Damage", 16.0);}}),
    DANCE_OF_PASSION_1(3, "정열의 춤", 1, new HashMap<>(){{put("Identity Gain", 10.0);put("Evolution Damage", 7.0);}}),
    DANCE_OF_PASSION_2(3, "정열의 춤", 2, new HashMap<>(){{put("Identity Gain", 20.0);put("Evolution Damage", 14.0);}}),
    BLUNT_SPIKE_1(4, "뭉툭한 가시", 1, new HashMap<>(){{put("Blunt Spike", 50.0);put("Evolution Damage", 7.5);}}),
    BLUNT_SPIKE_2(4, "뭉툭한 가시", 2, new HashMap<>(){{put("Blunt Spike", 70.0);put("Evolution Damage", 15.0);}}),
    SONIC_BREAKTHROUGH_1(4, "음속 돌파", 1, new HashMap<>(){{put("Sonic Breakthrough", 12.0);}}),
    SONIC_BREAKTHROUGH_2(4, "음속 돌파", 2, new HashMap<>(){{put("Sonic Breakthrough", 24.0);}}),
    INFIGHTING_1(4, "인파이팅", 1, new HashMap<>(){{put("Evolution Damage", 9.0);}}),
    INFIGHTING_2(4, "인파이팅", 2, new HashMap<>(){{put("Evolution Damage", 18.0);}}),
    STAND_UP_FIGHTING_1(4, "입식 타격가", 1, new HashMap<>(){{put("Evolution Damage", 10.5);put("Brand Power", 10.0);}}),
    STAND_UP_FIGHTING_2(4, "입식 타격가", 2, new HashMap<>(){{put("Evolution Damage", 21.0);put("Brand Power", 20.0);}}),
    MANA_FORGE_1(4, "마나 용광로", 1, new HashMap<>(){{put("Mana Forge", 12.0);put("Brand Power", 10.0);}}),
    MANA_FORGE_2(4, "마나 용광로", 2, new HashMap<>(){{put("Mana Forge", 24.0);put("Brand Power", 20.0);}}),
    STABILITY_1(4, "안정된 관리자", 1, new HashMap<>(){{put("Brand Power", 10.0);put("Identity Gain", -3.0);}}),
    STABILITY_2(4, "안정된 관리자", 2, new HashMap<>(){{put("Brand Power", 20.0);put("Identity Gain", -6.0);}}),
    ;

    private final int tier;
    private final String name;
    private final int lvl;
    private final Map<String, Double> effect = new HashMap<>();

    ArkpassiveEvolution(int tier, String name, int lvl, Map<String, Double> effect) {
        this.tier = tier;
        this.name = name;
        this.lvl = lvl;
        this.effect.putAll(effect);
    }

    public static ArkpassiveEvolution of(int tier, String name, int lvl) {
        return Arrays.stream(ArkpassiveEvolution.values())
                .filter(value -> value.tier == tier && value.name.equals(name) && value.lvl == lvl)
                .findFirst()
                .orElse(null);
    }

    public static ArkpassiveEvolution of(int tier, String name) {
        return Arrays.stream(ArkpassiveEvolution.values())
                .filter(value -> value.tier == tier && value.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public static Map<String, Double> findEffect(int tier, String name, int lvl) {
        if (tier == 1) {
            String effectName = of(tier, name).name;
            Map<String, Double> effect = new HashMap<>();
            effect.put(effectName, lvl * 50.0);
            return effect;
        } else {
            return of(tier, name, lvl).effect;
        }
    }
}
