package jmna.loacalc.calculator;

import jmna.loacalc.processor.armory.CharacterProfile;
import jmna.loacalc.processor.armory.arkpassive.CharacterArkpassive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CharacterClassCalculator {

    public void calculateClassIdentity(TotalArmoryEffect totalArmoryEffect, CharacterProfile characterProfile, CharacterArkpassive characterArkpassive) {
        String className = characterProfile.getClassName();
        String arkpassiveName = characterArkpassive.getName();
        switch (className) {
            case "버서커", "슬레이어" -> {
                // 폭주 효과
                totalArmoryEffect.addAtkSpeed(20);
                totalArmoryEffect.addMoveSpeed(20);
                totalArmoryEffect.addCrit(30);
            }
            case "호크아이" -> {
                // 두동
                if (arkpassiveName.equals("두번째 동료"))
                    totalArmoryEffect.addMoveSpeed(4);
            }
            case "블래스터" -> {
                // 화력 게이지
                totalArmoryEffect.addOutgoingDmg(30);
            }
            case "스카우터" -> {
                // 하이퍼 싱크 모드에만 해당함
                if (arkpassiveName.equals("진화의 유산")) {
                    totalArmoryEffect.addAtkSpeed(15);
                    totalArmoryEffect.addMoveSpeed(30);
                }
            }
            case "기공사" -> {
                // 금강선공 3단계
                totalArmoryEffect.addAtkSpeed(15);
                totalArmoryEffect.addOutgoingDmg(60);
            }
            case "창술사" -> {
                // 난무
                if (arkpassiveName.equals("절제")){
                    totalArmoryEffect.addMoveSpeed(15);
                    totalArmoryEffect.addOutgoingDmg(10);
                    totalArmoryEffect.addCritDmg(25);
                }
                // 절정
                if (arkpassiveName.equals("절정 I")) {
                    totalArmoryEffect.addAtkSpeed(10);
                    totalArmoryEffect.addOutgoingDmg(10);
                    totalArmoryEffect.addCrit(15);
                }
            }
            case "블레이드" -> {
                totalArmoryEffect.addMoveSpeed(10);
                totalArmoryEffect.addAtkSpeed(20);
                totalArmoryEffect.addAtkPowerPercent(30); // 이 공격력 %는 후에 적용되는 곱연산임. 추가 수정 필요
            }
            case "데모닉" -> {
                if (arkpassiveName.equals("멈출 수 없는 충동"))
                    totalArmoryEffect.addMoveSpeed(20); // 악마화 시에만 적용됨.
            }
            case "리퍼" -> {
                // 갈증 각인에 한함.
                if (arkpassiveName.equals("피냄새")) {
                    totalArmoryEffect.addCrit(15);
                    totalArmoryEffect.addMoveSpeed(10);
                    totalArmoryEffect.addAtkSpeed(10);
                }
            }
            case "소서리스" -> {
                // 마력 해방 시
                if (arkpassiveName.equals("점화")) {
                    totalArmoryEffect.addOutgoingDmg(16); // 특화 보정을 받음
                    totalArmoryEffect.addCrit(30);
                    totalArmoryEffect.addCritDmg(55);
                }
            }
            case "소울이터" -> {
                // 사신화 시
                if (arkpassiveName.equals("영혼친화력")) {
                    totalArmoryEffect.addAtkSpeed(10);
                    totalArmoryEffect.addMoveSpeed(20);
                    totalArmoryEffect.addCrit(20);
                }
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
