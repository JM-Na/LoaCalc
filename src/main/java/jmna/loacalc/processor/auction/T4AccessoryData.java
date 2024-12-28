package jmna.loacalc.processor.auction;

import jmna.loacalc.feign.client.auctions.items.request.EtcOption;
import jmna.loacalc.feign.client.auctions.items.request.RequestAuctionItems;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



@Getter
public enum T4AccessoryData {
    NECKLACE_L_1("목걸이", "하 단일", "추가 피해 %", "하", null, null, null),
    NECKLACE_L_2("목걸이", "하 단일", "적에게 주는 피해 %", "하", null, null, null),
    NECKLACE_M_1("목걸이", "중 단일", "추가 피해 %", "중", null, null, null),
    NECKLACE_M_2("목걸이", "중 단일", "적에게 주는 피해 %", "중", null, null, null),
    NECKLACE_H_1("목걸이", "상 단일", "추가 피해 %", "상", null, null, null),
    NECKLACE_H_2("목걸이", "상 단일", "적에게 주는 피해 %", "상", null, null, null),
    NECKLACE_LL("목걸이", "하하", "추가 피해 %", "하", "적에게 주는 피해 %", "하", null),
    NECKLACE_ML_1("목걸이", "중하", "추가 피해 %", "중", "적에게 주는 피해 %", "하", null),
    NECKLACE_ML_2("목걸이", "중하", "적에게 주는 피해 %", "중", "추가 피해 %", "하", null),
    NECKLACE_HL_1("목걸이", "상하", "추가 피해 %", "상", "적에게 주는 피해 %", "하", null),
    NECKLACE_HL_2("목걸이", "상하", "적에게 주는 피해 %", "상", "추가 피해 %", "하", null),
    NECKLACE_MM("목걸이", "중중", "추가 피해 %", "중", "적에게 주는 피해 %", "중", null),
    NECKLACE_HM_1("목걸이", "상중", "추가 피해 %", "상", "적에게 주는 피해 %", "중", null),
    NECKLACE_HM_2("목걸이", "상중", "적에게 주는 피해 %", "상", "추가 피해 %", "중", null),
    NECKLACE_HH("목걸이", "상상", "추가 피해 %", "상", "적에게 주는 피해 %", "상", null),
    EARRING_L_1("귀걸이", "하 단일", "공격력 %", "하", null, null, null),
    EARRING_L_2("귀걸이","하 단일", "무기 공격력 %", "하", null, null, null),
    EARRING_M_1("귀걸이","중 단일", "공격력 %", "중", null, null, null),
    EARRING_M_2("귀걸이","중 단일", "무기 공격력 %", "중", null, null, null),
    EARRING_H_1("귀걸이","상 단일", "공격력 %", "상", null, null, null),
    EARRING_H_2("귀걸이","상 단일", "무기 공격력 %", "상", null, null, null),
    EARRING_LL("귀걸이","하하", "공격력 %", "하", "무기 공격력 %", "하", null),
    EARRING_ML_1("귀걸이","중하", "공격력 %", "중", "무기 공격력 %", "하", null),
    EARRING_ML_2("귀걸이","중하", "무기 공격력 %", "중", "공격력 %", "하", null),
    EARRING_HL_1("귀걸이","상하", "공격력 %", "상", "무기 공격력 %", "하", null),
    EARRING_HL_2("귀걸이","상하", "무기 공격력 %", "상", "공격력 %", "하", null),
    EARRING_MM("귀걸이","중중", "공격력 %", "중", "무기 공격력 %", "중", null),
    EARRING_HM_1("귀걸이","상중", "공격력 %", "상", "무기 공격력 %", "중", null),
    EARRING_HM_2("귀걸이","상중", "무기 공격력 %", "상", "공격력 %", "중", null),
    EARRING_HH("귀걸이","상상", "공격력 %", "상", "무기 공격력 %", "상", null),
    RING_L_1("반지","하 단일", "치명타 적중률 %", "하", null, null, null),
    RING_L_2("반지","하 단일", "치명타 피해 %", "하", null, null, null),
    RING_M_1("반지","중 단일", "치명타 적중률 %", "중", null, null, null),
    RING_M_2("반지","중 단일", "치명타 피해 %", "중", null, null, null),
    RING_H_1("반지","상 단일", "치명타 적중률 %", "상", null, null, null),
    RING_H_2("반지","상 단일", "치명타 피해 %", "상", null, null, null),
    RING_LL("반지","하하", "치명타 적중률 %", "하", "치명타 피해 %", "하", null),
    RING_ML_1("반지","중하", "치명타 적중률 %", "중", "치명타 피해 %", "하", null),
    RING_ML_2("반지","중하", "치명타 피해 %", "중", "치명타 적중률 %", "하", null),
    RING_HL_1("반지","상하", "치명타 적중률 %", "상", "치명타 피해 %", "하", null),
    RING_HL_2("반지","상하", "치명타 피해 %", "상", "치명타 적중률 %", "하", null),
    RING_MM("반지","중중", "치명타 적중률 %", "중", "치명타 피해 %", "중", null),
    RING_HM_1("반지","상중", "치명타 적중률 %", "상", "치명타 피해 %", "중", null),
    RING_HM_2("반지","상중", "치명타 피해 %", "상", "치명타 적중률 %", "중", null),
    RING_HH("반지","상상", "치명타 적중률 %", "상", "치명타 피해 %", "상", null),
    ;

    private final String partName;
    private final String type;
    private final String effectName1;
    private final String effectRank1;
    private final String effectName2;
    private final String effectRank2;
    @Setter
    private Integer price;

    T4AccessoryData(String partName, String type, String effectName1, String effectRank1, String effectName2, String effectRank2, Integer price) {
        this.partName = partName;
        this.type = type;
        this.effectName1 = effectName1;
        this.effectRank1 = effectRank1;
        this.effectName2 = effectName2;
        this.effectRank2 = effectRank2;
        this.price = price;
    }

    public static List<T4AccessoryData> getListOfData() {
        return Arrays.stream(values()).toList();
    }

    public static List<EtcOption> getListOfOptionObject(T4AccessoryData target) {

        List<EtcOption> etcOptionList = new ArrayList<>();

        AccessoryOptionType type1 = AccessoryOptionType.findByTypeAndOptionRank(target.getEffectName1(), target.getEffectRank1());

        if (type1 != null) {
            EtcOption etcOption = new EtcOption(type1);
            etcOptionList.add(etcOption);
        }

        AccessoryOptionType type2 = AccessoryOptionType.findByTypeAndOptionRank(target.getEffectName2(), target.getEffectRank2());

        if (type2 != null) {
            EtcOption etcOption = new EtcOption(type2);
            etcOptionList.add(etcOption);
        }

        System.out.println("etcOptionList = " + etcOptionList);

        return etcOptionList;
    }

    public static void setPrice(T4AccessoryData target, Integer price) {
        target.setPrice(price);
    }

    public static RequestAuctionItems setRequestAuctionItem(T4AccessoryData target){
        RequestAuctionItems requestAuctionItems = new RequestAuctionItems();
        requestAuctionItems.setCategoryCode(AuctionCategoryCode.getPartCode(target.getPartName()));
        requestAuctionItems.setItemTier(4);
        requestAuctionItems.setItemGrade("고대");

        List<EtcOption> etcOptionList = getListOfOptionObject(target);
        requestAuctionItems.setEtcOptions(etcOptionList);

        return requestAuctionItems;
    }

}
