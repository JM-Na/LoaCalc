package jmna.loacalc.feign.client.markets;

import jmna.loacalc.feign.client.markets.items.RequestMarketItems;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "MarketClient", url = "${smilegate.url}" + "/markets")
public interface MarketClient {

    @GetMapping("/options")
    MarketOptions getMarketOptions();

    @GetMapping("/items/{itemId}")
    List<MarketItemsById> getMarketItemsById(@PathVariable Integer itemId);

    @PostMapping("/items")
    MarketItems getMarketItems(@RequestBody RequestMarketItems requestMarketItems);
}
