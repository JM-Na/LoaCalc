package jmna.loacalc.processor.market;

import jmna.loacalc.feign.client.markets.MarketClient;
import jmna.loacalc.feign.client.markets.MarketItems;
import jmna.loacalc.feign.client.markets.items.Item;
import jmna.loacalc.feign.client.markets.items.RequestMarketItems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MarketProcessor {

    private final MarketClient marketClient;

    public void getDestructionStone() {


        List<String> ingredientNames = List.of("파괴", "수호", "돌파석", "파편", "융화");

        for (String ingredientName : ingredientNames) {
            RequestMarketItems requestMarketItems = new RequestMarketItems();
            requestMarketItems.setSort("GRADE");
            requestMarketItems.setCategoryCode(50000);
            requestMarketItems.setCharacterClass(null);
            requestMarketItems.setItemTier(null);
            requestMarketItems.setItemGrade(null);
            requestMarketItems.setSortCondition("ASC");

            requestMarketItems.setItemName("파괴");

            List<Item> items = marketClient.getMarketItems(requestMarketItems).getItems();

            for (Item item : items) {
                String name = item.getName();
                String icon = item.getIcon();
                Double yDayAvgPrice = item.getYDayAvgPrice();

                HoneIngredients.setIconAndPrice(name, icon, yDayAvgPrice);
            }
        }
    }
}
