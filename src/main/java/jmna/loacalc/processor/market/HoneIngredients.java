package jmna.loacalc.processor.market;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public enum HoneIngredients {
    T3_DESTRUCTION("정제된 파괴강석"),
    T4_DESTRUCTION("운명의 파괴석"),
    T3_GUARDIAN("정제된 수호강석"),
    T4_GUARDIAN("운명의 수호석"),
    T3_LEAP("찬란한 명예의 돌파석"),
    T4_LEAP("운명의 돌파석"),
    T3_FRAGMENT("명예의 파편"),
    T4_FRAGMENT("운명의 파편"),
    T3_FUSION("최상급 오레하 융화 재료"),
    T4_FUSION("아비도스 융화 재료"),
    ;

    @Getter
    private final String name;
    @Getter
    @Setter
    private String icon;
    @Getter
    @Setter
    private double price;

    HoneIngredients(String name) {
        this.name = name;
    }

    public static HoneIngredients of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public static void setIconAndPrice(String name, String icon, double price) {
        HoneIngredients target = of(name);
        if (target != null) {
            target.setIcon(icon);
            target.setPrice(price);
        }
    }
}
