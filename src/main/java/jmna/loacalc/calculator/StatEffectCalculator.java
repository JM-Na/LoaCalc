package jmna.loacalc.calculator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

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

    // 리스트 안의 값을 곱 연산 (적에게 주는 피해 등)
    public double calculateMulOperation(List<Double> outgoingDmgs) {
        return outgoingDmgs.stream()
                .filter(Objects::nonNull)
                .reduce(1.0, (a, b) -> a * b);
    }

    // 리스트 안의 값을 합 연산 (추가 피해, 치명타 피해 등)
    public double calculateSumOperation(List<Double> additionalDmgs){
        return additionalDmgs.stream()
                .filter(Objects::nonNull)
                .mapToDouble(additionalDmg -> additionalDmg)
                .sum();
    }
}
