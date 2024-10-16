package jmna.loacalc.feign.client.armories.cards;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Effect {
    @JsonProperty("Index")
    private Integer index;
    @JsonProperty("CardsSlots")
    private List<Integer> cardSlots;
    @JsonProperty("Items")
    private List<Item> items;
}
