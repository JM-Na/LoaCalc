package jmna.loacalc.processor.auction;

import jmna.loacalc.feign.client.auctions.AuctionClient;
import jmna.loacalc.feign.client.auctions.AuctionItems;
import jmna.loacalc.feign.client.auctions.items.request.EtcOption;
import jmna.loacalc.feign.client.auctions.items.request.RequestAuctionItems;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuctionProcessor {

    private final AuctionClient auctionClient;

    public void initPrice() {
        List<T4NecklaceData> list = T4NecklaceData.getListOfData();

        for (T4NecklaceData t4NecklaceData : list) {

            RequestAuctionItems requestAuctionItems = new RequestAuctionItems();
            requestAuctionItems.setCategoryCode(200010);
            requestAuctionItems.setItemTier(4);
            List<EtcOption> etcOptionList = T4NecklaceData.getListOfOptionObject(t4NecklaceData);
            requestAuctionItems.setEtcOptions(etcOptionList);

            AuctionItems auctionItems = auctionClient.getAuctionItems(requestAuctionItems);

            Integer price = auctionItems.getItems().get(0).getAuctionInfo().getBuyPrice();

            T4NecklaceData.setPrice(t4NecklaceData, price);
        }

    }

}
