package jmna.loacalc.processor.auction;

import jmna.loacalc.entity.T4Accessory;
import jmna.loacalc.entity.T4Gem;
import jmna.loacalc.feign.client.auctions.AuctionClient;
import jmna.loacalc.feign.client.auctions.AuctionItems;
import jmna.loacalc.feign.client.auctions.items.Item;
import jmna.loacalc.repository.T4AccessoryRepository;
import jmna.loacalc.repository.T4GemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AuctionProcessor {

    private final AuctionClient auctionClient;
    private final T4GemRepository t4GemRepository;
    private final T4AccessoryRepository t4AccessoryRepository;

    public void initPrice() {
        initAccessoryPrice();
        initGemPrice();
    }

//    public void initAccessoryPrice() {
//        List<T4AccessoryData> list = T4AccessoryData.getListOfData();
//
//        for (T4AccessoryData t4AccessoryData : list) {
//
//            AuctionItems auctionItems = auctionClient.getAuctionItems(T4AccessoryData.setRequestAuctionItem(t4AccessoryData));
//
//            Integer price = auctionItems.getItems().get(0).getAuctionInfo().getBuyPrice();
//
//            if(price == null)
//                price = auctionItems.getItems().get(0).getAuctionInfo().getBidStartPrice();
//
//            T4AccessoryData.setPrice(t4AccessoryData, price);
//        }
//    }
    public void initAccessoryPrice() {
        List<T4AccessoryData> list = T4AccessoryData.getListOfData();

        for (T4AccessoryData t4AccessoryData : list) {
            AuctionItems auctionItems = auctionClient.getAuctionItems(T4AccessoryData.setRequestAuctionItem(t4AccessoryData));

            Item item = auctionItems.getItems().get(0);
            Integer price = item.getAuctionInfo().getBuyPrice();
            String icon = item.getIcon();
            if(price == null)
                price = item.getAuctionInfo().getBidStartPrice();

            t4AccessoryRepository.save(new T4Accessory(t4AccessoryData.toString(), price, icon));
        }
    }

    //    public void initGemPrice() {
//        List<T4GemData> list = T4GemData.getListOfData();
//
//        for (T4GemData t4GemData : list) {
//            AuctionItems auctionItems = auctionClient.getAuctionItems(T4GemData.setRequestAuctionItem(t4GemData));
//
//            Integer price = auctionItems.getItems().get(0).getAuctionInfo().getBuyPrice();
//
//            if(price == null)
//                price = auctionItems.getItems().get(0).getAuctionInfo().getBidStartPrice();
//
//            T4GemData.setPrice(t4GemData, price);
//        }
//
//    }
    public void initGemPrice() {
        List<T4GemData> list = T4GemData.getListOfData();

        for (T4GemData t4GemData : list) {
            AuctionItems auctionItems = auctionClient.getAuctionItems(T4GemData.setRequestAuctionItem(t4GemData));

            Item item = auctionItems.getItems().get(0);

            String name = item.getName();
            String icon = item.getIcon();
            Integer price = item.getAuctionInfo().getBuyPrice();

            if(price == null)
                price = item.getAuctionInfo().getBidStartPrice();

            t4GemRepository.save(new T4Gem(name, price, icon));
        }

    }

}
