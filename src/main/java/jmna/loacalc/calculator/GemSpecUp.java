package jmna.loacalc.calculator;

import lombok.Data;

import java.util.List;

@Data
public class GemSpecUp {

    private String description;
    private int targetLvl;
    private double expectedSpecUp;
    private double cost;

    public GemSpecUp(String description, int targetLvl, double expectedSpecUp, double cost) {
        this.description = description;
        this.targetLvl = targetLvl;
        this.expectedSpecUp = expectedSpecUp;
        this.cost = cost;
    }
}
