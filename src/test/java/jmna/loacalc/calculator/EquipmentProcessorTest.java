package jmna.loacalc.calculator;

import jmna.loacalc.calculator.accessory.BraceletEffect;
import jmna.loacalc.calculator.accessory.Tier3Bracelet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class EquipmentProcessorTest {

    @Autowired
    private EquipmentProcessor equipmentProcessor;

    @Test
    void testEquipmentObject() {
        equipmentProcessor.parseEquipmentInfo("XIEL");
    }

    @Test
    void splitTest() {
        String text_xiel = "<img src='emoticon_tooltip_bracelet_locked' vspace='-5'></img> 치명 +72<BR><img src='emoticon_tooltip_bracelet_locked' vspace='-5'></img> [<FONT COLOR='#F9F7D0'>순환</FONT>] 몬스터에게 공격 적중 시 30초 동안 '순환' 효과를 획득한다. 해당 효과는 갱신되지 않는다.<BR>순환 : 10초 간격으로 스킬 피해 <FONT COLOR='#99ff99'>3.5%</FONT> 증가, 치명타 적중률 <FONT COLOR='#99ff99'>6%</FONT> 증가, 치명타 피해 <FONT COLOR='#99ff99'>10%</FONT> 증가 효과가 순차적으로 적용된다. <FONT COLOR='#969696'>(60레벨 초과 몬스터에게는 효과 감소)</FONT><BR><img src='emoticon_tooltip_bracelet_locked' vspace='-5'></img> 특화 +82<BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>[<FONT COLOR='#F9F7D0'>망치</FONT>] 몬스터에게 공격 적중 시 8초 동안 '망치' 효과를 획득한다.<BR>'강철 쐐기' 효과를 보유 시 치명타 피해가 8% 추가 증가한다.<BR>망치 : 공격 적중 시 치명타 피해량이 <FONT COLOR='#99ff99'>10%</FONT> 증가한다. <FONT COLOR='#969696'>(60레벨 초과 몬스터에게는 효과 감소)</FONT>";
        String text3 = "<img src='emoticon_tooltip_bracelet_locked' vspace='-5'></img> 치명 +95<BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>[<FONT COLOR='#F9F7D0'>망치</FONT>] 몬스터에게 공격 적중 시 8초 동안 '망치' 효과를 획득한다.<BR>'강철 쐐기' 효과를 보유 시 치명타 피해가 8% 추가 증가한다.<BR>망치 : 공격 적중 시 치명타 피해량이 <FONT COLOR='#99ff99'>12%</FONT> 증가한다. <FONT COLOR='#969696'>(60레벨 초과 몬스터에게는 효과 감소)</FONT><BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>신속 +118<BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>[<FONT COLOR='#F9F7D0'>수확</FONT>] 공격 적중 시 3%의 확률로 60초 간 '정기' 효과를 획득한다.<BR>정기: 무기 공격력이 <FONT COLOR='#99ff99'>220</FONT> 증가한다.(최대 10중첩)";
        String text4 = "<img src='emoticon_tooltip_bracelet_locked' vspace='-5'></img> 힘 +13121<BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>치명타 피해가 <FONT COLOR='#99FF99'>10%</FONT> 증가한다.<BR>공격이 치명타로 적중 시 적에게 주는 피해가 <FONT COLOR='#99ff99'>1.5%</FONT> 증가한다.<BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>신속 +114<BR><img src='emoticon_tooltip_bracelet_changeable' width='20' height='20' vspace='-6'></img>치명 +77";
        String[] s = text_xiel.replaceAll("<img[^>]*>", "\n").replaceAll("</img>", "").replaceAll("<BR>", " ").split("\n");

        System.out.println("s = " + Arrays.toString(s));

        int tier = 3;

        for (String s1 : s) {
            System.out.println("s1 = " + s1);
            Pattern pattern = Pattern.compile("(\\S?\\S)\\s\\+(\\d){2,5}");
            Matcher matcher = pattern.matcher(s1);
            if (matcher.find()) {
                String[] s2 = s1.replaceAll(" ", "").split("\\+");
                System.out.println("s2[1] = " + s2[0]);
                System.out.println("s2[2] = " + s2[1]);
            }
            // 3티어 팔찌의 경우
            else if (tier == 3){
                Pattern pattern2 = Pattern.compile("\\[<FONT COLOR='#F9F7D0'>(\\S\\S)</FONT>][^>]*<FONT COLOR='#99ff99'>(\\S{2,5})</FONT>");
                Matcher matcher2 = pattern2.matcher(s1);
                if (matcher2.find()) {

                    String name = matcher2.group(1);
                    String effect = matcher2.group(2);

                    String grade = Tier3Bracelet.findGradeByNameAndEffect(name, effect);

                    BraceletEffect braceletEffect = new BraceletEffect(tier, name, effect, grade);
                }
            } else if (tier ==4) {
                String s2 = s1.replaceAll("<[^>]*>", "");
                System.out.println("s2 = " + s2);
            }
        }
    }

}