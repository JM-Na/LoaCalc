package jmna.loacalc.calculator;

import lombok.Data;

@Data
public class WeaponPowerDto {
    private Integer base;
    private Double percent;

    public WeaponPowerDto(Integer base, Double percent) {
        this.base = base;
        this.percent = percent;
    }
}
