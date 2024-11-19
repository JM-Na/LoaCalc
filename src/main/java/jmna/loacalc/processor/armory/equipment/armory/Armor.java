package jmna.loacalc.processor.armory.equipment.armory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@RequiredArgsConstructor
public class Armor extends BaseArmory{
    // 방어구
    private Integer phyDefense;
    private Integer magDefense;
    private Integer mainStat;
    private Integer vitality; // 체력
    private Integer vigor; // 생명 활성력

    private List<ElixirData> elixirData;

}
