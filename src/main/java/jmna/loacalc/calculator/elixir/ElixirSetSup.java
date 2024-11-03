package jmna.loacalc.calculator.elixir;

import java.util.Arrays;
import java.util.List;

public enum ElixirSetSup {
    SUP_1(1, 0.23, 0.64, 800,9),
    SUP_2(2, 0.47, 1.32, 1650,19),
    SUP_3(3, 0.72, 2, 2500,29),
    SUP_4(4, 1.08, 3, 3750,43),
    SUP_5(5, 1.44, 4, 5000,58);

    private final int level;
    private final double healShieldEfficiency;
    private final double atkBuffEfficiency;
    private final int maxHP;
    private final int hpRegen;


    ElixirSetSup(int level, double healShieldEfficiency, double atkBuffEfficiency, int maxHP, int hpRegen) {
        this.level = level;
        this.healShieldEfficiency = healShieldEfficiency;
        this.atkBuffEfficiency = atkBuffEfficiency;
        this.maxHP = maxHP;
        this.hpRegen = hpRegen;
    }

    public static ElixirSetSup of(int level) {
        return Arrays.stream(values())
                .filter(value -> value.level == level)
                .findFirst()
                .orElse(null);
    }

    public static List<Double> findStatByLevel(int level) {
        ElixirSetSup elixirSet = ElixirSetSup.of(level);
        if (elixirSet == null) {
            return List.of(0.0, 0.0, 0.0);
        }
        return List.of(elixirSet.healShieldEfficiency, elixirSet.atkBuffEfficiency, (double)elixirSet.maxHP, (double) elixirSet.hpRegen);
    }
}
