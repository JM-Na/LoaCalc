package jmna.loacalc.feign.client.auctions;


import jmna.loacalc.feign.client.auctions.items.request.RequestAuctionItems;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "AuctionClient", url = "${smilegate.url}" + "/auctions")
public interface AuctionClient {

    @GetMapping("/options")
    AuctionOptions getAuctionOptions();

    @PostMapping(value = "/items", consumes = "application/json")
    AuctionItems getAuctionItems(@RequestBody RequestAuctionItems requestAuctionItems);
}
