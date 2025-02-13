package jmna.loacalc.calculator;

import jmna.loacalc.processor.armory.CharacterProfile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CharacterClassCalculator {

    public void test(TotalArmoryEffect totalArmoryEffect, CharacterProfile characterProfile) {
        String className = characterProfile.getClassName();

        switch (className) {
            case "버서커", "슬레이어" -> {
                totalArmoryEffect.addAtkSpeed(20);
                totalArmoryEffect.addMoveSpeed(20);
                totalArmoryEffect.addCrit(30);
            }
            case "호크아이" -> {
                totalArmoryEffect.addMoveSpeed(4);
            }
            case "블래스터" -> {
                totalArmoryEffect.addOutgoingDmg(30);
            }
            case "스카우터" -> {
                // 하이퍼 싱크 모드에만 해당함
                totalArmoryEffect.addAtkSpeed(15);
                totalArmoryEffect.addMoveSpeed(30);
            }
            case "기공사" -> {
                // 금강선공 3단계
                totalArmoryEffect.addAtkSpeed(15);
                totalArmoryEffect.addOutgoingDmg(60);
            }
            case "창술사" -> {
                // 난무
                totalArmoryEffect.addMoveSpeed(15);
                totalArmoryEffect.addOutgoingDmg(10);
                totalArmoryEffect.addCritDmg(25);
                // 절정
                totalArmoryEffect.addAtkSpeed(10);
                totalArmoryEffect.addOutgoingDmg(10);
                totalArmoryEffect.addCrit(15);
            }
            case "블레이드" -> {
                totalArmoryEffect.addMoveSpeed(10);
                totalArmoryEffect.addAtkSpeed(20);
                totalArmoryEffect.addAtkPowerPercent(30); // 이 공격력 %는 후에 적용되는 곱연산임. 추가 수정 필요
            }
            case "데모닉" -> {
                totalArmoryEffect.addMoveSpeed(20); // 악마화 시에만 적용됨.
            }
            case "리퍼" -> {
                // 갈증 각인에 한함.
                totalArmoryEffect.addCrit(15);
                totalArmoryEffect.addMoveSpeed(10);
                totalArmoryEffect.addAtkSpeed(10);
            }
            case "소서리스" -> {
                // 마력 해방 시
                totalArmoryEffect.addOutgoingDmg(16); // 특화 보정을 받음
                totalArmoryEffect.addCrit(30);
                totalArmoryEffect.addCritDmg(55);
            }
            case "소울이터" -> {
                // 사신화 시
                totalArmoryEffect.addAtkSpeed(10);
                totalArmoryEffect.addMoveSpeed(20);
                totalArmoryEffect.addCrit(20);
            }
            case "기상술사" -> {
                // 공깍 10, 이감 25
            }
            case "환수사" -> {
                // 환수 각성 공이속 20
                // 둔갑 곰 이속 -10, 여우 이속 +30
            }
        }
    }
}
