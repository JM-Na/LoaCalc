package jmna.loacalc.feign.client.auctions;

import jmna.loacalc.feign.client.auctions.items.request.EtcOption;
import jmna.loacalc.feign.client.auctions.items.request.RequestAuctionItems;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

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

        //200000 장신구
        //200010 목걸이
        //200020 귀걸이
        //200030 반지
        //200040 팔찌

        // 7: 연마효과
        // 45: 공격력 % / 46: 무기 공격력 %
        // 공격력 %: 1 10 12 / 무기 공격력 %: 4, 10, 12
        // 공격력 %: 0.40, 0.95, 1.55 / 무기 공격력 %: 0.80, 1.80, 3.00
        // 41: 추가 피해 / 42: 적에게 주는 피해 증가
        // 추가 피해 %: 4, 9, 11 / 적에게 주는 피해 증가 %: 5, 10, 12
        // 추가 피해 %: 0.70, 1.60, 2.60 / 적에게 주는 피해 증가 %: 0.55, 1.20, 2.00
        // 49: 치명타 적중률 % / 50: 치명타 피해 %
        // 치명타 적중률 %: 4, 10, 12 / 치명타 피해 %: 5, 10, 12
        // 치명타 적중률 %: 0.40, 0.95, 1.55 / 치명타 피해 %: 1.10, 2.40, 4.00
        // 53: 공격력+ / 54: 무기 공격력+
        // 공격력+: 9, 11, 12 / 무기 공격력+: 9, 11, 12
        // 공격력+: 80, 195, 390 / 무기 공격력+: 195, 480, 960

        RequestAuctionItems requestAuctionItems = new RequestAuctionItems();
        requestAuctionItems.setCategoryCode(200010);
        requestAuctionItems.setItemTier(4);
        requestAuctionItems.setItemGrade("고대");

        EtcOption etcOption1 = new EtcOption();
        etcOption1.setFirstOption(7);
        etcOption1.setSecondOption(41);
        etcOption1.setMinValue(260);
        etcOption1.setMaxValue(260);

        EtcOption etcOption2 = new EtcOption();
//        etcOption2.setFirstOption(7);
//        etcOption2.setSecondOption(46);
//        etcOption2.setMinValue(10);
//        etcOption2.setMaxValue(12);

        List<EtcOption> etcOptionList = List.of(etcOption1, etcOption2);
        requestAuctionItems.setEtcOptions(etcOptionList);

        AuctionItems auctionItems = auctionService.getAuctionItems(requestAuctionItems);

        System.out.println("auctionItems = " + auctionItems);

        Assertions.assertThat(auctionItems).isNotNull();
    }
}