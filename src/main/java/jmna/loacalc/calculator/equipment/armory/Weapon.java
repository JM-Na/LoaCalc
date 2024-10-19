package jmna.loacalc.calculator.equipment.armory;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class Weapon extends BaseArmory{

    // 무기
    private Integer weaponPower;
    private Double additionalDmg;

    public Weapon() {

    }
}
