package jmna.loacalc.calculator.equipment.accessory;


import lombok.Data;

@Data
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
