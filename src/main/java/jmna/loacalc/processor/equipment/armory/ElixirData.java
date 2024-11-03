package jmna.loacalc.processor.equipment.armory;

import lombok.Data;

@Data
public class ElixirData {
    private String armoryType;
    private String effectType;
    private String effectName;
    private Integer level;
    private Double effect;

    public ElixirData(String armoryType, String effectType, String effectName, Integer level, Double effect) {
        this.armoryType = armoryType;
        this.effectType = effectType;
        this.effectName = effectName;
        this.level = level;
        this.effect = effect;
    }
}
