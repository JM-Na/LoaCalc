package jmna.loacalc.feign.client.news;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "NewsClient", url = "${smilegate.url}" + "/news")
public interface NewsClient {

    @GetMapping(value = "/notices")
    List<NewsNotices> getNewsNotices(@RequestParam(name = "searchText", required = false) String searchText,
                                     @RequestParam(name = "type", required = false) String type);

    @GetMapping(value = "/events")
    List<NewsEvents> getNewsEvents();
}
