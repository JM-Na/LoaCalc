package jmna.loacalc.processor.equipment.accessory;

import lombok.Data;

@Data
public class ElixirEffect {
    private String part;
    private String effectName;
    private Integer level;
    private Double effect;

    public ElixirEffect(String part, String effectName, Integer level, Double effect) {
        this.part = part;
        this.effectName = effectName;
        this.level = level;
        this.effect = effect;
    }
}
