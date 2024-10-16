package jmna.loacalc.feign.client.markets;

import jmna.loaapi.feign.client.markets.items.RequestMarketItems;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MarketServiceTest {

    @Autowired
    private MarketService marketService;

    @Test
    void getMarketsOptions() {
        MarketOptions marketOptions = marketService.getMarketsOptions();

        System.out.println("marketsOptions = " + marketOptions);

        Assertions.assertThat(marketOptions).isNotNull();
    }

    @Test
    void getMarketsItemsByID() {
        List<MarketItemsById> marketsItemByIDS = marketService.getMarketsItemsById(101061);
        //101061 회복약
        //101062 고급회복약
        //101063 정령의회복약
        //101917 빛나는정령의회복약
        System.out.println("marketsItems = " + marketsItemByIDS);

        Assertions.assertThat(marketsItemByIDS).isNotNull();
    }

    @Test
    void getMarketItems() {
        RequestMarketItems requestMarketItems = new RequestMarketItems();
        requestMarketItems.setSort("GRADE");
        requestMarketItems.setCategoryCode(60200);
        requestMarketItems.setCharacterClass(null);
        requestMarketItems.setItemTier(null);
        requestMarketItems.setItemGrade(null);
        requestMarketItems.setItemName(null);
        requestMarketItems.setSortCondition("ASC");

        MarketItems marketItems = marketService.getMarketsItems(requestMarketItems);

        System.out.println("marketsItems = " + marketItems);

        Assertions.assertThat(marketItems).isNotNull();
    }
}