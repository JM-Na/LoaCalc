package jmna.loacalc.calculator.hone;

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
    T3_FRAGMENT("명예의 파편 주머니(중)"),
    T4_FRAGMENT("운명의 파편 주머니(중)"),
    T3_FUSION("최상급 오레하 융화 재료"),
    T4_FUSION("아비도스 융화 재료"),
    T3_SUP_RARE("태양의 은총"),
    T3_SUP_HEROIC("태양의 축복"),
    T3_SUP_EPIC("태양의 가호"),
    T4_SUP_WEAPON("용암의 숨결"),
    T4_SUP_ARMOR("빙하의 숨결"),
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
