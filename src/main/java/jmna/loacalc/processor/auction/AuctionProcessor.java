package jmna.loacalc.processor.auction;

import jmna.loacalc.feign.client.auctions.AuctionClient;
import jmna.loacalc.feign.client.auctions.AuctionItems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuctionProcessor {

    private final AuctionClient auctionClient;

    public void initPrice() {
        List<T4AccessoryData> list = T4AccessoryData.getListOfData();

        for (T4AccessoryData t4AccessoryData : list) {

            AuctionItems auctionItems = auctionClient.getAuctionItems(T4AccessoryData.setRequestAuctionItem(t4AccessoryData));

            Integer price = auctionItems.getItems().get(0).getAuctionInfo().getBuyPrice();

            System.out.println(auctionItems.getItems().get(0));
            System.out.println("price = " + price);

            if(price == null)
                price = auctionItems.getItems().get(0).getAuctionInfo().getBidStartPrice();

            T4AccessoryData.setPrice(t4AccessoryData, price);
        }

    }

}
