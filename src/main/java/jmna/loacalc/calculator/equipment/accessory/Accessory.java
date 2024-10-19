package jmna.loacalc.calculator.equipment.accessory;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@ToString(callSuper = true)
public class Accessory extends SubEquipment {

    private BasicEffect basicEffect;

    private List<Tendency> tendencies;

    private List<EngravingEffect> engravingEffects;

    private List<HoneEffect> honeEffects;

    private Integer arkpassiveEffect;

    public Accessory(String type, String name, Integer quality, String grade, int tier) {
        this.setType(type);
        this.setName(name);
        this.setQuality(quality);
        this.setGrade(grade);
        this.setTier(tier);
    }
}
