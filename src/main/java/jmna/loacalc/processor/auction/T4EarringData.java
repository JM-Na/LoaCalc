package jmna.loacalc.processor.auction;

public enum T4EarringData {
    EARRING_L_1("하 단일", "공격력 %", "하", null, null, null),
    EARRING_L_2("하 단일", "무기 공격력 %", "하", null, null, null),
    EARRING_M_1("중 단일", "공격력 %", "중", null, null, null),
    EARRING_M_2("중 단일", "무기 공격력 %", "중", null, null, null),
    EARRING_H_1("상 단일", "공격력 %", "상", null, null, null),
    EARRING_H_2("상 단일", "무기 공격력 %", "상", null, null, null),
    EARRING_LL("하하", "공격력 %", "하", "무기 공격력 %", "하", null),
    EARRING_ML_1("중하", "공격력 %", "중", "무기 공격력 %", "하", null),
    EARRING_ML_2("중하", "무기 공격력 %", "중", "공격력 %", "하", null),
    EARRING_HL_1("상하", "공격력 %", "상", "무기 공격력 %", "하", null),
    EARRING_HL_2("상하", "무기 공격력 %", "상", "공격력 %", "하", null),
    EARRING_MM("중중", "공격력 %", "중", "무기 공격력 %", "중", null),
    EARRING_HM_1("상중", "공격력 %", "상", "무기 공격력 %", "중", null),
    EARRING_HM_2("상중", "무기 공격력 %", "상", "공격력 %", "중", null),
    EARRING_HH("상상", "공격력 %", "상", "무기 공격력 %", "상", null),
    ;

    private final String type;
    private final String effectName1;
    private final String effectRank1;
    private final String effectName2;
    private final String effectRank2;
    private Double price;

    T4EarringData(String type, String effectName1, String effectRank1, String effectName2, String effectRank2, Double price) {
        this.type = type;
        this.effectName1 = effectName1;
        this.effectRank1 = effectRank1;
        this.effectName2 = effectName2;
        this.effectRank2 = effectRank2;
        this.price = price;
    }
}
