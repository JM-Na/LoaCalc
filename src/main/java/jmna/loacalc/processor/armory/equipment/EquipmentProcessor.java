package jmna.loacalc.processor.armory.equipment;

import com.fasterxml.jackson.databind.JavaType;
import jmna.loacalc.calculator.subequipments.BraceletEffectT3;
import jmna.loacalc.feign.client.armories.ArmoryEquipment;
import jmna.loacalc.processor.armory.equipment.accessory.*;
import jmna.loacalc.processor.armory.equipment.armory.Armor;
import jmna.loacalc.processor.armory.equipment.armory.BaseArmory;
import jmna.loacalc.processor.armory.equipment.armory.ElixirData;
import jmna.loacalc.processor.armory.equipment.armory.Weapon;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EquipmentProcessor {

    public CharacterEquipment parseEquipmentInfo(List<ArmoryEquipment> armoryEquipment) {

        CharacterEquipment characterEquipment = new CharacterEquipment();
        List<BaseArmory> baseArmories = new ArrayList<>();
        List<SubEquipment> subEquipments = new ArrayList<>();

        for (int code = 0; code < 13; code++) {
            ArmoryEquipment equipment = armoryEquipment.get(code);

            if (code < 6) {
                BaseArmory baseArmory = parseArmorWeapon(equipment, code);
                baseArmories.add(baseArmory);
            } else {
                SubEquipment subEquipment = parseSubEquipment(equipment, code);
                subEquipments.add(subEquipment);
            }
        }

        characterEquipment.setBaseArmories(baseArmories);
        characterEquipment.setSubEquipments(subEquipments);
        characterEquipment.setTotalTranscendence();

        return characterEquipment;
    }

    public void setBaseArmory(BaseArmory baseArmory, JSONObject tooltipObject) {
        String name = textProcessor((String) tooltipObject.getJSONObject("Element_000").get("value")).get(0);

        JSONObject value = tooltipObject.getJSONObject("Element_001").getJSONObject("value");

        // 강화 단계
        int honeLvl = Integer.parseInt(name.split(" ")[0].replace("+", ""));
        // 품질
        Integer quality = (Integer) value.get("qualityValue");
        // 장비 종류
        String type = textProcessor((String) value.get("leftStr0")).get(0).split(" ")[1];

        String[] test = textProcessor((String) value.get("leftStr2")).get(0).split(" ");
        // 티어
//        String tier = (test[3] + " " + test[4]).replace("(", "").replace(")", "");
        // 아이템 레벨
        int itemLvl = Integer.parseInt(test[2]);
        // 아이콘
        String icon = (String) value.getJSONObject("slotData").get("iconPath");

        baseArmory.setName(name);
        baseArmory.setType(type);
        baseArmory.setHoneLvl(honeLvl);
        baseArmory.setItemLvl(itemLvl);
        baseArmory.setQuality(quality);
        baseArmory.setIcon(icon);
    }

    public void setTranscendence(BaseArmory baseArmory, JSONObject tooltipObject, int count) {
        List<String> transcendenceProcessed = textProcessor((String) tooltipObject.getJSONObject("Element_" + String.format("%03d", (8 + count))).getJSONObject("value").getJSONObject("Element_000").get("topStr"));
        baseArmory.setTranscendenceLvl(Integer.valueOf(transcendenceProcessed.get(3)));
        baseArmory.setTranscendenceGrade(Integer.valueOf(transcendenceProcessed.get(5)));
    }

    public void setAdvancedHone(BaseArmory baseArmory, JSONObject tooltipObject) {
        Integer lvl = Integer.valueOf(textProcessor((String) tooltipObject.getJSONObject("Element_005").get("value")).get(2));
        baseArmory.setAdvancedHone(lvl);
    }

    public BaseArmory parseArmorWeapon(ArmoryEquipment equipment, int code) {
        // \n 등의 개행 문자를 모두 띄어쓰기로 변환
        String tooltip = equipment.getTooltip();

        JSONObject tooltipObject = new JSONObject(tooltip);

        int count = 0;

        // 무기일 경우
        if (code == 0) {
            Weapon weapon = new Weapon();
//            System.out.println("tooltip = " + tooltip);
            setBaseArmory(weapon, tooltipObject);
            if (isAdvancedHoned(tooltipObject)) {
                setAdvancedHone(weapon, tooltipObject);
                count++;
            }

            setWeaponBaseAndAdditionalEffect(tooltipObject, weapon, count);

            if (isTranscendenceApplied(tooltipObject, count)) {
                setTranscendence(weapon, tooltipObject, count);
            }

            return weapon;
        }

        //무기를 제외한 방어구의 경우
        if (code > 0) {

            Armor armor = new Armor();

            setBaseArmory(armor, tooltipObject);

            if (isAdvancedHoned(tooltipObject)) {
                setAdvancedHone(armor, tooltipObject);
                count++;
            }

            setArmorBaseAndAdditionalEffect(tooltipObject, armor, count);

            if (isTranscendenceApplied(tooltipObject, count)) {
                setTranscendence(armor, tooltipObject, count);
                count++;
            }

            if (isElixirApplied(tooltipObject, count)) {
                setElixir(tooltipObject, armor, count);
            }
            return armor;
        }

        return null;
    }

    private void setElixir(JSONObject tooltipObject, Armor armor, int count) {
        List<ElixirData> elixirData = new ArrayList<>();

        JSONObject elixirEffectsTooltip = tooltipObject.getJSONObject("Element_" + String.format("%03d", 8 + count)).getJSONObject("value").getJSONObject("Element_000").getJSONObject("contentStr");

        List<String> effect1 = textProcessor((String) elixirEffectsTooltip.getJSONObject("Element_000").get("contentStr"));
        ElixirData elixirData1 = new ElixirData(armor.getType(), effect1.get(0), effect1.get(1).trim(), Integer.valueOf(effect1.get(2).replace("Lv.", "")), Double.valueOf(effect1.get(3).split(" \\+")[1].replace("%", "")));
        elixirData.add(elixirData1);

        List<String> effect2 = textProcessor((String) elixirEffectsTooltip.getJSONObject("Element_001").get("contentStr"));
        ElixirData elixirData2 = new ElixirData(armor.getType(), effect2.get(0), effect2.get(1).trim(), Integer.valueOf(effect2.get(2).replace("Lv.", "")), Double.valueOf(effect2.get(3).split(" \\+")[1].replace("%", "")));
        elixirData.add(elixirData2);

        armor.setElixirData(elixirData);
    }

    private static boolean isElixirApplied(JSONObject tooltipObject, int count) {
        return String.valueOf(tooltipObject.getJSONObject("Element_" + String.format("%03d", 8 + count))).contains("엘릭서");
    }

    private static boolean isTranscendenceApplied(JSONObject tooltipObject, int count) {
        return String.valueOf(tooltipObject.getJSONObject("Element_00" + (8 + count))).contains("Transcendence");
    }

    private static void setArmorBaseAndAdditionalEffect(JSONObject tooltipObject, Armor armor, int count) {
        String baseEffectTooltip = (String) tooltipObject.getJSONObject("Element_" + String.format("%03d", (5 + count))).getJSONObject("value").get("Element_001");
        String[] baseEffects = baseEffectTooltip.split("<BR>");
        for (String baseEffect : baseEffects) {
            String[] effect = baseEffect.split(" \\+");
            switch (effect[0]) {
                case "물리 방어력" -> armor.setPhyDefense(Integer.valueOf(effect[1]));
                case "마법 방어력" -> armor.setMagDefense(Integer.valueOf(effect[1]));
                case "힘", "민첩", "지능" -> armor.setMainStat(Integer.valueOf(effect[1]));
                case "체력" -> armor.setVitality(Integer.valueOf(effect[1]));
                default -> {
                }
            }
        }
        String addEffect = (String) tooltipObject.getJSONObject("Element_" + String.format("%03d", (6 + count))).getJSONObject("value").get("Element_001");
        armor.setVigor(Integer.valueOf(addEffect.split(" \\+")[1]));
    }

    private void setWeaponBaseAndAdditionalEffect(JSONObject tooltipObject, Weapon weapon, int count) {
        String s = (String) tooltipObject.getJSONObject("Element_" + String.format("%03d", (5 + count))).getJSONObject("value").get("Element_001");
        int weaponPower = Integer.parseInt(textProcessor(s).get(0).split(" \\+")[1]);
        String s2 = (String) tooltipObject.getJSONObject("Element_" + String.format("%03d", (6 + count))).getJSONObject("value").get("Element_001");
        String addDmg = String.valueOf(textProcessor(s2).get(0).split(" \\+")[1]);
        weapon.setWeaponPower(weaponPower);
        weapon.setAddDmg(Double.valueOf(addDmg.replace("%", "")));
    }

    private static boolean isAdvancedHoned(JSONObject tooltipObject) {
        return String.valueOf(tooltipObject.getJSONObject("Element_005")).contains("상급 재련");
    }

    public SubEquipment parseSubEquipment(ArmoryEquipment equipment, int code) {

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
            Accessory accessory = new Accessory(type, name, quality, grade, tier);
            return parseAccessory(accessory, tooltip, tier);
        } else if (code == 11) {
            AbilityStone abilityStone = new AbilityStone(type, name, quality, grade, tier);
            return parseAbilityStone(abilityStone, tooltip, tier);
        } else if (code == 12) {
            Bracelet bracelet = new Bracelet(type, name, quality, grade, tier);
            return parseBracelet(bracelet, tooltip, tier);
        } else {
            return null;
        }
    }

    public Accessory parseAccessory(Accessory accessory, JSONObject tooltip, int tier) {

        // 기본 효과 힘/민/지/체력
        String basicEffectTooltip = (String) tooltip.getJSONObject("Element_004").getJSONObject("value").get("Element_001");
        List<String> splitBasicEffect = textProcessor(basicEffectTooltip);
        BasicEffect basicEffect = new BasicEffect(splitBasicEffect.stream()
                .flatMap(expr -> Arrays.stream(expr.split(" \\+")))
                .toList());
        accessory.setBasicEffect(basicEffect);

        if (tier == 3) {
            // 특성 수치 치/특/신/제/인/숙, 목걸이의 경우 2개
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
            List<EngravingData> engravingData = getEngravingEffect(tooltip, "Element_000");
            accessory.setEngravingData(engravingData);
        } else {
            // 연마 효과
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

        return accessory;
    }

    public AbilityStone parseAbilityStone(AbilityStone abilityStone, JSONObject tooltip, int tier) {
        List<EngravingData> engravingData = new ArrayList<>();

        if (tier == 3) {
            engravingData = getEngravingEffect(tooltip, "Element_000");
        } else if (tier == 4) {
            engravingData = getEngravingEffect(tooltip, "Element_000");
        } else {
            System.out.println("올바르지 않은 티어 정보입니다.");
        }
        abilityStone.setEngravingData(engravingData);
        return abilityStone;
    }

    public Bracelet parseBracelet(Bracelet bracelet, JSONObject tooltip, int tier) {
        String braceletEffectTooltip = (String) tooltip.getJSONObject("Element_004").getJSONObject("value").get("Element_001");

        String[] s = braceletEffectTooltip.replaceAll("<img[^>]*>", "\n").replaceAll("</img>", "").replaceAll("<BR>", " ").split("\n");

//        System.out.println("s = " + Arrays.toString(s));

        List<BraceletData> braceletDataList = new ArrayList<>();

        for (String s1 : s) {
//            System.out.println("s1 = " + s1);
            Pattern pattern = Pattern.compile("(\\S?\\S)\\s\\+(\\d){2,5}");
            Matcher matcher = pattern.matcher(s1);
            if (matcher.find()) {
                String[] s2 = s1.replaceAll(" ", "").split("\\+");

                BraceletData braceletData = new BraceletData(tier, s2[0], s2[1], false, null);
                braceletDataList.add(braceletData);
            }
            // 3티어 팔찌의 경우
            else if (tier == 3) {
                Pattern pattern2 = Pattern.compile("\\[<FONT COLOR='#F9F7D0'>(\\S\\S)</FONT>][^>]*<FONT COLOR='#99ff99'>(\\S{2,5})</FONT>");
                Matcher matcher2 = pattern2.matcher(s1);
                if (matcher2.find()) {
                    String name = matcher2.group(1);
                    String effect = matcher2.group(2).replace("%", "");

                    String grade = BraceletEffectT3.findGradeByNameAndEffect(name, effect);

                    BraceletData braceletData = new BraceletData(tier, name, effect, true, grade);
                    braceletDataList.add(braceletData);
                }
            } else if (tier == 4) {
                String s2 = s1.replaceAll("<[^>]*>", "");
//                System.out.println("s2 = " + s2);
                BraceletData braceletData = new BraceletData(tier, s2, s2, true, null);
                braceletDataList.add(braceletData);
            }
        }

//        System.out.println("braceletEffectList = " + braceletEffectList);
        if (tier == 4) {
            String[] arkpassiveEffect = getArkpassiveEffect(tooltip);
        }

        bracelet.setBraceletData(braceletDataList);
        return bracelet;
    }


    public List<String> textProcessor(String input) {
        return Arrays.stream(input.split("<[^>]*>"))
                .filter(text -> !text.isEmpty())
                .toList();
    }

    private List<EngravingData> getEngravingEffect(JSONObject tooltip, String target) throws JSONException {
        System.out.println("tooltip = " + tooltip);

        List<EngravingData> engravingDataList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject();

        // null인 경우는 세공 단계 보너스가 없는 경우이다.
        String s = "";
        if (s.equals(null))
            jsonObject = tooltip.getJSONObject("Element_005");
        else
            jsonObject = tooltip.getJSONObject("Element_006");

        JSONObject contentStr = jsonObject
                .getJSONObject("value")
                .getJSONObject(target)
                .getJSONObject("contentStr");

        for (int i = 0; i < 3; i++) {
            String contentStrEngraving = ((String) contentStr.getJSONObject("Element_00" + i).get("contentStr"))
                    .replaceAll("\\[", "")
                    .replaceAll("]", "");
            List<String> splitEngravingEffect = textProcessor(contentStrEngraving);
            EngravingData engravingData = new EngravingData(splitEngravingEffect.get(0), splitEngravingEffect.get(1));
            engravingDataList.add(engravingData);
        }
        return engravingDataList;
    }

    private String[] getArkpassiveEffect(JSONObject tooltip) throws JSONException {
        String arkpassiveEffect = (String) tooltip.getJSONObject("Element_007").getJSONObject("value").get("Element_001");
        return arkpassiveEffect.split(" \\+");
    }
}
