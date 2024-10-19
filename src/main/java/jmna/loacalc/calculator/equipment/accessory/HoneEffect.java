package jmna.loacalc.calculator.equipment.accessory;

import lombok.Data;

@Data
public class HoneEffect {
    private String name;
    private String effect;

    public HoneEffect(String[] name) {
        this.name = name[0];
        this.effect = name[1];
    }
}
