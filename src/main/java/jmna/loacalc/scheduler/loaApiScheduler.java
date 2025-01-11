package jmna.loacalc.scheduler;

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

    /**
     * 시각이 아닌 시간 단위 작업은 서버 로딩시 기준으로 바로 실행되고 계산됨.
     */


    // 매시간 실행 (1시간 간격)
    @Scheduled(fixedRate = 3600000) // 1시간 = 3600000ms
    public void executeTaskByHour() {
//        System.out.println("1시간당 작업 실행 중: " + java.time.LocalDateTime.now());
        // 여기에 실행할 작업 로직 추가
    }

    @Scheduled(fixedRate = 60000) // 1시간 = 3600000ms
    public void executeTaskByMinute() {
//        System.out.println("1분당 작업 실행 중: " + java.time.LocalDateTime.now());
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
