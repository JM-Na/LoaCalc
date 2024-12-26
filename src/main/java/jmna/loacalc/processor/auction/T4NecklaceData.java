package jmna.loacalc.processor.auction;

import jmna.loacalc.feign.client.auctions.items.request.EtcOption;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public enum T4NecklaceData {
    NECKLACE_L_1("하 단일", "추가 피해 %", "하", null, null, null),
    NECKLACE_L_2("하 단일", "적에게 주는 피해 %", "하", null, null, null),
    NECKLACE_M_1("중 단일", "추가 피해 %", "중", null, null, null),
    NECKLACE_M_2("중 단일", "적에게 주는 피해 %", "중", null, null, null),
    NECKLACE_H_1("상 단일", "추가 피해 %", "상", null, null, null),
    NECKLACE_H_2("상 단일", "적에게 주는 피해 %", "상", null, null, null),
    NECKLACE_LL("하하", "추가 피해 %", "하", "적에게 주는 피해 %", "하", null),
    NECKLACE_ML_1("중하", "추가 피해 %", "중", "적에게 주는 피해 %", "하", null),
    NECKLACE_ML_2("중하", "적에게 주는 피해 %", "중", "추가 피해 %", "하", null),
    NECKLACE_HL_1("상하", "추가 피해 %", "상", "적에게 주는 피해 %", "하", null),
    NECKLACE_HL_2("상하", "적에게 주는 피해 %", "상", "추가 피해 %", "하", null),
    NECKLACE_MM("중중", "추가 피해 %", "중", "적에게 주는 피해 %", "중", null),
    NECKLACE_HM_1("상중", "추가 피해 %", "상", "적에게 주는 피해 %", "중", null),
    NECKLACE_HM_2("상중", "적에게 주는 피해 %", "상", "추가 피해 %", "중", null),
    NECKLACE_HH("상상", "추가 피해 %", "상", "적에게 주는 피해 %", "상", null),
    ;

    private final String type;
    private final String effectName1;
    private final String effectRank1;
    private final String effectName2;
    private final String effectRank2;
    @Setter
    private Integer price;

    T4NecklaceData(String type, String effectName1, String effectRank1, String effectName2, String effectRank2, Integer price) {
        this.type = type;
        this.effectName1 = effectName1;
        this.effectRank1 = effectRank1;
        this.effectName2 = effectName2;
        this.effectRank2 = effectRank2;
        this.price = price;
    }

    public static List<T4NecklaceData> getListOfData() {
        return Arrays.stream(values()).toList();
    }

    public static List<EtcOption> getListOfOptionObject(T4NecklaceData target) {

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

    public static void setPrice(T4NecklaceData target, Integer price) {
        target.setPrice(price);
    }

}
