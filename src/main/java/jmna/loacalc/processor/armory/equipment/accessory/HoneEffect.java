package jmna.loacalc.processor.armory.equipment.accessory;

import jmna.loacalc.processor.auction.AccessoryOptionType;
import lombok.Data;

@Data
public class HoneEffect {
    private String name;
    private Double effect;
    private AccessoryOptionType type;

    public HoneEffect(String[] name) {
        if (name[1].contains("%")) {
            name[0] += " %";
            name[1] = name[1].replace("%", "");
        }
        this.name = name[0];
        this.effect = Double.valueOf(name[1]);
        this.type = AccessoryOptionType.findByTypeAndIncrement(name[0], Double.valueOf(name[1]));
    }
}
