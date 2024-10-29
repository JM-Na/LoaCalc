package jmna.loacalc.calculator;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AdditionalEffectCalculator {

    // 적에게 주는 피해 (곱 연산)
    public double calculateOutgoingDmg(List<Double> outgoingDmgs) {
        return outgoingDmgs.stream()
                .filter(Objects::nonNull)
                .reduce(1.0, (a, b) -> a * b);
    }

    // 추가 피해 (합 연산)
    public double calculateAdditionalDmg(List<Double> additionalDmgs){
        return additionalDmgs.stream()
                .filter(Objects::nonNull)
                .mapToDouble(additionalDmg -> additionalDmg)
                .sum();
    }

    // 치명타 피해 (합 연산)
    public double calculateCriticalDmg(List<Double> criticalDmgs) {
        return criticalDmgs.stream()
                .filter(Objects::nonNull)
                .mapToDouble(criticalDmg -> criticalDmg)
                .sum();
    }
}
