package jmna.loacalc.processor.auction;

import jmna.loacalc.feign.client.auctions.items.request.EtcOption;
import lombok.Getter;

import java.util.Arrays;

public enum AccessoryOptionType {
    ATK_POWER_PERCENT_L("공격력 %", 45, "하", 1, 0.40, "귀걸이"),
    ATK_POWER_PERCENT_M("공격력 %", 45, "중", 10, 0.95, "귀걸이"),
    ATK_POWER_PERCENT_H("공격력 %", 45, "상", 12, 1.55, "귀걸이"),
    WEAPON_POWER_PERCENT_L("무기 공격력 %", 46, "하", 4, 0.80, "귀걸이"),
    WEAPON_POWER_PERCENT_M("무기 공격력 %", 46, "중", 10, 1.80, "귀걸이"),
    WEAPON_POWER_PERCENT_H("무기 공격력 %", 46, "상", 12, 3.00, "귀걸이"),
    ADD_DMG_PERCENT_L("추가 피해 %", 41, "하", 4, 0.70, "목걸이"),
    ADD_DMG_PERCENT_M("추가 피해 %", 41, "중", 9, 1.60, "목걸이"),
    ADD_DMG_PERCENT_H("추가 피해 %", 41, "상", 11, 2.60, "목걸이"),
    OUTGOING_DMG_PERCENT_L("적에게 주는 피해 %", 42, "하", 5, 0.55, "목걸이"),
    OUTGOING_DMG_PERCENT_M("적에게 주는 피해 %", 42, "중", 10, 1.20, "목걸이"),
    OUTGOING_DMG_PERCENT_H("적에게 주는 피해 %", 42, "상", 12, 2.00, "목걸이"),
    CRIT_RATE_PERCENT_L("치명타 적중률 %", 49, "하", 4, 0.40, "반지"),
    CRIT_RATE_PERCENT_M("치명타 적중률 %", 49, "중", 10, 0.95, "반지"),
    CRIT_RATE_PERCENT_H("치명타 적중률 %", 49, "상", 12, 1.55, "반지"),
    CRIT_DMG_PERCENT_L("치명타 피해 %", 50, "하", 5, 1.10, "반지"),
    CRIT_DMG_PERCENT_M("치명타 피해 %", 50, "중", 10, 2.40, "반지"),
    CRIT_DMG_PERCENT_H("치명타 피해 %", 50, "상", 12, 4.00, "반지"),

    ATK_POWER_L("공격력 +", 45, "하", 9, 80, "공용"),
    ATK_POWER_M("공격력 +", 45, "중", 11, 195, "공용"),
    ATK_POWER_H("공격력 +", 45, "상", 12, 390, "공용"),
    WEAPON_POWER_L("무기 공격력 +", 46, "하", 9, 195, "공용"),
    WEAPON_POWER_M("무기 공격력 +", 46, "중", 11, 480, "공용"),
    WEAPON_POWER_H("무기 공격력 +", 46, "상", 12, 960, "공용"),
    ;

    private final String type;
    @Getter
    private final int typeCode;
    private final String optionRank;
    @Getter
    private final int optionCode; //상, 중, 하를 구분하는 코드. 옵션의 종류에 따라 상, 중, 하를 지칭하는 값이 다름.
    @Getter
    private final double increment; //해당 옵션의 상승값
    private final String partName;

    AccessoryOptionType(String type, int typeCode, String optionRank, int optionCode, double increment, String partName) {
        this.type = type;
        this.typeCode = typeCode;
        this.optionRank = optionRank;
        this.optionCode = optionCode;
        this.increment = increment;
        this.partName = partName;
    }

    private static AccessoryOptionType of(String type, String optionRank) {
        return Arrays.stream(values())
                .filter(value -> value.type.equals(type) && value.optionRank.equals(optionRank))
                .findFirst()
                .orElse(null);
    }

    public static AccessoryOptionType findByTypeAndOptionRank(String type, String optionRank) {
        return AccessoryOptionType.of(type, optionRank);
    }

    public static EtcOption getOptionObject(String type, String optionRank) {
        return new EtcOption(AccessoryOptionType.of(type, optionRank));
    }
}
