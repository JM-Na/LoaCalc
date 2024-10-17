package jmna.loacalc.feign.client.markets;

import jmna.loacalc.feign.client.markets.items.RequestMarketItems;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarketService {

    private final MarketClient marketClient;

    public MarketService(MarketClient marketClient) {
        this.marketClient = marketClient;
    }

    public MarketOptions getMarketsOptions() {
        return marketClient.getMarketOptions();
    }

    public List<MarketItemsById> getMarketsItemsById(Integer id) {
        return marketClient.getMarketItemsById(id);
    }

    public MarketItems getMarketsItems(RequestMarketItems requestMarketItems) {
        return marketClient.getMarketItems(requestMarketItems);
    }
}
