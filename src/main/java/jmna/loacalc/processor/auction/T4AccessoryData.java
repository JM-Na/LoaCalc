package jmna.loacalc.processor.auction;

import jmna.loacalc.feign.client.auctions.items.request.EtcOption;
import jmna.loacalc.feign.client.auctions.items.request.RequestAuctionItems;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Getter
public enum T4AccessoryData {
    NECKLACE_L_1("목걸이", "하 단일", "추가 피해 %", "하", null, null),
    NECKLACE_L_2("목걸이", "하 단일", "적에게 주는 피해 %", "하", null, null),
    NECKLACE_M_1("목걸이", "중 단일", "추가 피해 %", "중", null, null),
    NECKLACE_M_2("목걸이", "중 단일", "적에게 주는 피해 %", "중", null, null),
    NECKLACE_H_1("목걸이", "상 단일", "추가 피해 %", "상", null, null),
    NECKLACE_H_2("목걸이", "상 단일", "적에게 주는 피해 %", "상", null, null),
    NECKLACE_LL("목걸이", "하하", "추가 피해 %", "하", "적에게 주는 피해 %", "하"),
    NECKLACE_ML_1("목걸이", "중하", "추가 피해 %", "중", "적에게 주는 피해 %", "하"),
    NECKLACE_ML_2("목걸이", "중하", "적에게 주는 피해 %", "중", "추가 피해 %", "하"),
    NECKLACE_HL_1("목걸이", "상하", "추가 피해 %", "상", "적에게 주는 피해 %", "하"),
    NECKLACE_HL_2("목걸이", "상하", "적에게 주는 피해 %", "상", "추가 피해 %", "하"),
    NECKLACE_MM("목걸이", "중중", "추가 피해 %", "중", "적에게 주는 피해 %", "중"),
    NECKLACE_HM_1("목걸이", "상중", "추가 피해 %", "상", "적에게 주는 피해 %", "중"),
    NECKLACE_HM_2("목걸이", "상중", "적에게 주는 피해 %", "상", "추가 피해 %", "중"),
    NECKLACE_HH("목걸이", "상상", "추가 피해 %", "상", "적에게 주는 피해 %", "상"),
    EARRING_L_1("귀걸이", "하 단일", "공격력 %", "하", null, null),
    EARRING_L_2("귀걸이", "하 단일", "무기 공격력 %", "하", null, null),
    EARRING_M_1("귀걸이", "중 단일", "공격력 %", "중", null, null),
    EARRING_M_2("귀걸이", "중 단일", "무기 공격력 %", "중", null, null),
    EARRING_H_1("귀걸이", "상 단일", "공격력 %", "상", null, null),
    EARRING_H_2("귀걸이", "상 단일", "무기 공격력 %", "상", null, null),
    EARRING_LL("귀걸이", "하하", "공격력 %", "하", "무기 공격력 %", "하"),
    EARRING_ML_1("귀걸이", "중하", "공격력 %", "중", "무기 공격력 %", "하"),
    EARRING_ML_2("귀걸이", "중하", "무기 공격력 %", "중", "공격력 %", "하"),
    EARRING_HL_1("귀걸이", "상하", "공격력 %", "상", "무기 공격력 %", "하"),
    EARRING_HL_2("귀걸이", "상하", "무기 공격력 %", "상", "공격력 %", "하"),
    EARRING_MM("귀걸이", "중중", "공격력 %", "중", "무기 공격력 %", "중"),
    EARRING_HM_1("귀걸이", "상중", "공격력 %", "상", "무기 공격력 %", "중"),
    EARRING_HM_2("귀걸이", "상중", "무기 공격력 %", "상", "공격력 %", "중"),
    EARRING_HH("귀걸이", "상상", "공격력 %", "상", "무기 공격력 %", "상"),
    RING_L_1("반지", "하 단일", "치명타 적중률 %", "하", null, null),
    RING_L_2("반지", "하 단일", "치명타 피해 %", "하", null, null),
    RING_M_1("반지", "중 단일", "치명타 적중률 %", "중", null, null),
    RING_M_2("반지", "중 단일", "치명타 피해 %", "중", null, null),
    RING_H_1("반지", "상 단일", "치명타 적중률 %", "상", null, null),
    RING_H_2("반지", "상 단일", "치명타 피해 %", "상", null, null),
    RING_LL("반지", "하하", "치명타 적중률 %", "하", "치명타 피해 %", "하"),
    RING_ML_1("반지", "중하", "치명타 적중률 %", "중", "치명타 피해 %", "하"),
    RING_ML_2("반지", "중하", "치명타 피해 %", "중", "치명타 적중률 %", "하"),
    RING_HL_1("반지", "상하", "치명타 적중률 %", "상", "치명타 피해 %", "하"),
    RING_HL_2("반지", "상하", "치명타 피해 %", "상", "치명타 적중률 %", "하"),
    RING_MM("반지", "중중", "치명타 적중률 %", "중", "치명타 피해 %", "중"),
    RING_HM_1("반지", "상중", "치명타 적중률 %", "상", "치명타 피해 %", "중"),
    RING_HM_2("반지", "상중", "치명타 피해 %", "상", "치명타 적중률 %", "중"),
    RING_HH("반지", "상상", "치명타 적중률 %", "상", "치명타 피해 %", "상"),
    ;

