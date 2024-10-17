package jmna.loacalc.feign.client.armories;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.feign.client.armories.cards.Card;
import jmna.loacalc.feign.client.armories.engravings.Effect;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArmoryCards {
    @JsonProperty("Cards")
    private List<Card> cards;

    @JsonProperty("Effects")
    private List<Effect> effects;
}
