package jmna.loacalc.processor.equipment.armory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@RequiredArgsConstructor
public class Weapon extends BaseArmory{

    // 무기
    private Integer weaponPower;
    private Double addDmg;

}