    private final String partName;
    private final String type;
    private final String effectName1;
    private final String effectRank1;
    private final String effectName2;
    private final String effectRank2;
    
    T4AccessoryData(String partName, String type, String effectName1, String effectRank1, String effectName2, String effectRank2) {
        this.partName = partName;
        this.type = type;
        this.effectName1 = effectName1;
        this.effectRank1 = effectRank1;
        this.effectName2 = effectName2;
        this.effectRank2 = effectRank2;
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

        return etcOptionList;
    }

    public static RequestAuctionItems setRequestAuctionItem(T4AccessoryData target) {
        RequestAuctionItems requestAuctionItems = new RequestAuctionItems();
        requestAuctionItems.setCategoryCode(AuctionCategoryCode.getPartCode(target.getPartName()));
        requestAuctionItems.setItemTier(4);
        requestAuctionItems.setItemGrade("고대");
        requestAuctionItems.setSort("BUY_PRICE");
        requestAuctionItems.setSortCondition("ASC");
        requestAuctionItems.setItemGradeQuality(70);

        List<EtcOption> etcOptionList = getListOfOptionObject(target);
        requestAuctionItems.setEtcOptions(etcOptionList);

        return requestAuctionItems;
    }


    public static T4AccessoryData findTypeByOptions(AccessoryOptionType option1) {
        return Arrays.stream(values())
                .filter(value -> value.effectName1.equals(option1.getType())
                        && value.effectRank1.equals(option1.getOptionRank()))
                .findFirst()
                .orElse(null);
    }

    public static T4AccessoryData findTypeByOptions(AccessoryOptionType option1, AccessoryOptionType option2) {
        T4AccessoryData target = Arrays.stream(values())
                .filter(value -> value.effectName1.equals(option1.getType())
                        && value.effectRank1.equals(option1.getOptionRank())
                        && Objects.equals(value.effectName2, option2.getType())
                        && Objects.equals(value.effectRank2, option2.getOptionRank()))
                .findFirst()
                .orElse(null);
        // 해당하는 값을 찾을 수 없을 시 주어진 두 개의 옵션 위치를 바꿔 다시 찾는다.
        if (target == null) {
            return Arrays.stream(values())
                    .filter(value -> value.effectName1.equals(option2.getType())
                            && value.effectRank1.equals(option2.getOptionRank())
                            && Objects.equals(value.effectName2, option1.getType())
                            && Objects.equals(value.effectRank2, option1.getOptionRank()))
                    .findFirst()
                    .orElse(null);
        }
        return target;
    }

    public static T4AccessoryData findTypeByOptions(List<AccessoryOptionType> optionList) {
        if (optionList.size() == 2) {
            return findTypeByOptions(optionList.get(0), optionList.get(1));
        } else if (optionList.size() == 1) {
            return findTypeByOptions(optionList.get(0));
        } else {
            System.out.println("유효하지 않은 요청입니다. : 옵션이 하나도 주어지지 않음.");
            return null;
        }
    }

    public static List<String> findOptionsByType(String partName) {
        T4AccessoryData target = Arrays.stream(values())
                .filter(value -> value.partName.equals(partName) && value.type.equals("상상"))
                .findFirst()
                .orElse(null);

        if (target == null)
            return null;

        return List.of(target.getEffectName1(), target.getEffectName2());
    }

    public static String getOptionInfo(T4AccessoryData target) {
        String effectName1 = target.getEffectName1();
        String effectRank1 = target.getEffectRank1();
        String effectName2 = target.getEffectName2();
        String effectRank2 = target.getEffectRank2();

        switch (effectName1){
            case "적에게 주는 피해 %" -> effectName1 = "적추피 %";
            case "추가 피해 %" -> effectName1 = "추피 %";
            case "무기 공격력 %" -> effectName1 = "무공 %";
            case "공격력 %" -> effectName1 = "공 %";
            case "치명타 적중률 %" -> effectName1 = "치적 %";
            case "치명타 피해 %" -> effectName1 = "치피 %";
        }

        String optionInfo = effectName1 + " " + effectRank1;

        if (effectName2 != null) {

            switch (effectName2) {
                case "적에게 주는 피해 %" -> effectName2 = "적추피 %";
                case "추가 피해 %" -> effectName2 = "추피 %";
                case "무기 공격력 %" -> effectName2 = "무공 %";
                case "공격력 %" -> effectName2 = "공 %";
                case "치명타 적중률 %" -> effectName2 = "치적 %";
                case "치명타 피해 %" -> effectName2 = "치피 %";
            }

            optionInfo += ", " + effectName2 + " " + effectRank2;
        }

        return optionInfo;
    }
}
