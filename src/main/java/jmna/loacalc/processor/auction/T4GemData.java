package jmna.loacalc.processor.auction;

import jmna.loacalc.feign.client.auctions.items.request.EtcOption;
import jmna.loacalc.feign.client.auctions.items.request.RequestAuctionItems;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum T4GemData {
    GEM_DEAL_1("1레벨 겁화의 보석", 8, null),
    GEM_DEAL_2("2레벨 겁화의 보석", 12, null),
    GEM_DEAL_3("3레벨 겁화의 보석", 16,null),
    GEM_DEAL_4("4레벨 겁화의 보석", 20, null),
    GEM_DEAL_5("5레벨 겁화의 보석", 24, null),
    GEM_DEAL_6("6레벨 겁화의 보석", 28, null),
    GEM_DEAL_7("7레벨 겁화의 보석", 32, null),
    GEM_DEAL_8("8레벨 겁화의 보석", 36, null),
    GEM_DEAL_9("9레벨 겁화의 보석", 40, null),
    GEM_DEAL_10("10레벨 겁화의 보석", 44, null),
    GEM_COOL_1("1레벨 작열의 보석", 6, null),
    GEM_COOL_2("2레벨 작열의 보석", 8, null),
    GEM_COOL_3("3레벨 작열의 보석", 10, null),
    GEM_COOL_4("4레벨 작열의 보석", 12, null),
    GEM_COOL_5("5레벨 작열의 보석", 14, null),
    GEM_COOL_6("6레벨 작열의 보석", 16, null),
    GEM_COOL_7("7레벨 작열의 보석", 18, null),
    GEM_COOL_8("8레벨 작열의 보석", 20, null),
    GEM_COOL_9("9레벨 작열의 보석", 22, null),
    GEM_COOL_10("10레벨 작열의 보석", 24, null),
    ;

    private final String name;
    private final int effect;
    @Setter
    private Integer price;

    T4GemData(String name, int effect, Integer price) {
        this.name = name;
        this.effect = effect;
        this.price = price;
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

    public static void setPrice(T4GemData target, Integer price) {
        target.setPrice(price);
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

}
