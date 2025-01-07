package jmna.scheduler;

import jmna.loacalc.processor.auction.AuctionProcessor;
import jmna.loacalc.processor.market.MarketProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class loaApiScheduler {

    private final AuctionProcessor auctionProcessor;
    private final MarketProcessor marketProcessor;

    // 매시간 실행 (1시간 간격)
    @Scheduled(fixedRate = 3600000) // 1시간 = 3600000ms
    public void executeTask() {
        System.out.println("작업 실행 중: " + java.time.LocalDateTime.now());
        // 여기에 실행할 작업 로직 추가
    }

    // 매 정각(예: 01:00, 02:00, 03:00...)
    @Scheduled(cron = "0 0 * * * *")
    public void executeTaskAtHour() {
        System.out.println("정각 작업 실행 중: " + java.time.LocalDateTime.now());
        // 여기에 실행할 작업 로직 추가

        auctionProcessor.initPrice();
        marketProcessor.initPrice();
        marketProcessor.initEngravingBookPrice();

    }
}
