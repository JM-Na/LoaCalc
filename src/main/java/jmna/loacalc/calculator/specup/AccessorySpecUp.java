package jmna.loacalc.calculator.specup;

import lombok.Data;

@Data
public class AccessorySpecUp {

    private String description;
    private String name;
    private double expectedSpecUp;
    private int cost = 10000;
    private int seq;

    public AccessorySpecUp(String description, String name, double expectedSpecUp, Integer cost, int seq) {
        this.description = description;
        this.name = name;
        this.expectedSpecUp = expectedSpecUp;
        if (cost != null)
            this.cost = cost;
        this.seq = seq;
    }
}
