package jmna.loacalc.feign.client.auctions;

import jmna.loacalc.feign.client.auctions.items.request.RequestAuctionItems;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuctionServiceTest {

    @Autowired
    private AuctionService auctionService;

    @Test
    void getAuctionOptions() {
        AuctionOptions auctionOptions = auctionService.getAuctionOptions();

        System.out.println("auctionOptions = " + auctionOptions);

        Assertions.assertThat(auctionOptions).isNotNull();
    }

    @Test
    void getAuctionItems() {
        RequestAuctionItems requestAuctionItems = new RequestAuctionItems();
        requestAuctionItems.setCategoryCode(200000); //팔찌
        requestAuctionItems.setItemTier(4);
        requestAuctionItems.setItemLevelMax(1800);

        AuctionItems auctionItems = auctionService.getAuctionItems(requestAuctionItems);

        System.out.println("auctionItems = " + auctionItems);

        Assertions.assertThat(auctionItems).isNotNull();
    }
}