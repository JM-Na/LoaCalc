package jmna.loacalc.feign.client.auctions.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Item {
    @JsonProperty("Name")
    private String name;
    @JsonProperty("Grade")
    private String grade;
    @JsonProperty("Tier")
    private Integer tier;
    @JsonProperty("Level")
    private Integer level;
    @JsonProperty("Icon")
    private String icon;
    @JsonProperty("GradeQuality")
    private Integer gradeQuality;
    @JsonProperty("AuctionInfo")
    private AuctionInfo auctionInfo;
    @JsonProperty("Options")
    private List<Option> options;
}
