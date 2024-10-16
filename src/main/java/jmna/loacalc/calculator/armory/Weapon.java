package jmna.loacalc.calculator.armory;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class Weapon extends BaseArmory{

    // 무기
    private Integer weaponPower;
    private Integer additionalDmg;

    public Weapon() {

    }
}
