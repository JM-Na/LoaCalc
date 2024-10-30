package jmna.loacalc.calculator.v1;

import lombok.Data;

@Data
public class WeaponPowerDto {
    private Integer baseWeaponPower;
    private Double weaponPowerPercent;

    public WeaponPowerDto(Integer baseWeaponPower, Double weaponPowerPercent) {
        this.baseWeaponPower = baseWeaponPower;
        this.weaponPowerPercent = weaponPowerPercent;
    }
}
