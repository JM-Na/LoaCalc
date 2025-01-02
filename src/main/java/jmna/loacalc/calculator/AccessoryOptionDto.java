package jmna.loacalc.calculator;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AccessoryOptionDto {

    List<String> OPTION_LIST_1 = List.of("공격력 %", "추가 피해 %", "치명타 적중률 %");
    List<String> OPTION_LIST_2 = List.of("무기 공격력 %", "치명타 피해 %");

    private double option1Increment = 0.0;
    private double option2Increment = 0.0;
    private double prevDmg;
    private double expDmg;
    private List<Double> outgoingDmg = new ArrayList<>();

    public void addOptionIncrement(String option, double increment, boolean isAdding) {
        if (OPTION_LIST_1.contains(option)){
            if(isAdding)
                this.option1Increment += increment;
            else
                this.option1Increment -= increment;
        } else if (OPTION_LIST_2.contains(option)) {
            if(isAdding)
                this.option2Increment += increment;
            else
                this.option2Increment -= increment;
        } else if (option.equals("적에게 주는 피해 %")) {
            if(isAdding)
                this.outgoingDmg.add(increment);
            else
                this.outgoingDmg.add(-increment);
        }
    }
}
