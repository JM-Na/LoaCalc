package jmna.loacalc.processor.auction;

public enum T4RingData {
    RING_L_1("하 단일", "치명타 적중률 %", "하", null, null, null),
    RING_L_2("하 단일", "치명타 피해 %", "하", null, null, null),
    RING_M_1("중 단일", "치명타 적중률 %", "중", null, null, null),
    RING_M_2("중 단일", "치명타 피해 %", "중", null, null, null),
    RING_H_1("상 단일", "치명타 적중률 %", "상", null, null, null),
    RING_H_2("상 단일", "치명타 피해 %", "상", null, null, null),
    RING_LL("하하", "치명타 적중률 %", "하", "치명타 피해 %", "하", null),
    RING_ML_1("중하", "치명타 적중률 %", "중", "치명타 피해 %", "하", null),
    RING_ML_2("중하", "치명타 피해 %", "중", "치명타 적중률 %", "하", null),
    RING_HL_1("상하", "치명타 적중률 %", "상", "치명타 피해 %", "하", null),
    RING_HL_2("상하", "치명타 피해 %", "상", "치명타 적중률 %", "하", null),
    RING_MM("중중", "치명타 적중률 %", "중", "치명타 피해 %", "중", null),
    RING_HM_1("상중", "치명타 적중률 %", "상", "치명타 피해 %", "중", null),
    RING_HM_2("상중", "치명타 피해 %", "상", "치명타 적중률 %", "중", null),
    RING_HH("상상", "치명타 적중률 %", "상", "치명타 피해 %", "상", null),
    ;

    private final String type;
    private final String effectName1;
    private final String effectRank1;
    private final String effectName2;
    private final String effectRank2;
    private Double price;

    T4RingData(String type, String effectName1, String effectRank1, String effectName2, String effectRank2, Double price) {
        this.type = type;
        this.effectName1 = effectName1;
        this.effectRank1 = effectRank1;
        this.effectName2 = effectName2;
        this.effectRank2 = effectRank2;
        this.price = price;
    }

}
