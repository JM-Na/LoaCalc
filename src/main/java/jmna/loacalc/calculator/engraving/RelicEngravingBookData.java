package jmna.loacalc.calculator.engraving;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

public enum RelicEngravingBookData {
    ADRENALINE("아드레날린 각인서", null),
    GRUDGE("원한 각인서", null),
    KEEN_BLUNT_WEAPON("예리한 둔기 각인서", null),
    RAID_CAPTAIN("돌격대장 각인서", null),
    CURSED_DOLL("저주받은 인형 각인서", null),
    AMBUSH_MASTER("기습의 대가 각인서", null),
    HIT_MASTER("타격의 대가 각인서", null),
    MASS_INCREASE("질량 증가 각인서", null),
    MAGICK_STREAM("마나의 흐름 각인서", null),
    MASTER_BRAWLER("결투의 대가 각인서", null),
    SUPER_CHARGE("슈퍼 차지 각인서", null),
    EXPERT("전문의 각인서", null),
    DROPS_OF_ETHER("구슬동자 각인서", null),
    ALL_OUT_ATTACK("속전속결 각인서", null),
    AWAKENING("각성 각인서", null),
    MP_EFFICIENCY_INCREASE("마나 효율 증가 각인서", null),
    STABILIZED_STATUS("안정된 상태 각인서", null),
    BARRICADE("바리케이드 각인서", null),
    HEAVY_ARMOR("중갑 착용 각인서", null),
    PRECISE_DAGGER("정밀 단도 각인서", null),
    ;

    private final String name;
    @Getter
    @Setter
    private Double price;

    RelicEngravingBookData(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public static RelicEngravingBookData of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public static void initPrice(String name, Double price) {
        RelicEngravingBookData target = of(name);
        target.setPrice(price);
    }

    public static Double getPriceByName(String name) {
        RelicEngravingBookData target = of(name);
        return target.price;
    }
}
