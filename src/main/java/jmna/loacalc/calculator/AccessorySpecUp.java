package jmna.loacalc.calculator;

import lombok.Data;

@Data
public class AccessorySpecUp {

    private String description;
    private String name;
    private double expectedSpecUp;
    private int cost;

    public AccessorySpecUp(String description, String name, double expectedSpecUp, Integer cost) {
        this.description = description;
        this.name = name;
        this.expectedSpecUp = expectedSpecUp;
        if (cost != null)
            this.cost = cost;
    }
}
