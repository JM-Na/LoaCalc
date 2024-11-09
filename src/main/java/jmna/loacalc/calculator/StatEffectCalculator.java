package jmna.loacalc.calculator;

import org.springframework.stereotype.Component;

@Component
public class StatEffectCalculator {

    public double calculateStatCrit(int crit) {
        return crit / 28.0;
    }

    public double calculateSwiftnessSpeed(int swiftness) {
        return swiftness * 0.017;
    }

    public double calculateSwiftnessCooldown(int swiftness) {
        return swiftness * 0.021;
    }

}
