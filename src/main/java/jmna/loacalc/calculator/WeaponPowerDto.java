package jmna.loacalc.calculator;

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
