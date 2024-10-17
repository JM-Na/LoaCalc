package jmna.loacalc.feign.client.auctions;

import jmna.loacalc.feign.client.auctions.items.request.RequestAuctionItems;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {

    private final AuctionClient auctionClient;


    public AuctionService(AuctionClient auctionClient) {
        this.auctionClient = auctionClient;
    }

    public AuctionOptions getAuctionOptions() {
        return auctionClient.getAuctionOptions();
    }

    public AuctionItems getAuctionItems(RequestAuctionItems requestAuctionItems) {
        return auctionClient.getAuctionItems(requestAuctionItems);
    }
}
