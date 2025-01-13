package jmna.loacalc.calculator;

import lombok.Data;

@Data
public class AccessorySpecUp {

    private String description;
    private String name;
    private double expectedSpecUp;
    private double cost;

    public AccessorySpecUp(String description, String name, double expectedSpecUp, double cost) {
        this.description = description;
        this.name = name;
        this.expectedSpecUp = expectedSpecUp;
        this.cost = cost;
    }
}
