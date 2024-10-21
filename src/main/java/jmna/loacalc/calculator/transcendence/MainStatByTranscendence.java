package jmna.loacalc.calculator.transcendence;

import java.lang.reflect.Array;
import java.util.Arrays;

public enum MainStatByTranscendence {
    TRANSCENDENCE_1(1, 600),
    TRANSCENDENCE_2(2, 1280),
    TRANSCENDENCE_3(3, 2040),
    TRANSCENDENCE_4(4, 2880),
    TRANSCENDENCE_5(5, 3800),
    TRANSCENDENCE_6(6, 4800),
    TRANSCENDENCE_7(7, 5880);

    private final int level;
    private final int mainStat;

    MainStatByTranscendence(int level, int mainStat) {
        this.level = level;
        this.mainStat = mainStat;
    }

    public static MainStatByTranscendence of(int level) {
        return Arrays.stream(values())
                .filter(value -> value.level == level)
                .findFirst()
                .orElse(null);
    }

    public static Integer findMainStatByLevel(int level) {
        MainStatByTranscendence mainStat = MainStatByTranscendence.of(level);
        if (mainStat == null) {
            return null;
        }
        return mainStat.mainStat;
    }
}
