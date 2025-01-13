package jmna.loacalc.calculator;

import lombok.Data;

@Data
public class EngravingSpecUp {

    private String description;
    private String name;
    private double expectedSpecUp;
    private double cost;

    public EngravingSpecUp(String description, String name, double expectedSpecUp, double cost) {
        this.description = description;
        this.name = name;
        this.expectedSpecUp = expectedSpecUp;
        this.cost = cost;
    }
}
