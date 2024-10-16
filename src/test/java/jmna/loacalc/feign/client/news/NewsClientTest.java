package jmna.loacalc.feign.client.news;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class NewsClientTest {

    @Autowired
    private NewsService newsService;

    @Test
    void NewsNotices() {
        NewsNotices newsNotices = newsService.getNewsNotices(null, null)
                .get(0);

        System.out.println("newsNotices = " + newsNotices);

        assertThat(newsNotices).isNotNull();
    }

    @Test
    void NewsEvents() {
        NewsEvents newsEvents = newsService.getNewsEvents()
                .get(0);

        System.out.println("newsEvents = " + newsEvents);

        assertThat(newsEvents).isNotNull();
    }
}