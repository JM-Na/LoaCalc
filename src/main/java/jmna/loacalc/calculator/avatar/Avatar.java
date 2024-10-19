package jmna.loacalc.calculator.avatar;

import jmna.loacalc.feign.client.armories.ArmoryAvatar;
import lombok.Data;

@Data
public class Avatar {
    private String type;
    private String name;
    private String icon;
    private String grade;
    private Boolean isInner; // true: 효과만 적용, false: 덧입기로 외관만 적용
    private Boolean isSet; // 상하의 일체형 아바타 인지

    public Avatar(ArmoryAvatar amoryAvatar) {
        this.type = amoryAvatar.getType();
        this.name = amoryAvatar.getName();
        this.icon = amoryAvatar.getIcon();
        this.grade = amoryAvatar.getGrade();
        this.isInner = amoryAvatar.getIsInner();
        this.isSet = amoryAvatar.getIsSet();
    }
}
