package jmna.loacalc.processor.equipment.accessory;

import lombok.Builder;
import lombok.Data;

@Data
public class ElixirEffect {
    private String part;
    private String effect;
    private Integer level;

    public ElixirEffect(String part, String effect, Integer level) {
        this.part = part;
        this.effect = effect;
        this.level = level;
    }
}
