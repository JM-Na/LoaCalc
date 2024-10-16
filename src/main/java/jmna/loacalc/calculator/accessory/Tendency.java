package jmna.loacalc.calculator.accessory;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tendency {
    private String name;
    private Integer grade;

    public Tendency(String[] input) {
        this.name = input[0];
        this.grade = Integer.valueOf(input[1]);
    }
}
