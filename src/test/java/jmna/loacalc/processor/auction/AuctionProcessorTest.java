package jmna.loacalc.processor.auction;

import jmna.loacalc.entity.T4Gem;
import jmna.loacalc.repository.T4GemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AuctionProcessorTest {

    @Autowired
    private AuctionProcessor auctionProcessor;
    @Autowired
    private T4GemRepository t4GemRepository;

    @Test
    void initPrice() {
        auctionProcessor.initPrice();

        List<T4AccessoryData> list = T4AccessoryData.getListOfData();


        for (T4AccessoryData t4AccessoryData : list) {
            System.out.println("t4AccessoryData.toString() = " + t4AccessoryData.toString());
        }
    }

//    @Test
//    void initGemPrice() {
//        auctionProcessor.initGemPrice();
//
//        List<T4GemData> list = T4GemData.getListOfData();
//
//
//        for (T4GemData t4GemData : list) {
//            System.out.println("t4NecklaceData.toString() = " + t4GemData.getPrice());
//        }
//    }

    @Test
    void initGemPrice_JPA() {
        auctionProcessor.initGemPrice();

        List<T4Gem> all = t4GemRepository.findAll();

        for (T4Gem t4Gem : all) {
            System.out.println("t4Gem.getName() = " + t4Gem.getName());
            System.out.println("t4Gem.getPrice() = " + t4Gem.getPrice());
        }
    }
}