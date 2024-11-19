package jmna.loacalc.processor.market;

import jmna.loacalc.feign.client.markets.MarketClient;
import jmna.loacalc.feign.client.markets.MarketItems;
import jmna.loacalc.feign.client.markets.items.Item;
import jmna.loacalc.feign.client.markets.items.RequestMarketItems;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MarketProcessorTest {

    @Autowired
    private MarketClient marketClient;

    @Test
    void 파괴석() {

        RequestMarketItems requestMarketItems = new RequestMarketItems();
        requestMarketItems.setSort("GRADE");
        requestMarketItems.setCategoryCode(50000);
        requestMarketItems.setCharacterClass(null);
        requestMarketItems.setItemTier(null);
        requestMarketItems.setItemGrade(null);
        requestMarketItems.setSortCondition("ASC");
        requestMarketItems.setItemName("융화");

        MarketItems marketItems = marketClient.getMarketItems(requestMarketItems);
        System.out.println("marketItems = " + marketItems);
//        List<Item> items = marketItems.getItems();
//
//        for (Item item : items) {
//            String name = item.getName();
//            String icon = item.getIcon();
//            Double yDayAvgPrice = item.getYDayAvgPrice();
//
//            HoneIngredients.setIconAndPrice(name, icon, yDayAvgPrice);
//        }
    }
}