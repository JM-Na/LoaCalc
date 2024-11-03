package jmna.loacalc.calculator.elixir;

import java.util.Arrays;
import java.util.List;

public enum ElixirSetDeal {

    DEAL_1(1, 0.23, 0.23, 480),
    DEAL_2(2, 0.47, 0.47, 990),
    DEAL_3(3, 0.72, 0.72, 1500),
    DEAL_4(4, 1.08, 1.08, 2250),
    DEAL_5(5, 1.44, 1.44, 3000);

    private final int level;
    private final double outgoingDmg;
    private final double atkPowerPercent;
    private final int phyMagDefense;


    ElixirSetDeal(int level, double outgoingDmg, double atkPowerPercent, int phyMagDefense) {
        this.level = level;
        this.outgoingDmg = outgoingDmg;
        this.atkPowerPercent = atkPowerPercent;
        this.phyMagDefense = phyMagDefense;
    }

    public static ElixirSetDeal of(int level) {
        return Arrays.stream(values())
                .filter(value -> value.level == level)
                .findFirst()
                .orElse(null);
    }

    public static List<Double> findStatByLevel(int level) {
        ElixirSetDeal elixirSetDeal = ElixirSetDeal.of(level);
        if (elixirSetDeal == null) {
            return List.of(0.0, 0.0, 0.0);
        }
        return List.of(elixirSetDeal.outgoingDmg, elixirSetDeal.atkPowerPercent, (double) elixirSetDeal.phyMagDefense);
    }
}
