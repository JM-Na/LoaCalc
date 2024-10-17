package jmna.loacalc.calculator.armory;

import jmna.loacalc.calculator.ElixirEffect;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Armor extends BaseArmory{
    // 방어구
    private Integer phyDefense;
    private Integer magDefense;
    private Integer mainStat;
    private Integer vitality; // 체력
    private Integer vigor; // 생명 활성력

    private List<ElixirEffect> elixirEffects;

    public Armor() {
        super();
    }
}
