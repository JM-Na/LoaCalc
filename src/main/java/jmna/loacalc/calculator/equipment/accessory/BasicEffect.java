package jmna.loacalc.calculator.equipment.accessory;

import lombok.Data;

import java.util.List;

@Data
public class BasicEffect {
    private Integer mainStat;
    private Integer vitality; // 체력?

    public BasicEffect(List<String> effects) {
        this.mainStat = Integer.valueOf(effects.get(1));
        this.vitality = Integer.valueOf(effects.get(7));
    }

}
