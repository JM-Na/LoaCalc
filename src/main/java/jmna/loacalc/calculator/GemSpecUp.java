package jmna.loacalc.calculator;

import lombok.Data;

import java.util.List;

@Data
public class GemSpecUp {

    private String description;
    private int targetLvl;
    private double expectedSpecUp;
    private int cost;

    public GemSpecUp(String description, int targetLvl, double expectedSpecUp, Integer cost) {
        this.description = description;
        this.targetLvl = targetLvl;
        this.expectedSpecUp = expectedSpecUp * 100;
        if (cost != null)
            this.cost = cost;
    }
}
