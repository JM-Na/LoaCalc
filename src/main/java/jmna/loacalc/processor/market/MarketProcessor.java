package jmna.loacalc.processor.market;

import jmna.loacalc.calculator.engraving.RelicEngravingBookData;
import jmna.loacalc.calculator.hone.HoneIngredients;
import jmna.loacalc.entity.RelicEngravingBook;
import jmna.loacalc.feign.client.markets.MarketClient;
import jmna.loacalc.feign.client.markets.MarketItems;
import jmna.loacalc.feign.client.markets.items.Item;
import jmna.loacalc.feign.client.markets.items.RequestMarketItems;
import jmna.loacalc.processor.armory.avatar.CharacterAvatar;
import jmna.loacalc.repository.RelicEngravingBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MarketProcessor {

    private final MarketClient marketClient;
    private final RelicEngravingBookRepository engravingBookRepository;

    public void initPrice() {

        List<String> ingredientNames = List.of("파괴", "수호", "돌파석", "파편", "융화", "태양의", "숨결");

        for (String ingredientName : ingredientNames) {
            RequestMarketItems requestMarketItems = new RequestMarketItems();
            requestMarketItems.setSort("GRADE");
            requestMarketItems.setCategoryCode(50000);
            requestMarketItems.setCharacterClass(null);
            requestMarketItems.setItemTier(null);
            requestMarketItems.setItemGrade(null);
            requestMarketItems.setSortCondition("ASC");

            requestMarketItems.setItemName(ingredientName);

            List<Item> items = marketClient.getMarketItems(requestMarketItems).getItems();

            for (Item item : items) {
                String name = item.getName();
                String icon = item.getIcon();
                Double yDayAvgPrice = item.getYDayAvgPrice();

                HoneIngredients.setIconAndPrice(name, icon, yDayAvgPrice);
            }
        }
    }

    public void getLegendaryAvatar(String className, CharacterAvatar characterAvatar) {

        List<Integer> avatarCodes = new java.util.ArrayList<>();

        if (!characterAvatar.getWeapon())
            avatarCodes.add(20005);
        if (!characterAvatar.getHead())
            avatarCodes.add(20010);
        if (!characterAvatar.getChest())
            avatarCodes.add(20050);
        if (!characterAvatar.getPants())
            avatarCodes.add(20060);

        System.out.println("avatarCodes = " + avatarCodes);

        int sum = 0;

        for (Integer avatarCode : avatarCodes) {
            RequestMarketItems requestMarketItems = new RequestMarketItems();
            requestMarketItems.setSort("GRADE");
            requestMarketItems.setCategoryCode(avatarCode);
            requestMarketItems.setCharacterClass(className);
            requestMarketItems.setItemTier(null);
            requestMarketItems.setItemGrade("전설");
            requestMarketItems.setItemName(null);
            requestMarketItems.setSortCondition(null);

            MarketItems marketItems = marketClient.getMarketItems(requestMarketItems);
            System.out.println("marketItems = " + marketItems);
            System.out.println("avatarCode = " + avatarCode);
            Integer price = marketItems.getItems().get(0).getCurrentMinPrice();
            System.out.println("price = " + price);
            sum += price;
        }

        System.out.println("sum = " + sum);
    }

//    public void initEngravingBookPrice() {
//        for (int pageNo = 1; pageNo <= 2; pageNo++) {
//            RequestMarketItems baseMarketRequest = createBaseMarketRequest(pageNo);
//            List<Item> items = marketClient.getMarketItems(baseMarketRequest).getItems();
//
//            for (Item item : items) {
//                String name = item.getName();
//                Double yDayAvgPrice = item.getYDayAvgPrice();
//                RelicEngravingBookData.initPrice(name, yDayAvgPrice);
//            }
//        }
//    }

    public void initEngravingBookPrice() {
        for (int pageNo = 1; pageNo <= 2; pageNo++) {
            RequestMarketItems baseMarketRequest = createBaseMarketRequest(pageNo);
            List<Item> items = marketClient.getMarketItems(baseMarketRequest).getItems();

            for (Item item : items) {
                String name = item.getName();
                Double yDayAvgPrice = item.getYDayAvgPrice();

                engravingBookRepository.save(new RelicEngravingBook(name, yDayAvgPrice));
            }
        }
    }

    private RequestMarketItems createBaseMarketRequest(Integer page) {
        RequestMarketItems requestMarketItems = new RequestMarketItems();
        requestMarketItems.setSort("YDAY_AVG_PRICE");
        requestMarketItems.setCategoryCode(40000);
        requestMarketItems.setCharacterClass(null);
        requestMarketItems.setItemTier(null);
        requestMarketItems.setItemGrade("유물");
        requestMarketItems.setSortCondition("DESC");
        requestMarketItems.setPageNo(page);
        return requestMarketItems;
    }
}
