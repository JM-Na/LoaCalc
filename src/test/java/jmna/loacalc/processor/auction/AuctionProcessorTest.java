package jmna.loacalc.processor.auction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AuctionProcessorTest {

    @Autowired
    private AuctionProcessor auctionProcessor;

    @Test
    void initPrice() {
        auctionProcessor.initPrice();

        List<T4AccessoryData> list = T4AccessoryData.getListOfData();


        for (T4AccessoryData t4AccessoryData : list) {
            System.out.println("t4NecklaceData.toString() = " + t4AccessoryData.getPrice());
        }
    }

}