package jmna.loacalc.processor.equipment;

import jmna.loacalc.calculator.subequipments.BraceletEffect;
import jmna.loacalc.feign.client.armories.ArmoryClient;
import jmna.loacalc.feign.client.armories.ArmoryEquipment;
import jmna.loacalc.feign.client.armories.ArmoryTotalForEffect;
import jmna.loacalc.processor.armory.equipment.CharacterEquipment;
import jmna.loacalc.processor.armory.equipment.EquipmentProcessor;
import jmna.loacalc.processor.armory.equipment.accessory.Accessory;
import jmna.loacalc.processor.armory.equipment.accessory.BraceletData;
import jmna.loacalc.processor.armory.equipment.accessory.HoneEffect;
import jmna.loacalc.processor.armory.equipment.accessory.SubEquipment;
import jmna.loacalc.processor.auction.AccessoryOptionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class EquipmentProcessorTest {

    @Autowired
    private EquipmentProcessor equipmentProcessor;
    @Autowired
    private ArmoryClient armoryClient;

    @Test
    void splitTest() {
        String text4 = "<img src='emoticon_tooltip_bracelet_locked' vspace='-5'></img> 힘 +13121<BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>치명타 피해가 <FONT COLOR='#99FF99'>10%</FONT> 증가한다.<BR>공격이 치명타로 적중 시 적에게 주는 피해가 <FONT COLOR='#99ff99'>1.5%</FONT> 증가한다.<BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>신속 +114<BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>치명 +77";
        String[] s = text4.replaceAll("<img[^>]*>", "\n").replaceAll("</img>", "").replaceAll("<BR>", " ").split("\n");

        System.out.println("s = " + Arrays.toString(s));

        int tier = 4;

        List<BraceletData> braceletDataList = new ArrayList<>();

        for (String s1 : s) {
            System.out.println("s1 = " + s1);
            Pattern pattern = Pattern.compile("(\\S?\\S)\\s\\+(\\d){2,5}");
            Matcher matcher = pattern.matcher(s1);
            if (matcher.find()) {
                String[] s2 = s1.replaceAll(" ", "").split("\\+");

                BraceletData braceletData = new BraceletData(tier, s2[0], s2[1], false, null);
                braceletDataList.add(braceletData);
            }
            // 3티어 팔찌의 경우
            else {
                String s2 = s1.replaceAll("<[^>]*>", "");
                System.out.println("s2 = " + s2);
                BraceletData braceletData = new BraceletData(tier, s2, s2, true, null);
                braceletDataList.add(braceletData);
            }
        }

        System.out.println("braceletEffectList = " + braceletDataList);
    }

    @Test
    void t4BraceletOptionParser() {

        String input = "치명타 피해가 10% 증가한다. 공격이 치명타로 적중 시 적에게 주는 피해가 1.5% 증가한다. ";


        String[][] patterns = {
                {"crit_critOutDmg", "치명타 적중률이 (\\S{1,4})% 증가한다. 공격이 치명타로 적중 시 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                {"OutDmg", "적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                {"critDmg_critOutDmg", "치명타 피해가 (\\S{1,4})% 증가한다. 공격이 치명타로 적중 시 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                {"OutDmg_StagDmg", "적에게 주는 피해가 (\\S{1,4})% 증가하며, 무력화 상태의 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                {"AddDmg_DaemonDmg", "추가 피해가 (\\S{1,4})% 증가한다. 악마 및 대악마 계열 피해량이 (\\S{1,4})% 증가한다"},
                {"AddCool_OutDmg", "스킬의 재사용 대기 시간이 (\\S{1,4})% 증가하지만, 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                {"Weapon_Speed_Stack", "공격 적중 시 매 초마다 10초 동안 무기 공격력이 (\\S{1,4}), 공격 및 이동 속도가 1% 증가한다. (최대 6중첩)"},
                {"Weapon_Stable", "무기 공격력이 (\\S{1,4}) 증가한다. 자신의 생명력이 50% 이상일 경우 적에게 공격 적중 시 5초 동안 무기 공격력이 (\\S{1,4}) 증가한다"},
                {"Weapon_Weapon_Stack", "무기 공격력이 (\\S{1,4}) 증가한다. 공격 적중 시 30초 마다 120초 동안 무기 공격력이 (\\S{1,4}) 증가한다. (최대 30중첩)"},
                {"BackOutDmg", "백어택 스킬이 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                {"HeadOutDmg", "헤드어택 스킬이 적에게 주는 피해가 (\\S{1,4})% 증가한다"},
                {"NonOutDmg", "방향성 공격이 아닌 스킬이 적에게 주는 피해가 (\\S{1,4})% 증가한다. 각성기는 적용되지않는다"},
                {"AddDmg", "추가 피해 (\\S{1,4})%"},
                {"Crit", "치명타 적중률 (\\S{1,4})%"},
                {"CritDmg", "치명타 피해 (\\S{1,4})%"},
                {"Weapon", "무기 공격력 (\\S{1,4})"},
                {"ArmorReduction", "적에게 공격 적중 시 8초 동안 대상의 방어력을 (\\S{1,4})% 감소시킨다. 해당 효과는 한 파티 당 하나만 적용된다. 아군 공격력 강화 효과가 (\\S{1,4})% 증가한다"},
                {"CritReduction", "적에게 공격 적중 시 8초 동안 대상의 치명타 저항을 (\\S{1,4})% 감소시킨다. 해당 효과는 한 파티 당 하나만 적용된다. 아군 공격력 강화 효과가 (\\S{1,4})% 증가한다"},
                {"CritDmgReduction", "적에게 공격 적중 시 8초 동안 대상의 치명타 피해 저항을 (\\S{1,4})% 감소시킨다. 해당 효과는 한 파티 당 하나만 적용된다. 아군 공격력 강화 효과가 (\\S{1,4})% 증가한다"},
                {"BuffOutDmgReduction", "파티 효과로 보호 효과(보호막, 생명력 회복, 받는 피해 감소)가 적용된 대상이 5초 동안 적에게 주는 피해가 (\\S{1,4})% 증가한다. 해당 효과는 한 파티 당 하나만 적용된다, 아군 공격력 강화 효과가 (\\S{1,4})% 증가한다"},
                {"Shield_Heal", "파티원 보호 및 회복 효과가 (\\S{1,4})% 증가한다"},
                {"Buff_Attack", "아군 공격력 강화 효과 (\\S{1,4})%"},
                {"Buff_Dmg", "아군 피해량 강화 효과 (\\S{1,4})%"}
        };

        // 가장 가까운(첫 번째 매칭된) 패턴만 저장할 변수
        String closestVarName = null;
        String closestPattern = null;
        int closestStartIndex = Integer.MAX_VALUE; // 가장 작은 시작 인덱스를 찾기 위해 큰 값으로 초기화
        String[] closestMatches = null;

        // 모든 패턴을 순회하며 매칭
        for (String[] patternInfo : patterns) {
            String varName = patternInfo[0]; // 변수명
            String regex = patternInfo[1];   // 정규식 패턴

            Pattern compiledPattern = Pattern.compile(regex);
            Matcher matcher = compiledPattern.matcher(input);

            // 매칭된 결과가 있을 때
            while (matcher.find()) {
                int startIndex = matcher.start(); // 매칭 시작 인덱스
                System.out.println("startIndex = " + startIndex);

                // 더 가까운 매칭이 발견되면 갱신
                if (startIndex < closestStartIndex) {
                    closestStartIndex = startIndex;
                    closestVarName = varName;
                    closestPattern = regex;
                    closestMatches = new String[matcher.groupCount()];
                    for (int i = 1; i <= matcher.groupCount(); i++) {
                        closestMatches[i - 1] = matcher.group(i);
                    }
                }
            }
        }

        // 가장 가까운 매칭 결과 출력
        if (closestVarName != null) {
            System.out.println("변수명: " + closestVarName);
            System.out.println("패턴: " + closestPattern);
            for (String closestMatch : closestMatches) {
                System.out.println("일치하는 값: " + closestMatch);
            }
            System.out.println("-----------------------------------");
        } else {
            System.out.println("일치하는 패턴이 없습니다.");
        }
        BraceletEffect braceletEffect = new BraceletEffect();
        if (closestVarName != null && closestMatches[0] != null) {
            System.out.println("closestVarName = " + closestVarName);
            switch (closestVarName) {
                case "crit_critOutDmg" -> {
                    braceletEffect.addCrtRate(Double.parseDouble(closestMatches[0]));
                    braceletEffect.addOutgoingDmgWhenCrit(Double.parseDouble(closestMatches[1]));
                }
                case "OutDmg" -> braceletEffect.addOutgoingDmg(Double.parseDouble(closestMatches[0]));
                case "critDmg_critOutDmg" -> {
                    braceletEffect.addCritDmg(Double.parseDouble(closestMatches[0]));
                    braceletEffect.addOutgoingDmgWhenCrit(Double.parseDouble(closestMatches[1]));
                }
                case "OutDmg_StagDmg" -> {
                    braceletEffect.addOutgoingDmg(Double.parseDouble(closestMatches[0]));
                    braceletEffect.addStaggerDmg(Double.parseDouble(closestMatches[1]));
                }
                case "AddDmg_DaemonDmg" -> {
                    braceletEffect.addAddDmg(Double.parseDouble(closestMatches[0]));
                    braceletEffect.addDaemonDmg(Double.parseDouble(closestMatches[1]));
                }
                case "AddCool_OutDmg" -> {
                    braceletEffect.addCooldown(Double.parseDouble(closestMatches[0]) * -1);
                    braceletEffect.addOutgoingDmg(Double.parseDouble(closestMatches[1]));
                }
                case "Weapon_Speed_Stack" -> {
                    braceletEffect.addWeaponPower(Integer.parseInt(closestMatches[0]) * 6);
                    braceletEffect.addSpeed(6);
                }
                case "Weapon_Stable" -> braceletEffect.addWeaponPower(Integer.parseInt(closestMatches[0]) + Integer.parseInt(closestMatches[1]));
                case "Weapon_Weapon_Stack" -> braceletEffect.addWeaponPower(Integer.parseInt(closestMatches[0]) + Integer.parseInt(closestMatches[1]) * 30);
                case "BackOutDmg" -> braceletEffect.setBackDmg(Double.parseDouble(closestMatches[0]));
                case "HeadOutDmg" -> braceletEffect.setHeadDmg(Double.parseDouble(closestMatches[0]));
                case "NonOutDmg" -> braceletEffect.setHitDmg(Double.parseDouble(closestMatches[0]));
                case "AddDmg" -> braceletEffect.addAddDmg(Double.parseDouble(closestMatches[0]));
                case "Crit" -> braceletEffect.addCrtRate(Double.parseDouble(closestMatches[0]));
                case "CritDmg" -> braceletEffect.addCritDmg(Double.parseDouble(closestMatches[0]));
                case "Weapon" -> braceletEffect.addWeaponPower(Integer.parseInt(closestMatches[0]));
                case "ArmorReduction" -> {
                    braceletEffect.setArmorReductionSynergy(Double.parseDouble(closestMatches[0]));
                    braceletEffect.addApBuff(Double.parseDouble(closestMatches[1]));
                }
                case "CritReduction" -> {
                    braceletEffect.setCritRateSynergy(Double.parseDouble(closestMatches[0]));
                    braceletEffect.addApBuff(Double.parseDouble(closestMatches[1]));
                }
                case "CritDmgReduction" -> {
                    braceletEffect.setCritDmgSynergy(Double.parseDouble(closestMatches[0]));
                    braceletEffect.addApBuff(Double.parseDouble(closestMatches[1]));
                }
                case "BuffOutDmgReduction" -> {
                    braceletEffect.setOutgoingDmgSynergy(Double.parseDouble(closestMatches[0]));
                    braceletEffect.addApBuff(Double.parseDouble(closestMatches[1]));
                }
                case "Shield_Heal" -> braceletEffect.addShieldHeal(Double.parseDouble(closestMatches[0]));
                case "Buff_Attack" -> braceletEffect.addApBuff(Double.parseDouble(closestMatches[0]));
                case "Buff_Dmg" -> braceletEffect.addDmgBuff(Double.parseDouble(closestMatches[0]));
            }
        }
        System.out.println("braceletEffect = " + braceletEffect);
    }


    @Test
    void test() {
        // 여러개의 데이터 요청을 통채로 처리하는 API
        ArmoryTotalForEffect armoryTotal = armoryClient.getArmoryTotalForEffect("레게머리뿌뿌뿡", null);

        // 장비 정보를 담고있는 CharacterEquipment
        List<ArmoryEquipment> armoryEquipment = armoryTotal.getArmoryEquipments();
        CharacterEquipment characterEquipment = equipmentProcessor.parseEquipmentInfo(armoryEquipment);

        List<SubEquipment> subEquipments = characterEquipment.getSubEquipments();

        System.out.println("subEquipments = " + subEquipments);

        for (SubEquipment subEquipment : subEquipments) {
            if (subEquipment.getClass().equals(Accessory.class)) {
                List<HoneEffect> honeEffects = ((Accessory) subEquipment).getHoneEffects();
                List<AccessoryOptionType> typeList = honeEffects.stream().map(HoneEffect::getType).filter(Objects::nonNull).toList();
                System.out.println("typeList = " + typeList);
            }
        }

    }

}