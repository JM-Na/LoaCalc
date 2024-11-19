package jmna.loacalc.feign.client.markets;

import jmna.loacalc.feign.client.markets.items.RequestMarketItems;
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
        List<MarketItemsById> marketsItemByIDS = marketService.getMarketsItemsById(66112544);
        //101061 회복약
        //101062 고급회복약
        //101063 정령의회복약
        //101917 빛나는정령의회복약
        //
        //66102104 수호강석
        //66102105 정제된 수호강석
        //66102106 운명의 수호석
        //
        //66102004 파괴강석
        //66102005 정제된 파괴강석
        //66102006 운명의 파괴석

        //66110223 경이로운 명예의 돌파석
        //66110224 찬란한 명예의 돌파석
        //66110225 운명의 돌파석
        //
        //66111121 태양의 은총
        //66111122 태양의 축복
        //66111123 태양의 가호
        //66111132 빙하의 숨결
        //
        //66112541 야금술 쇠락 [13-15]
        //66112542 야금술 쇠락 [16-19]
        //66112543 야금술 업화 [11-14]
        //66112544 재봉술 쇠락 [13-15]
        //66112545 재봉술 쇠락 [16-19]
        //66112546 재봉술 업화 [11-14]
        System.out.println("marketsItems = " + marketsItemByIDS);

        Assertions.assertThat(marketsItemByIDS).isNotNull();
    }

    @Test
    void getMarketItems() {
        //회복 배틀아이템 60200
        //재련 재료 50000
        //아바타 20000
        //무기 아바타 20005
        //머리 아바타 20010
        //상의 아바타 20050
        //하의 아바타 20060

        RequestMarketItems requestMarketItems = new RequestMarketItems();
        requestMarketItems.setSort("GRADE");
        requestMarketItems.setCategoryCode(20010);
        requestMarketItems.setCharacterClass("워로드");
        requestMarketItems.setItemTier(null);
        requestMarketItems.setItemGrade("전설");
        requestMarketItems.setItemName(null);
        requestMarketItems.setSortCondition("ASC");
        //TODO 직업에 맞는 전설아바타도 받아올 수 있을지?

        MarketItems marketItems = marketService.getMarketsItems(requestMarketItems);

        System.out.println("marketsItems = " + marketItems);

        Assertions.assertThat(marketItems).isNotNull();
    }

    @Test
    void 재련재료확인() {
        RequestMarketItems requestMarketItems = new RequestMarketItems();
        requestMarketItems.setSort("GRADE");
        requestMarketItems.setCategoryCode(50000);
        requestMarketItems.setCharacterClass(null);
        requestMarketItems.setItemTier(null);
        requestMarketItems.setItemGrade(null);
        requestMarketItems.setItemName("파괴");
        requestMarketItems.setSortCondition("ASC");
        //TODO 직업에 맞는 전설아바타도 받아올 수 있을지?

        MarketItems marketItems = marketService.getMarketsItems(requestMarketItems);

        System.out.println("marketsItems = " + marketItems);

        Assertions.assertThat(marketItems).isNotNull();
    }
}