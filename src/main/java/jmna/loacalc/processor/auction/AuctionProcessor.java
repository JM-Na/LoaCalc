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

    private void initPrice() {
        //200000 장신구
        //200010 목걸이
        //200020 귀걸이
        //200030 반지
        //200040 팔찌

        // 7: 연마효과
        // 45: 공격력 %, 46: 무기 공격력 %
        // 공격력 %: 1 10 12, 무기 공격력 %: 4, 10, 12

        RequestAuctionItems requestAuctionItems = new RequestAuctionItems();
        requestAuctionItems.setCategoryCode(200020);
        requestAuctionItems.setItemTier(4);

        // 공격력 % 옵션
        EtcOption etcOption1 = new EtcOption();
        etcOption1.setFirstOption(7);
        etcOption1.setSecondOption(45);
        etcOption1.setMinValue(1);
        etcOption1.setMaxValue(12);
        // 무기 공격력 % 옵션
        EtcOption etcOption2 = new EtcOption();
        etcOption2.setFirstOption(7);
        etcOption2.setSecondOption(46);
        etcOption2.setMinValue(10);
        etcOption2.setMaxValue(12);

        List<EtcOption> etcOptionList = List.of(etcOption1, etcOption2);
        requestAuctionItems.setEtcOptions(etcOptionList);

        AuctionItems auctionItems = auctionClient.getAuctionItems(requestAuctionItems);

        System.out.println("auctionItems = " + auctionItems);

    }

}
