package jmna.loacalc.calculator.accessory;

import lombok.Data;

@Data
public class BraceletEffect {
    // 티어
    private Integer tier;
    // 효과명
    private String name;
    // 정확한 수치
    private String effect;
    // 특수 옵션의 상/중/하 수치
    private String grade;

    public BraceletEffect(int tier, String name, String effect, String grade) {
        this.tier = tier;
        this.name = name;
        this.effect = effect;
        this.grade = grade;
    }
}
