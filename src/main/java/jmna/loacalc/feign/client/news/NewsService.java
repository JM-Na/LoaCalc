package jmna.loacalc.feign.client.news;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    private final NewsClient newsClient;

    public NewsService(NewsClient newsClient) {
        this.newsClient = newsClient;
    }

    public List<NewsNotices> getNewsNotices(String searchText, String type) {
        return newsClient.getNewsNotices(searchText, type);
    }

    public List<NewsEvents> getNewsEvents() {
        return newsClient.getNewsEvents();
    }
}
