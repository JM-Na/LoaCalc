package jmna.loacalc.calculator;

import jmna.loacalc.calculator.accessory.*;
import jmna.loacalc.calculator.armory.Armor;
import jmna.loacalc.calculator.armory.BaseArmory;
import jmna.loacalc.calculator.armory.Weapon;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EquipmentProcessor {

    public void parseEquipmentInfo(String characterName) {
        List<ArmoryEquipment> armoryEquipment = null;

        for (int code = 0; code < 13; code++) {
            ArmoryEquipment equipment = armoryEquipment.get(code);

            if (code < 6) {
                parseArmorWeapon(equipment, code);
            } else {
                parseSubEquipment(equipment, code);
            }
        }
    }

    public void setBaseArmory(BaseArmory baseArmory, String tooltip) {
        Pattern patternArmory = Pattern.compile("\\+(\\d\\d)\\s*(\\S+(?:\\s+\\S+){0,3})</FONT></P>\" }, \"Element_001\": \\{ \"type\": \"ItemTitle\", \"value\": \\{ \"bEquip\": 0, \"leftStr0\": \"<FONT SIZE='12'><FONT COLOR='#E3C7A1'>(\\S+(?:\\s+\\S+){0,3})</FONT></FONT>\", \"leftStr1\": \"<FONT SIZE='14'>품질</FONT>\", \"leftStr2\": \"<FONT SIZE='14'>아이템 레벨 (\\d\\d\\d\\d) \\(티어 4\\)</FONT>\", \"qualityValue\": (\\d{1,3}), \"rightStr0\": \"<FONT SIZE='12'><FONT COLOR='#FFD200'>장착중</FONT></FONT>\", \"slotData\": \\{ \"advBookIcon\": 0, \"battleItemTypeIcon\": 0, \"cardIcon\": false, \"friendship\": 0, \"iconGrade\": 6, \"iconPath\": \"(\\S{0,100})\", ");
        Matcher matcherArmory = patternArmory.matcher(tooltip);

        // 패턴 매칭 결과를 통해 초월 단계, 수치 출력
        while (matcherArmory.find()) {
            baseArmory.setHoneLvl(Integer.parseInt(matcherArmory.group(1)));
            baseArmory.setName(matcherArmory.group(2));
            baseArmory.setType(matcherArmory.group(3));
            baseArmory.setItemLvl(Integer.parseInt(matcherArmory.group(4)));
            baseArmory.setQuality(Integer.parseInt(matcherArmory.group(5)));
            baseArmory.setIcon(matcherArmory.group(6));
        }
    }

    public void setTranscendence(BaseArmory baseArmory, String tooltip) {
        // 초월 확인
        Pattern patternTranscendence = Pattern.compile("\"topStr\": \"<FONT SIZE='12' COLOR='#A9D0F5'>슬롯 효과</FONT><BR><FONT COLOR='#FF9632'>\\[초월\\]</FONT> <FONT COLOR='#FFD200'>(\\d+)</FONT>단계 <img src='emoticon_Transcendence_Grade' width='18' height='18' vspace ='-4'></img>(\\d{1,2})");
        Matcher matcherTranscendence = patternTranscendence.matcher(tooltip);

        if (!matcherTranscendence.find()) {
            System.out.println("해당 장비는 초월 부여가 되어있지 않습니다.");
        }

        while (matcherTranscendence.find()) {
            baseArmory.setTranscendenceLvl(Integer.valueOf(matcherTranscendence.group(1)));
            baseArmory.setTranscendenceGrade(Integer.valueOf(matcherTranscendence.group(2)));
        }
    }

    public void setAdvancedHone(BaseArmory baseArmory, String tooltip) {
        // 상급 재련을 한 경우
        if (tooltip.contains("상급 재련")) {
            Pattern patternAdvanced = Pattern.compile("\\[상급 재련]</FONT> <FONT COLOR='#FFD200'>(\\d{1,2})</FONT>단계</FONT>\" ");
            Matcher matcherAdvanced = patternAdvanced.matcher(tooltip);
            while (matcherAdvanced.find()) {
                baseArmory.setAdvancedHone(Integer.valueOf(matcherAdvanced.group(1)));
            }
        } else {
            System.out.println("해당 장비는 상급 재련이 되어있지 않습니다.");
        }
    }

    public void parseArmorWeapon(ArmoryEquipment equipment, int code) {
        // \n 등의 개행 문자를 모두 띄어쓰기로 변환
        String tooltip = equipment.getTooltip().replaceAll("\\s+", " ");
        System.out.println("tooltip = " + tooltip);

        // 무기일 경우
        if (code == 0) {
            Weapon weapon = new Weapon();

            setBaseArmory(weapon, tooltip);

            Pattern patternWeapon = Pattern.compile("기본 효과</FONT>\", \"Element_001\": \"무기 공격력 \\+(\\d{3,10})\" } }, \"Element_007\": \\{ \"type\": \"ItemPartBox\", \"value\": \\{ \"Element_000\": \"<FONT COLOR='#A9D0F5'>추가 효과</FONT>\", \"Element_001\": \"추가 피해 \\+(\\S{2,6})\"");
            Matcher matcherWeapon = patternWeapon.matcher(tooltip);

            while (matcherWeapon.find()) {
                weapon.setWeaponPower(Integer.valueOf(matcherWeapon.group(1)));
                weapon.setAdditionalDmg(Integer.valueOf(matcherWeapon.group(2)));
            }

            setTranscendence(weapon, tooltip);
            setAdvancedHone(weapon, tooltip);
        }

        //무기를 제외한 방어구의 경우
        if (code > 0) {

            Armor armor = new Armor();

            setBaseArmory(armor, tooltip);

            Pattern patternArmor = Pattern.compile("기본 효과</FONT>\", \"Element_001\": \"물리 방어력 \\+(\\d{1,6})<BR>마법 방어력 \\+(\\d{1,6})<BR>힘 \\+(\\d{1,6})<BR>체력 \\+(\\d{1,6})\" } }, \"Element_007\": \\{ \"type\": \"ItemPartBox\", \"value\": \\{ \"Element_000\": \"<FONT COLOR='#A9D0F5'>추가 효과</FONT>\", \"Element_001\": \"생명 활성력 \\+(\\d{1,6})");
            Matcher matcherArmor = patternArmor.matcher(tooltip);

            while (matcherArmor.find()) {
                armor.setPhyDefense(Integer.valueOf(matcherArmor.group(1)));
                armor.setMagDefense(Integer.valueOf(matcherArmor.group(2)));
                armor.setMainStat(Integer.valueOf(matcherArmor.group(3)));
                armor.setVitality(Integer.valueOf(matcherArmor.group(4)));
                armor.setVigor(Integer.valueOf(matcherArmor.group(5)));
            }

            // 엘릭서 관련 부분이 존재할 경우에만 추출 실행
            if (tooltip.contains("\"topStr\": \"<FONT COLOR='#FFE65A'>[엘릭서]</FONT><br><font color='#91fe02'><FONT size='12'>")) {

                // 정규 표현식 패턴 정의
                Pattern patternElixir = Pattern.compile("\\[(\\S\\S)\\]</FONT>\\s*(\\S+(?:\\s+\\S+){0,1})\\s*<FONT color='#FFD200'>Lv\\.(\\d+)</FONT><br>");
                Matcher matcherElixir = patternElixir.matcher(tooltip);

                List<ElixirEffect> elixirEffects = null;
                // 패턴 매칭 결과를 통해 엘릭서의 부위, 효과, 단계 출력
                while (matcherElixir.find()) {
                    if (matcherElixir.group(1) != null && matcherElixir.group(2) != null && matcherElixir.group(3) != null) {
                        ElixirEffect elixirEffect = new ElixirEffect(
                                matcherElixir.group(1),
                                matcherElixir.group(2),
                                Integer.valueOf(matcherElixir.group(3)));
                        elixirEffects.add(elixirEffect);
                    }
                }
                armor.setElixirEffects(elixirEffects);
            } else {
                System.out.println("엘릭서가 적용되지 않은 방어구입니다.");
            }

            setTranscendence(armor, tooltip);
            setAdvancedHone(armor, tooltip);
        }

    }

    public void setBaseSubEquipment(SubEquipment subEquipment, ArmoryEquipment equipment) {

    }

    public void parseSubEquipment(ArmoryEquipment equipment, int code) {

        // 장비 종류
        String type = equipment.getType();
        // 장비 명
        String name = equipment.getName();

        JSONObject tooltip = new JSONObject(equipment.getTooltip());

        JSONObject value = tooltip.getJSONObject("Element_001").getJSONObject("value");

        // 품질
        Integer quality = (Integer) value.get("qualityValue");
        // 등급
        String grade = textProcessor((String) value.get("leftStr0")).get(0).split(" ")[0];
        // 아이템 티어
        int tier = Integer.parseInt(textProcessor((String) value.get("leftStr2")).get(0).split(" ")[2]);
        // 귀속 여부, 거래 가능 횟수, 거래 제한 아이템 레벨
        List<String> splitItemInfo = textProcessor((String) tooltip.getJSONObject("Element_002").get("value"));

        if (code < 11) {
            parseAccessory(tooltip, tier);
        } else if (code == 11) {
            parseAbilityStone(tooltip, tier);
        } else if (code == 12) {
            parseBracelet(tooltip, tier);
        }
    }

    public void parseAccessory(JSONObject tooltip, int tier) {
        Accessory accessory = new Accessory();

        // 기본 효과 힘/민/지/체력
        String basicEffectTooltip = (String) tooltip.getJSONObject("Element_004").getJSONObject("value").get("Element_001");
        List<String> splitBasicEffect = textProcessor(basicEffectTooltip);
        BasicEffect basicEffect = new BasicEffect(splitBasicEffect.stream()
                .flatMap(expr -> Arrays.stream(expr.split(" \\+")))
                .toList());
        accessory.setBasicEffect(basicEffect);

        if (tier == 3) {
            // 특성 수치 치/특/신/제/인/숙, 목걸이의 경우 2개
            System.out.println("3티어 악세서리에만 해당합니다-----------------------------------");
            String[] tendencies = ((String) tooltip.getJSONObject("Element_005")
                    .getJSONObject("value")
                    .get("Element_001"))
                    .replaceAll("\\+", "")
                    .split("<BR>");

            List<Tendency> tendencyList = Arrays.stream(tendencies)
                    .map(comp -> new Tendency(comp.split(" ")))
                    .toList();

            accessory.setTendencies(tendencyList);

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

            accessory.setHoneEffects(honeEffects);

            // 아크패시브 효과
            String[] arkpassiveEffect = getArkpassiveEffect(tooltip);
            accessory.setArkpassiveEffect(Integer.valueOf(arkpassiveEffect[1]));
        }
    }

    public void parseAbilityStone(JSONObject tooltip, int tier) {
        if (tier == 3) {
            getEngravingEffect(tooltip, "Element_000");
        }
        if (tier == 4) {
            getEngravingEffect(tooltip, "Element_001");
        }
    }

    public void parseBracelet(JSONObject tooltip, int tier) {
        String braceletEffectTooltip = (String) tooltip.getJSONObject("Element_004").getJSONObject("value").get("Element_001");
        List<String> braceletEffect = textProcessor(braceletEffectTooltip);
        System.out.println("braceletEffect = " + braceletEffect);
        if (tier == 4) {
            String[] arkpassiveEffect = getArkpassiveEffect(tooltip);
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
