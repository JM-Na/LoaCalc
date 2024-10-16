package jmna.loacalc.calculator.accessory;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public abstract class SubEquipment {
    private String type;
    private String name;
    private Integer quality;
    private String grade;
    private Integer tier;
    private String itemInfo;

    public SubEquipment() {

    }
}
