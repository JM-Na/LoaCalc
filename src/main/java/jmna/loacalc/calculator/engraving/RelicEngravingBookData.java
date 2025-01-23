package jmna.loacalc.calculator.engraving;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Deprecated
public enum RelicEngravingBookData {
    ADRENALINE("아드레날린 각인서"),
    GRUDGE("원한 각인서"),
    KEEN_BLUNT_WEAPON("예리한 둔기 각인서"),
    RAID_CAPTAIN("돌격대장 각인서"),
    CURSED_DOLL("저주받은 인형 각인서"),
    AMBUSH_MASTER("기습의 대가 각인서"),
    HIT_MASTER("타격의 대가 각인서"),
    MASS_INCREASE("질량 증가 각인서"),
    MAGICK_STREAM("마나의 흐름 각인서"),
    MASTER_BRAWLER("결투의 대가 각인서"),
    SUPER_CHARGE("슈퍼 차지 각인서"),
    EXPERT("전문의 각인서"),
    DROPS_OF_ETHER("구슬동자 각인서"),
    ALL_OUT_ATTACK("속전속결 각인서"),
    AWAKENING("각성 각인서"),
    MP_EFFICIENCY_INCREASE("마나 효율 증가 각인서"),
    STABILIZED_STATUS("안정된 상태 각인서"),
    BARRICADE("바리케이드 각인서"),
    HEAVY_ARMOR("중갑 착용 각인서"),
    PRECISE_DAGGER("정밀 단도 각인서"),
    ;

    private final String name;

    RelicEngravingBookData(String name) {
        this.name = name;
    }

    public static RelicEngravingBookData of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(null);
    }
}
