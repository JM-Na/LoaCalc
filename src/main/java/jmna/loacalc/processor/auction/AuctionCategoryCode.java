package jmna.loacalc.processor.auction;

import java.util.Arrays;

public enum AuctionCategoryCode {
    NECKLACE("목걸이", 200010),
    EARRING("귀걸이", 200020),
    RING("반지", 200030),
    BRACELET("팔찌", 200040),
    ;

    private final String part;
    private final int code;

    AuctionCategoryCode(String part, int code) {
        this.part = part;
        this.code = code;
    }

    private static AuctionCategoryCode of(String part) {
        return Arrays.stream(values())
                .filter(value -> value.part.equals(part))
                .findFirst()
                .orElse(null);
    }

    public static Integer getPartCode(String part) {
        return of(part).code;
    }
}
