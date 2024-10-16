package jmna.loacalc.calculator;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ElixirEffect {
    private String part;
    private String effect;
    private Integer level;

}
