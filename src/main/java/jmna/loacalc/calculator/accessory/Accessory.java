package jmna.loacalc.calculator.accessory;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Accessory extends SubEquipment {

    private BasicEffect basicEffect;

    private List<Tendency> tendencies;

    private List<EngravingEffect> engravingEffects;

    private List<HoneEffect> honeEffects;

    private Integer arkpassiveEffect;

    public Accessory() {

    }
}
