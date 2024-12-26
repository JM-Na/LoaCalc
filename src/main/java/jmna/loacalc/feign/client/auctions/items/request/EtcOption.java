package jmna.loacalc.feign.client.auctions.items.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.processor.auction.AccessoryOptionType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EtcOption {
    @JsonProperty("FirstOption")
    private Integer firstOption;
    @JsonProperty("SecondOption")
    private Integer secondOption;
    @JsonProperty("MinValue")
    private Integer minValue;
    @JsonProperty("MaxValue")
    private Integer maxValue;

    public EtcOption(AccessoryOptionType target) {
        this.firstOption = 7;
        this.secondOption = target.getTypeCode();
        this.minValue = target.getOptionCode();
        this.maxValue = target.getOptionCode();
    }

}
