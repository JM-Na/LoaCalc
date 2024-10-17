package jmna.loacalc.feign.client.armories;

import com.fasterxml.jackson.annotation.JsonProperty;
import jmna.loacalc.feign.client.armories.skill.Rune;
import jmna.loacalc.feign.client.armories.skill.Tripod;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ArmoryCombatSkill {
    @JsonProperty("Name")
    private String name;

    @JsonProperty("Icon")
    private String icon;

    @JsonProperty("Level")
    private Integer level;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("SkillType")
    private Integer skillType;

    @JsonProperty("Tripods")
    private List<Tripod> tripods;

    @JsonProperty("Rune")
    private Rune rune;

    @JsonProperty("Tooltip")
    private String tooltip;
}
