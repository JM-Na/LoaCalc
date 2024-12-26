package jmna.loacalc.processor.auction;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuctionProcessorTest {

    @Autowired
    private AuctionProcessor auctionProcessor;

    @Test
    void initPrice() {
        auctionProcessor.initPrice();

        List<T4NecklaceData> list = T4NecklaceData.getListOfData();


        for (T4NecklaceData t4NecklaceData : list) {
            System.out.println("t4NecklaceData.toString() = " + t4NecklaceData.getPrice());
        }
        System.out.println("list = " + list);
    }

}