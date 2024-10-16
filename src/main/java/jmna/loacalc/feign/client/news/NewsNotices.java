package jmna.loacalc.feign.client.news;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsNotices {
    @JsonProperty("Title")
    private String title;

    @JsonProperty("Date")
    private String date;

    @JsonProperty("Link")
    private String link;

    @JsonProperty("Type")
    private String type;
}
