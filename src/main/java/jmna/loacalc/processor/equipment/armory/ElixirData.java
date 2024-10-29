package jmna.loacalc.processor.equipment.armory;

import lombok.Data;

@Data
public class ElixirData {
    private String part;
    private String effectName;
    private Integer level;
    private Double effect;

    public ElixirData(String part, String effectName, Integer level, Double effect) {
        this.part = part;
        this.effectName = effectName;
        this.level = level;
        this.effect = effect;
    }
}
