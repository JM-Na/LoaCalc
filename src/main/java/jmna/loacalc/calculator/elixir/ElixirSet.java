package jmna.loacalc.calculator.elixir;

import jmna.loacalc.calculator.transcendence.MainStatByTranscendence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum ElixirSet {

    DEAL_1("deal",1, 0.23, 0.23, 480),
    DEAL_2("deal", 2, 0.47, 0.47, 990),
    DEAL_3("deal", 3, 0.72, 0.72, 1500),
    DEAL_4("deal", 4, 1.08, 1.08, 2250),
    DEAL_5("deal", 5, 1.44, 1.44, 3000),
    SUP_1("sup", 1, 0.23, 0.64, 9),
    SUP_2("sup", 2, 0.47, 1.32, 19),
    SUP_3("sup", 3, 0.72, 2, 29),
    SUP_4("sup", 4, 1.08, 3, 43),
    SUP_5("sup", 5, 1.44, 4, 58);

    private final String type;
    private final int level;
    private final double dmgHealShield;
    private final double atkPower;
    private final int magDefHPRegen;


    ElixirSet(String type, int level, double dmgHealShield, double atkPower, int magDefHPRegen) {
        this.type = type;
        this.level = level;
        this.dmgHealShield = dmgHealShield;
        this.atkPower = atkPower;
        this.magDefHPRegen = magDefHPRegen;
    }

    public static ElixirSet of(String type, int level) {
        return Arrays.stream(values())
                .filter(value -> value.level == level && value.type == type)
                .findFirst()
                .orElse(null);
    }

    public static List<Double> findStatByLevel(String type, int level) {
        ElixirSet elixirSet = ElixirSet.of(type, level);
        if (elixirSet == null) {
            return List.of(0.0, 0.0, 0.0);
        }
        return List.of(elixirSet.dmgHealShield, elixirSet.atkPower, (double) elixirSet.magDefHPRegen);
    }
}
