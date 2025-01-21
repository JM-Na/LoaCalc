package jmna.loacalc.processor.auction;

import jmna.loacalc.feign.client.auctions.items.request.RequestAuctionItems;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum T4GemData {
    GEM_DEAL_1("1레벨 겁화의 보석", 8, 0),
    GEM_DEAL_2("2레벨 겁화의 보석", 12, 0),
    GEM_DEAL_3("3레벨 겁화의 보석", 16, 0.1),
    GEM_DEAL_4("4레벨 겁화의 보석", 20, 0.2),
    GEM_DEAL_5("5레벨 겁화의 보석", 24, 0.3),
    GEM_DEAL_6("6레벨 겁화의 보석", 28, 0.45),
    GEM_DEAL_7("7레벨 겁화의 보석", 32, 0.6),
    GEM_DEAL_8("8레벨 겁화의 보석", 36, 0.8),
    GEM_DEAL_9("9레벨 겁화의 보석", 40, 1.0),
    GEM_DEAL_10("10레벨 겁화의 보석", 44, 1.2),
    GEM_COOL_1("1레벨 작열의 보석", 6, 0),
    GEM_COOL_2("2레벨 작열의 보석", 8, 0),
    GEM_COOL_3("3레벨 작열의 보석", 10, 0.1),
    GEM_COOL_4("4레벨 작열의 보석", 12, 0.2),
    GEM_COOL_5("5레벨 작열의 보석", 14, 0.3),
    GEM_COOL_6("6레벨 작열의 보석", 16, 0.45),
    GEM_COOL_7("7레벨 작열의 보석", 18, 0.6),
    GEM_COOL_8("8레벨 작열의 보석", 20, 0.8),
    GEM_COOL_9("9레벨 작열의 보석", 22, 1.0),
    GEM_COOL_10("10레벨 작열의 보석", 24, 1.2),
    ;

    private final String name;
    private final int effect;
    private final double basicAtk;

    T4GemData(String name, int effect, double basicAtk) {
        this.name = name;
        this.effect = effect;
        this.basicAtk = basicAtk;
    }

    private static T4GemData of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElse(null);
    }

    public static List<T4GemData> getListOfData() {
        return Arrays.stream(values()).toList();
    }

    public static RequestAuctionItems setRequestAuctionItem(T4GemData target) {
        RequestAuctionItems requestAuctionItems = new RequestAuctionItems();
        requestAuctionItems.setCategoryCode(210000);
        requestAuctionItems.setItemTier(4);
        requestAuctionItems.setItemName(target.getName());
        requestAuctionItems.setSort("BUY_PRICE");
        requestAuctionItems.setSortCondition("ASC");
        return requestAuctionItems;
    }

    public static T4GemData findDataByLvl(int targetLvl) {
        return Arrays.stream(values())
                .filter(value -> value.name.contains(targetLvl + "레벨 겁화의"))
                .findFirst().orElse(null);
    }

}
