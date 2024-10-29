package jmna.loacalc.processor.equipment.accessory;

import lombok.Data;

@Data
public class BraceletData {
    // 티어
    private Integer tier;
    // 효과명
    private String name;
    // 정확한 수치
    private String effect;
    // 특옵인지 아닌지
    private Boolean isHidden;
    // 특수 옵션의 상/중/하 수치
    private String grade;
    // 아크 패시브 깨달음 수치
    private Integer arkpassiveEffect;

    public BraceletData(int tier, String name, String effect, Boolean isHidden, String grade) {
        this.tier = tier;
        this.name = name;
        this.effect = effect;
        this.isHidden = isHidden;
        this.grade = grade;
    }
}
