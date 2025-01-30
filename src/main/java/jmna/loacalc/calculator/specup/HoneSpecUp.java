package jmna.loacalc.calculator.specup;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class HoneSpecUp {

    private String description;
    private List<String> name;
    private double expectedSpecUp;
    private double cost;

    public HoneSpecUp(String description, List<String> name, double expectedSpecUp, double cost) {
        this.description = description;
        this.name = name;
        this.expectedSpecUp = expectedSpecUp;
        this.cost = cost;
    }
}
