package jmna.loacalc.feign.client.armories.gems;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Effects {
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Skills")
    private List<Skill> skills;
}
