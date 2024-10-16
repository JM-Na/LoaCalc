package jmna.loacalc.feign.client.armories;

import jmna.loaapi.calculator.BasicEffect;
import jmna.loaapi.calculator.HoneEffect;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ArmoryServiceTest {

    @Autowired
    private ArmoryService armoryService;

    @Test
    void getArmoriesProfiles() {
        ArmoryProfiles armoryProfiles = armoryService.getArmoryProfiles("레게머리뿌뿌뿡");

        System.out.println("armoriesProfiles = " + armoryProfiles);

        assertThat(armoryProfiles).isNotNull();
    }

    @Test
    void getArmoriesEquipment() {
        List<ArmoryEquipment> armoryEquipments = armoryService.getArmoryEquipment("레게머리뿌뿌뿡");

        System.out.println("armoriesEquipments = " + armoryEquipments);

        assertThat(armoryEquipments).isNotNull();
    }

    @Test
    void getArmoriesAvatar() {
        List<ArmoryAvatar> armoryAvatars = armoryService.getArmoryAvatars("레게머리뿌뿌뿡");

        System.out.println("armoriesAvatars = " + armoryAvatars);

        assertThat(armoryAvatars).isNotNull();
    }

    @Test
    void getArmoriesCombatSkills() {
        List<ArmoryCombatSkill> armoryCombatSkills = armoryService.getArmoryCombatSkills("레게머리뿌뿌뿡");

        System.out.println("armoriesCombatSkills = " + armoryCombatSkills);

        assertThat(armoryCombatSkills).isNotNull();
    }

    @Test
    void getArmoriesEngravings() {
        ArmoryEngravings armoryEngravings = armoryService.getArmoryEngravings("레게머리뿌뿌뿡");

        System.out.println("armoriesEngravings = " + armoryEngravings);

        assertThat(armoryEngravings).isNotNull();
    }

    @Test
    void getArmoriesCards() {
        ArmoryCards armoryCards = armoryService.getArmoryCards("레게머리뿌뿌뿡");

        System.out.println("armoriesCards = " + armoryCards);

        assertThat(armoryCards).isNotNull();
    }

    @Test
    void getArmoriesGems() {
        ArmoryGems armoryGems = armoryService.getArmoryGems("레게머리뿌뿌뿡");

        System.out.println("armoriesGems = " + armoryGems);

        assertThat(armoryGems).isNotNull();
    }

    @Test
    void getArmoriesColosseums() {
        ArmoryColosseums armoryColosseums = armoryService.getArmoryColosseums("레게머리뿌뿌뿡");

        System.out.println("armoriesColosseums = " + armoryColosseums);

        assertThat(armoryColosseums).isNotNull();
    }

    @Test
    void getArmoriesCollectibles() {
        List<ArmoryCollectible> armoryCollectibles = armoryService.getArmoryCollectibles("레게머리뿌뿌뿡");

        System.out.println("armoriesCollectibles = " + armoryCollectibles);

        assertThat(armoryCollectibles).isNotNull();
    }

    @Test
    void getArmoriesArkPassive() {
        ArmoryArkPassive armoryArkPassive = armoryService.getArmoryArkPassive("레게머리뿌뿌뿡");

        System.out.println("armoriesArkPassive = " + armoryArkPassive);

        assertThat(armoryArkPassive).isNotNull();
    }


    @Test
    void armoryTranscendence() throws JSONException {
        List<ArmoryEquipment> armoryEquipments = armoryService.getArmoryEquipment("레게머리뿌뿌뿡");

        // 무기 - 투구 - 상의 - 하의 - 장갑 - 어깨 순으로 반복문 작동
        for (int i = 0; i <= 5; i++) {
            // \n 등의 개행 문자를 모두 띄어쓰기로 변환
            String tooltip = armoryEquipments.get(i).getTooltip().replaceAll("\\s+", " ");
            System.out.println("tooltip = " + tooltip);

            Pattern patternArmory = Pattern.compile("\\+(\\d\\d)\\s*(\\S+(?:\\s+\\S+){0,3})</FONT></P>\" }, \"Element_001\": \\{ \"type\": \"ItemTitle\", \"value\": \\{ \"bEquip\": 0, \"leftStr0\": \"<FONT SIZE='12'><FONT COLOR='#E3C7A1'>(\\S+(?:\\s+\\S+){0,3})</FONT></FONT>\", \"leftStr1\": \"<FONT SIZE='14'>품질</FONT>\", \"leftStr2\": \"<FONT SIZE='14'>아이템 레벨 (\\d\\d\\d\\d) \\(티어 4\\)</FONT>\", \"qualityValue\": (\\d{1,3}), \"rightStr0\": \"<FONT SIZE='12'><FONT COLOR='#FFD200'>장착중</FONT></FONT>\", \"slotData\": \\{ \"advBookIcon\": 0, \"battleItemTypeIcon\": 0, \"cardIcon\": false, \"friendship\": 0, \"iconGrade\": 6, \"iconPath\": \"(\\S{0,100})\", ");
            Matcher matcherArmory = patternArmory.matcher(tooltip);

            // 패턴 매칭 결과를 통해 초월 단계, 수치 출력
            while (matcherArmory.find()) {
                System.out.println("강화 단계 = " + matcherArmory.group(1));
                System.out.println("장비 이름 = " + matcherArmory.group(2));
                System.out.println("장비 종류 = " + matcherArmory.group(3));
                System.out.println("아이템 레벨 = " + matcherArmory.group(4));
                System.out.println("품질 = " + matcherArmory.group(5));
                System.out.println("아이콘 = " + matcherArmory.group(6));
            }

            // 무기일 경우
            if (i == 0) {
                Pattern patternWeapon = Pattern.compile("기본 효과</FONT>\", \"Element_001\": \"무기 공격력 \\+(\\d{3,10})\" } }, \"Element_007\": \\{ \"type\": \"ItemPartBox\", \"value\": \\{ \"Element_000\": \"<FONT COLOR='#A9D0F5'>추가 효과</FONT>\", \"Element_001\": \"추가 피해 \\+(\\S{2,6})\"");
                Matcher matcherWeapon = patternWeapon.matcher(tooltip);

                while (matcherWeapon.find()) {
                    System.out.println("무기 공격력 = " + matcherWeapon.group(1));
                    System.out.println("추가 피해 = " + matcherWeapon.group(2));
                }
            }

            //무기를 제외한 방어구의 경우
            if (i > 0) {

                Pattern patternArmor = Pattern.compile("기본 효과</FONT>\", \"Element_001\": \"물리 방어력 \\+(\\d{1,6})<BR>마법 방어력 \\+(\\d{1,6})<BR>힘 \\+(\\d{1,6})<BR>체력 \\+(\\d{1,6})\" } }, \"Element_007\": \\{ \"type\": \"ItemPartBox\", \"value\": \\{ \"Element_000\": \"<FONT COLOR='#A9D0F5'>추가 효과</FONT>\", \"Element_001\": \"생명 활성력 \\+(\\d{1,6})");
                Matcher matcherArmor = patternArmor.matcher(tooltip);

                while (matcherArmor.find()) {
                    System.out.println("물리 방어력 = " + matcherArmor.group(1));
                    System.out.println("마법 방어력 = " + matcherArmor.group(2));
                    System.out.println("힘 = " + matcherArmor.group(3));
                    System.out.println("체력 = " + matcherArmor.group(4)); //vitality
                    System.out.println("생명 활성력 = " + matcherArmor.group(5)); //vigor
                }

                // 엘릭서 관련 부분이 존재할 경우에만 추출 실행
                if (tooltip.contains("\"topStr\": \"<FONT COLOR='#FFE65A'>[엘릭서]</FONT><br><font color='#91fe02'><FONT size='12'>")) {

                    // 정규 표현식 패턴 정의
                    Pattern patternElixir = Pattern.compile("\\[(\\S\\S)\\]</FONT>\\s*(\\S+(?:\\s+\\S+){0,1})\\s*<FONT color='#FFD200'>Lv\\.(\\d+)</FONT><br>");
                    Matcher matcherElixir = patternElixir.matcher(tooltip);

                    // 패턴 매칭 결과를 통해 엘릭서의 부위, 효과, 단계 출력
                    while (matcherElixir.find()) {
                        System.out.println("부위 = " + matcherElixir.group(1));
                        System.out.println("효과 = " + matcherElixir.group(2));
                        System.out.println("단계 = " + matcherElixir.group(3));
                    }
                } else {
                    System.out.println("엘릭서가 적용되지 않은 방어구입니다.");
                }
            }


            // 정규 표현식 패턴 정의
            Pattern patternTranscendence = Pattern.compile("\"topStr\": \"<FONT SIZE='12' COLOR='#A9D0F5'>슬롯 효과</FONT><BR><FONT COLOR='#FF9632'>\\[초월\\]</FONT> <FONT COLOR='#FFD200'>(\\d+)</FONT>단계 <img src='emoticon_Transcendence_Grade' width='18' height='18' vspace ='-4'></img>(\\d{1,2})");
            Matcher matcherTranscendence = patternTranscendence.matcher(tooltip);

            // 패턴 매칭 결과를 통해 초월 단계, 수치 출력
            while (matcherTranscendence.find()) {
                System.out.println("초월 단계 = " + matcherTranscendence.group(1));
                System.out.println("초월 수치 = " + matcherTranscendence.group(2));
            }


            // 상급 재련을 한 경우
            if (tooltip.contains("상급 재련")) {
                Pattern patternAdvanced = Pattern.compile("\\[상급 재련]</FONT> <FONT COLOR='#FFD200'>(\\d{1,2})</FONT>단계</FONT>\" ");
                Matcher matcherAdvanced = patternAdvanced.matcher(tooltip);
                // 패턴 매칭 결과를 통해 초월 단계, 수치 출력
                while (matcherAdvanced.find()) {
                    System.out.println("상급재련 단계 = " + matcherAdvanced.group(1));
                }
            } else {
                System.out.println(i + "의 해당 장비는 상급 재련이 되어있지 않습니다.");
            }

        }

        assertThat(armoryEquipments).isNotNull();
    }

    @Test
    void getAccessories() throws JSONException {
        List<ArmoryEquipment> armoryEquipment = armoryService.getArmoryEquipment("이의동영혼수확기");
        // 6: 목걸이 7,8: 귀걸이 9,10: 반지 11:어빌리티스톤 12:팔찌
        int code = 6;
        ArmoryEquipment accessory = armoryEquipment.get(code);

        System.out.println("accessory = " + accessory);
        // 장신구 종류
        String type = accessory.getType();
        System.out.println("type = " + type);
        // 장신구 명
        String name = accessory.getName();
        System.out.println("name = " + name);

        JSONObject tooltip = new JSONObject(accessory.getTooltip());

        JSONObject element_001 = tooltip.getJSONObject("Element_001");
        JSONObject value = element_001.getJSONObject("value");
        // 품질
        Integer quality = (Integer) value.get("qualityValue");
        System.out.println("quality = " + quality);
        // 등급 + 종류
        String gradeType = (String) value.get("leftStr0");
        List<String> splitGradeType = textProcessor(gradeType);
        String grade = splitGradeType.get(0).split(" ")[0];
        System.out.println("grade = " + grade);
        // 아이템 티어
        String tierTooltip = (String) value.get("leftStr2");
        String tier = textProcessor(tierTooltip).get(0).split(" ")[2];
        System.out.println("tier = " + tier);
        // 귀속 여부, 거래 가능 횟수, 거래 제한 아이템 레벨
        String itemInfo = (String) tooltip.getJSONObject("Element_002").get("value");
        List<String> splitItemInfo = textProcessor(itemInfo);
        System.out.println("itemInfo = " + splitItemInfo);

        if (code > 5 && code < 11) {
            // 기본 효과 힘/민/지/체력
            String basicEffectTooltip = (String) tooltip.getJSONObject("Element_004").getJSONObject("value").get("Element_001");
            List<String> splitBasicEffect = textProcessor(basicEffectTooltip);
            BasicEffect basicEffect = new BasicEffect(splitBasicEffect.stream()
                    .flatMap(expr -> Arrays.stream(expr.split(" \\+")))
                    .toList());
            System.out.println("basicEffect = " + basicEffect);

            if (tierTooltip.contains("3")) {
                // 특성 수치 치/특/신/제/인/숙, 목걸이의 경우 2개
                System.out.println("3티어 악세서리에만 해당합니다-----------------------------------");
                String tendency = ((String) tooltip.getJSONObject("Element_005")
                        .getJSONObject("value")
                        .get("Element_001"))
                        .replaceAll("<BR>", " ").replaceAll("\\+", "");

                System.out.println("tendency = " + tendency);

                // 각인 효과, 이로운 효과 2개, 해로운 효과 1개
                getEngravingEffect(tooltip, "Element_000");
            } else {
                // 연마 효과
                System.out.println("4티어 악세서리에만 해당합니다-----------------------------------");
                String honeEffect = (String) tooltip.getJSONObject("Element_005").getJSONObject("value").get("Element_001");

                List<String> splitHoneEffect = textProcessor(honeEffect);

                List<HoneEffect> honeEffects = splitHoneEffect.stream()
                        .map(comp -> new HoneEffect(comp.split(" \\+")))
                        .toList();

                System.out.println("honeEffects = " + honeEffects);


                // 아크패시브 효과
                String[] arkpassiveEffect = getArkpassiveEffect(tooltip);
            }
        }

        // 어빌리티 스톤의 경우
        if (code == 11) {
            if (tier.equals("3")) {
                getEngravingEffect(tooltip, "Element_000");
            }
            if (tier.equals("4")) {
                getEngravingEffect(tooltip, "Element_001");
            }
        }
        // 팔찌의 경우
        if (code == 12) {

            String braceletEffectTooltip = (String) tooltip.getJSONObject("Element_004").getJSONObject("value").get("Element_001");
            List<String> braceletEffect = textProcessor(braceletEffectTooltip);
            System.out.println("braceletEffect = " + braceletEffect);
            if (tier.equals("4")) {
                String[] arkpassiveEffect = getArkpassiveEffect(tooltip);
            }
        }
    }

    public List<String> textProcessor(String input) {
        return Arrays.stream(input.split("<[^>]*>"))
                .filter(text -> !text.isEmpty())
                .toList();
    }
    private void getEngravingEffect(JSONObject tooltip, String target) throws JSONException {
        JSONObject engravingEffect = tooltip.getJSONObject("Element_006").getJSONObject("value").getJSONObject(target).getJSONObject("contentStr");
        String engraving1 = (String) engravingEffect.getJSONObject("Element_000").get("contentStr");
        System.out.println("engraving1 = " + engraving1);
        String engraving2 = (String) engravingEffect.getJSONObject("Element_001").get("contentStr");
        System.out.println("engraving2 = " + engraving2);
        String engraving3 = (String) engravingEffect.getJSONObject("Element_002").get("contentStr");
        System.out.println("engraving3 = " + engraving3);
    }

    private String[] getArkpassiveEffect(JSONObject tooltip) throws JSONException {
        String arkpassiveEffect = (String) tooltip.getJSONObject("Element_007").getJSONObject("value").get("Element_001");
        String[] splitArkpassiveEffect = arkpassiveEffect.split(" \\+");
        System.out.println("아크 패시브 " + splitArkpassiveEffect[0] + " +" + splitArkpassiveEffect[1]);
        return splitArkpassiveEffect;
    }


}