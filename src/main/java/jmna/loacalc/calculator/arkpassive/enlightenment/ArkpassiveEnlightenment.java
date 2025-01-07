package jmna.loacalc.calculator.arkpassive.enlightenment;

import jmna.loacalc.calculator.arkpassive.ArkpassiveEvolutionEffect;
import jmna.loacalc.calculator.arkpassive.enlightenment.ArkpassiveEnlightenmentEffect;

import java.util.Arrays;

public enum ArkpassiveEnlightenment {
    GUNLANCER_1_1("워로드", 1, "창술 수련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 5);
        }
    },
    GUNLANCER_1_2("워로드", 1, "철옹성") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addDefenseStateShield(lvl * 10 + 20);
        }
    },
    GUNLANCER_2_1("워로드", 2, "정교함") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 20 - 10);
        }
    },
    GUNLANCER_2_2("워로드", 2, "전투 태세") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 3 + 3);
        }
    },
    GUNLANCER_3_1("워로드", 3, "효율 증대") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addMpRecovery(5);
            effect.addOutgoingDmg(lvl);
        }
    },
    GUNLANCER_3_2("워로드", 3, "고독한 기사") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6);
        }
    },
    GUNLANCER_3_3("워로드", 3, "슥련된 전술가") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 10);
        }
    },
    GUNLANCER_3_4("워로드", 3, "전술 훈련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 1.6);
            effect.addOutgoingDmg(lvl * 1.6);
        }
    },
    GUNLANCER_4_1("워로드", 4, "창의 달인") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl + 5);
        }
    },
    GUNLANCER_4_2("워로드", 4, "창격 태세") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 8);
        }
    },
    GUNLANCER_4_3("워로드", 4, "선봉장의 마음가짐") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg((lvl + 13) * 3);
            effect.removeOutgoingDmg(12);
            effect.addOutgoingDmg((lvl + 3) * 4);
        }
    },
    GUNLANCER_4_4("워로드", 4, "전술 이동") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(lvl * 1.6);
            effect.addOutgoingDmg(lvl * 0.8);
        }
    },
    BERSERKER_1_1("버서커", 1, "강인한 육체") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BERSERKER_1_2("버서커", 1, "광기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(lvl * 5);
            effect.addMoveSpeed(lvl * 5);
        }
    },
    BERSERKER_2_1("버서커", 2, "신체 활성") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BERSERKER_2_2("버서커", 2, "분노 반환") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BERSERKER_3_1("버서커", 3, "신체 각성") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl - 1);
        }
    },
    BERSERKER_3_2("버서커", 3, "광전사의 비기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 10);
        }
    },
    BERSERKER_3_3("버서커", 3, "차가운 분노") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6);
        }
    },
    BERSERKER_3_4("버서커", 3, "분노 자극") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 3);
        }
    },
    BERSERKER_4_1("버서커", 4, "분노 소모") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.5);
        }
    },
    BERSERKER_4_2("버서커", 4, "폭주 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6);
        }
    },
    BERSERKER_4_3("버서커", 4, "어둠 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 7);
        }
    },
    BERSERKER_4_4("버서커", 4, "쇄도") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.3);
        }
    },
    DESTROYER_1_1("디스트로이어", 1, "중력 갑옷") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    DESTROYER_1_2("디스트로이어", 1, "중력 충격") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    DESTROYER_2_1("디스트로이어", 2, "날카로운 해머") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 3);
        }
    },
    DESTROYER_2_2("디스트로이어", 2, "중력 충전") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    DESTROYER_3_1("디스트로이어", 3, "해방 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.8);
            // 중력 가중, 중력 해방? 아덴으로 보임
            effect.addOutgoingDmg(lvl * 1.6);
        }
    },
    DESTROYER_3_2("디스트로이어", 3, "분노의 망치") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 7);
        }
    },
    DESTROYER_3_3("디스트로이어", 3, "중력 수련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 10);
            int increment = lvl * 6;
            if (lvl == 3)
                increment += 2;
            effect.addOutgoingDmg(increment);
        }
    },
    DESTROYER_3_4("디스트로이어", 3, "영역 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 2);
        }
    },
    DESTROYER_4_1("디스트로이어", 4, "중력 변환") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 4);
        }
    },
    DESTROYER_4_2("디스트로이어", 4, "중력 해방") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(10);
            effect.addOutgoingDmg(lvl * 20 - 5);
        }
    },
    DESTROYER_4_3("디스트로이어", 4, "새로운 코어") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            int increment = lvl * 7;
            if (lvl == 3 || lvl == 1)
                increment += 1;
            effect.addOutgoingDmg(increment);
        }
    },
    DESTROYER_4_4("디스트로이어", 4, "중력 가속") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(lvl * 2);
        }
    },
    SLAYER_1_1("슬레이어", 1, "지치지 않는 힘") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SLAYER_1_2("슬레이어", 1, "끝나지 않는 분노") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SLAYER_2_1("슬레이어", 2, "강화된 기술") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 3);
        }
    },
    SLAYER_2_2("슬레이어", 2, "포식자") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SLAYER_3_1("슬레이어", 3, "갈증 해소") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 2);
        }
    },
    SLAYER_3_2("슬레이어", 3, "처단자") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 10);
        }
    },
    SLAYER_3_3("슬레이어", 3, "격분") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {

            effect.addCritDmg(lvl * 17);
        }
    },
    SLAYER_3_4("슬레이어", 3, "전투 본능") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl);
        }
    },
    SLAYER_4_1("슬레이어", 4, "사무치는 공포") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
        }
    },
    SLAYER_4_2("슬레이어", 4, "막을 수 없는 분노") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 4);
            effect.addOutgoingDmg(lvl * 4);
        }
    },
    SLAYER_4_3("슬레이어", 4, "깊어지는 분노") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 10);
        }
    },
    SLAYER_4_4("슬레이어", 4, "무모한 공격") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.4);
        }
    },
    PALADIN_1_1("홀리나이트", 1, "신성한 의무") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    PALADIN_1_2("홀리나이트", 1, "신성 보호") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    PALADIN_2_1("홀리나이트", 2, "신앙 수련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 3);
        }
    },
    PALADIN_2_2("홀리나이트", 2, "축복의 오라") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    PALADIN_3_1("홀리나이트", 3, "징벌 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl);
        }
    },
    PALADIN_3_2("홀리나이트", 3, "심판자") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 10);
        }
    },
    PALADIN_3_3("홀리나이트", 3, "신의 가호") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    PALADIN_3_4("홀리나이트", 3, "치유의 빛") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    PALADIN_4_1("홀리나이트", 4, "열성") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 1.6);
        }
    },
    PALADIN_4_2("홀리나이트", 4, "신의 기사") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(17.5 - lvl * 2.5);
            effect.addCritDmg(lvl * 5 + 10);
        }
    },
    PALADIN_4_3("홀리나이트", 4, "깊어진 신앙") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    PALADIN_4_4("홀리나이트", 4, "빛의 흔적") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SCRAPPER_1_1("인파이터", 1, "기력 회복") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SCRAPPER_1_2("인파이터", 1, "속도 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(20);
        }
    },
    SCRAPPER_2_1("인파이터", 2, "투지 회복") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SCRAPPER_2_2("인파이터", 2, "충격 회복") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SCRAPPER_3_1("인파이터", 3, "날카로운 타격") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 기력 스킬 치명타 피해 4/8/12/16/20, 충격 스킬 치명타 적중률 1/2/3/4/5
            effect.setStaminaSkillCritDmg(lvl * 5);
            effect.setShockSkillCritRate(lvl);
        }
    },
    SCRAPPER_3_2("인파이터", 3, "극의: 체술") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setShockSkillDmg(lvl * 15);
            effect.setShockSkillDmg(-30);
        }
    },
    SCRAPPER_3_3("인파이터", 3, "충격 단련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 7 - 1);
        }
    },
    SCRAPPER_3_4("인파이터", 3, "더킹 II") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.4);
        }
    },
    SCRAPPER_4_1("인파이터", 4, "치명적인 투지") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 2);
        }
    },
    SCRAPPER_4_2("인파이터", 4, "대지 가르기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 대지 가르기 스킬 추가, 0/100/200
        }
    },
    SCRAPPER_4_3("인파이터", 4, "가속화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 5 - 5);
        }
    },
    SCRAPPER_4_4("인파이터", 4, "일방 타격") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.5 - 0.5);
        }
    },
    WARDANCER_1_1("배틀마스터", 1, "강력한 체술") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 5);
        }
    },
    WARDANCER_1_2("배틀마스터", 1, "강력한 오의") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 5);
        }
    },
    WARDANCER_2_1("배틀마스터", 2, "원기 회복") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    WARDANCER_2_2("배틀마스터", 2, "구슬 증가") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    WARDANCER_3_1("배틀마스터", 3, "치명적인 체술") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 기력 스킬 치명타 피해 4/8/12/16/20, 충격 스킬 치명타 적중률 1/2/3/4/5
            effect.addCritDmg(lvl * 3);
        }
    },
    WARDANCER_3_2("배틀마스터", 3, "초심") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(10);
                case 2 -> effect.addOutgoingDmg(22);
                case 3 -> effect.addOutgoingDmg(35);
            }
        }
    },
    WARDANCER_3_3("배틀마스터", 3, "오의 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6);
        }
    },
    WARDANCER_3_4("배틀마스터", 3, "엘리멘탈 연소") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl);
        }
    },
    WARDANCER_4_1("배틀마스터", 4, "공수래") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 0.8);
        }
    },
    WARDANCER_4_2("배틀마스터", 4, "근원의 엘리멘탈") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 4);
            effect.addOutgoingDmg(lvl * 25 + 15);
        }
    },
    WARDANCER_4_3("배틀마스터", 4, "과소비") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.removeOutgoingDmg(18);
            effect.addOutgoingDmg(24);
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(24);
                case 2 -> effect.addOutgoingDmg(33);
                case 3 -> effect.addOutgoingDmg(42);
            }
        }
    },
    WARDANCER_4_4("배틀마스터", 4, "오의 준비") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 2.4);
        }
    },
    SOULFIST_1_1("기공사", 1, "세맥타통 I") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SOULFIST_1_2("기공사", 1, "역천지체") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAwakeningDmg(lvl * 7 - 1);
        }
    },
    SOULFIST_2_1("기공사", 2, "내공 금제") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SOULFIST_2_2("기공사", 2, "내공 활성") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SOULFIST_3_1("기공사", 3, "자연체") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 기력 스킬 치명타 피해 4/8/12/16/20, 충격 스킬 치명타 적중률 1/2/3/4/5
            effect.addCritRate(lvl * 3);
            effect.addOutgoingDmg(lvl);
        }
    },
    SOULFIST_3_2("기공사", 3, "세맥타통 II") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 7);
        }
    },
    SOULFIST_3_3("기공사", 3, "금강선공 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(13);
                case 2 -> effect.addOutgoingDmg(26);
                case 3 -> effect.addOutgoingDmg(40);
            }
        }
    },
    SOULFIST_3_4("기공사", 3, "날카로운 기공") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 4);
        }
    },
    SOULFIST_4_1("기공사", 4, "운기행공") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 2);
        }
    },
    SOULFIST_4_2("기공사", 4, "내공 폭발") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 25);
        }
    },
    SOULFIST_4_3("기공사", 4, "한계 돌파") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.removeOutgoingDmg(40);
            effect.addOutgoingDmg(40 * (lvl * 0.11 + 1));
            effect.addAtkSpeed(15 * (lvl * 0.11));
        }
    },
    SOULFIST_4_4("기공사", 4, "반동 제어") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.6);
        }
    },
    GLAIVIER_1_1("창술사", 1, "절제") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    GLAIVIER_1_2("창술사", 1, "절정 I") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            int increment = 0;
            switch (lvl) {
                case 1 -> increment = 6;
                case 2 -> increment = 9;
                case 3 -> increment = 15;
            }
            effect.setFlurryMoveSpeed(increment);
            effect.setFocusAtkSpeed(increment);
        }
    },
    GLAIVIER_2_1("창술사", 2, "난무 이동") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    GLAIVIER_2_2("창술사", 2, "절정 II") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addFlurryCritDmg(lvl * 20);
        }
    },
    GLAIVIER_3_1("창술사", 3, "치명적인 베기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addFlurryCritDmg(lvl * 4);
        }
    },
    GLAIVIER_3_2("창술사", 3, "난무 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addFlurryDmg(14);
                case 2 -> effect.addFlurryDmg(28);
                case 3 -> effect.addFlurryDmg(44);
            }

        }
    },
    GLAIVIER_3_3("창술사", 3, "절정 III") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addFocusDmg(8);
                case 2 -> effect.addFocusDmg(16);
                case 3 -> effect.addFocusDmg(25);
            }
        }
    },
    GLAIVIER_3_4("창술사", 3, "강력한 찌르기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addFocusDmg(lvl * 1.2);
        }
    },
    GLAIVIER_4_1("창술사", 4, "연가표식") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
        }
    },
    GLAIVIER_4_2("창술사", 4, "연가비기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    GLAIVIER_4_3("창술사", 4, "연가심공") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 연가 심공
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(23);
                case 2 -> effect.addOutgoingDmg(46);
                case 3 -> effect.addOutgoingDmg(70);
            }
        }
    },
    GLAIVIER_4_4("창술사", 4, "전환난무") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addFlurryDmg(lvl * 0.7);
        }
    },
    BREAKER_1_1("브레이커", 1, "권왕파천무") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(20);
        }
    },
    BREAKER_1_2("브레이커", 1, "수라의 길") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BREAKER_2_1("브레이커", 2, "단전 흐름") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(2);
                case 2 -> effect.addOutgoingDmg(7);
                case 3 -> effect.addOutgoingDmg(13);
            }
        }
    },
    BREAKER_2_2("브레이커", 2, "치명적인 주먹") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.setDmgByCritRate(60);
                case 2 -> effect.setDmgByCritRate(120);
                case 3 -> effect.setDmgByCritRate(200);
            }

        }
    },
    BREAKER_3_1("브레이커", 3, "권왕심법") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl);
        }
    },
    BREAKER_3_2("브레이커", 3, "권왕십이식 : 낙화 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 낙화 스킬 피해 25/50/80
        }
    },
    BREAKER_3_3("브레이커", 3, "수라강체") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addFocusDmg(8);
                case 2 -> effect.addFocusDmg(16);
                case 3 -> effect.addFocusDmg(25);
            }
        }
    },
    BREAKER_3_4("브레이커", 3, "전면전") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addFrontalDmg(lvl * 1.6);
        }
    },
    BREAKER_4_1("브레이커", 4, "호신강기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.6);
            effect.addAwakeningDmg(lvl * 0.6);
        }
    },
    BREAKER_4_2("브레이커", 4, "권왕십이식 : 풍랑") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 낙화 이후 풍랑 사용 가능, 풍랑 0/30/60, 치명타 적중률 15,
            effect.addOutgoingDmg(lvl * 4 - 4);
        }
    },
    BREAKER_4_3("브레이커", 4, "무아지경") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6 - 6);
        }
    },
    BREAKER_4_4("브레이커", 4, "호신투기 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(0);
                case 2 -> effect.addOutgoingDmg(1.2);
                case 3 -> effect.addOutgoingDmg(2.4);
                case 4 -> effect.addOutgoingDmg(3.7);
                case 5 -> effect.addOutgoingDmg(5);
            }
        }
    },
    STRIKER_1_1("스트라이커", 1, "오의난무") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    STRIKER_1_2("스트라이커", 1, "일격필살 I") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 12);
        }
    },
    STRIKER_2_1("스트라이커", 2, "답보") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 2);
        }
    },
    STRIKER_2_2("스트라이커", 2, "구슬 증가") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.removeOutgoingDmg(36);
            effect.addOutgoingDmg(48);
        }
    },
    STRIKER_3_1("스트라이커", 3, "후방 기습") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addBackDmg(lvl * 1.4);
        }
    },
    STRIKER_3_2("스트라이커", 3, "오의 집중") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 8);
        }
    },
    STRIKER_3_3("스트라이커", 3, "일격필살 II") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.removeOutgoingDmg(48);
            effect.addOutgoingDmg(48 + lvl * 32 + 2);
        }
    },
    STRIKER_3_4("스트라이커", 3, "치명적인 오의") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 4);
        }
    },
    STRIKER_4_1("스트라이커", 4, "체술 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 1.2);
            // 오의 스킬 사용 후 일반 스킬 피증
//            effect.addOutgoingDmg(lvl * 2);
        }
    },
    STRIKER_4_2("스트라이커", 4, "난무 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 일반 스킬 피증
//            effect.addOutgoingDmg(20);
            effect.addAtkSpeed(10);
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(5);
                case 2 -> effect.addOutgoingDmg(12);
                case 3 -> effect.addOutgoingDmg(20);
            }
        }
    },
    STRIKER_4_3("스트라이커", 4, "구슬의 축복") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(-20);
                case 2 -> effect.addOutgoingDmg(-17.3);
                case 3 -> effect.addOutgoingDmg(-14.5);
            }
        }
    },
    STRIKER_4_4("스트라이커", 4, "완전 충전") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(7);
                case 2 -> effect.addOutgoingDmg(8.2);
                case 3 -> effect.addOutgoingDmg(9.4);
                case 4 -> effect.addOutgoingDmg(10.6);
                case 5 -> effect.addOutgoingDmg(11.8);
            }
        }
    },
    ARTILLERIST_1_1("블래스터", 1, "포격 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTILLERIST_1_2("블래스터", 1, "화력 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTILLERIST_2_1("블래스터", 2, "포격 충전") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTILLERIST_2_2("블래스터", 2, "화력 지속") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTILLERIST_3_1("블래스터", 3, "신속 포격") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(lvl * 2);
            effect.addCritDmg(lvl * 4);
        }
    },
    ARTILLERIST_3_2("블래스터", 3, "포격 출력 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 4);
            effect.addCritRate(lvl * 10 + 10);
        }
    },
    ARTILLERIST_3_3("블래스터", 3, "과열") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 10 + 5);
        }
    },
    ARTILLERIST_3_4("블래스터", 3, "화력 유지") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2 - 1.2);
        }
    },
    ARTILLERIST_4_1("블래스터", 4, "위치 이동 시스템") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 2 - 2);
        }
    },
    ARTILLERIST_4_2("블래스터", 4, "A.C.T 호출") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 4 - 2);
            // 터렛 추가 1/2/3
        }
    },
    ARTILLERIST_4_3("블래스터", 4, "포화 공격") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6 - 3);
            // Z 스킬 추가
        }
    },
    ARTILLERIST_4_4("블래스터", 4, "오버히트") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 3 - 3);
            // 오버 히트 추가
        }
    },
    DEADEYE_1_1("데빌헌터", 1, "전술 탄환") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addCritRate(3);
                case 2 -> effect.addCritRate(6);
                case 3 -> effect.addCritRate(10);
            }
        }
    },
    DEADEYE_1_2("데빌헌터", 1, "핸드 거너") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(8);
            effect.addMoveSpeed(8);
        }
    },
    DEADEYE_2_1("데빌헌터", 2, "탄약 보충") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    DEADEYE_2_2("데빌헌터", 2, "화려한 발재간") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    DEADEYE_3_1("데빌헌터", 3, "해결사의 움직임") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addBackDmg(lvl * 0.8);
            effect.setPiercing(lvl);
        }
    },
    DEADEYE_3_2("데빌헌터", 3, "정밀 사격 훈련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    effect.addCritRate(4);
                    effect.addCritDmg(8);
                }
                case 2 -> {
                    effect.addCritRate(9);
                    effect.addCritDmg(11);
                }
                case 3 -> {
                    effect.addCritRate(14);
                    effect.addCritDmg(14);
                }
            }
        }
    },
    DEADEYE_3_3("데빌헌터", 3, "핸드건 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 25);
        }
    },
    DEADEYE_3_4("데빌헌터", 3, "퀵 드로우") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl);
        }
    },
    DEADEYE_4_1("데빌헌터", 4, "고폭탄") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
        }
    },
    DEADEYE_4_2("데빌헌터", 4, "전략적 군장") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(4);
                case 2 -> effect.addOutgoingDmg(12);
                case 3 -> effect.addOutgoingDmg(19);
            }
        }
    },
    DEADEYE_4_3("데빌헌터", 4, "비밀 병기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // Z 스킬 추가 0/100/200
        }
    },
    DEADEYE_4_4("데빌헌터", 4, "빛나는 탄") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.4);
        }
    },
    GUNSLINGER_1_1("건슬링어", 1, "피스메이커 - 핸드건") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    GUNSLINGER_1_2("건슬링어", 1, "사냥의 시간") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 5);
        }
    },
    GUNSLINGER_2_1("건슬링어", 2, "피스메이커 - 샷건") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    GUNSLINGER_2_2("건슬링어", 2, "급소 사격") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 9);
        }
    },
    GUNSLINGER_3_1("건슬링어", 3, "시크릿 불릿") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addBackDmg(lvl * 0.8);
            effect.setPiercing(lvl);
        }
    },
    GUNSLINGER_3_2("건슬링어", 3, "피스메이커 - 라이플") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    GUNSLINGER_3_3("건슬링어", 3, "라이플 숙련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 10);
        }
    },
    GUNSLINGER_3_4("건슬링어", 3, "급소 전문가") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 1.2);
        }
    },
    GUNSLINGER_4_1("건슬링어", 4, "총기 교체 기술") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl);
        }
    },
    GUNSLINGER_4_2("건슬링어", 4, "평화주의자") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> {
                    effect.addAtkSpeed(10);
                    effect.addCritRate(10);
                    effect.addOutgoingDmg(30);
                }
                case 2 -> {
                    effect.addAtkSpeed(13);
                    effect.addCritRate(12);
                    effect.addOutgoingDmg(36);
                }
                case 3 -> {
                    effect.addAtkSpeed(16);
                    effect.addCritRate(15);
                    effect.addOutgoingDmg(42);
                }
            }
        }
    },
    GUNSLINGER_4_3("건슬링어", 4, "어썰트 스나이퍼") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 2 + 6);
            // Z 스킬 추가 0/45/90
        }
    },
    GUNSLINGER_4_4("건슬링어", 4, "저격수의 의지") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(lvl * 0.9);
            effect.addMoveSpeed(lvl * 0.9);
            effect.addCritDmg(lvl * 3);
        }
    },
    SHARPSHOOTER_1_1("호크아이", 1, "죽음의 습격") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 4);
        }
    },
    SHARPSHOOTER_1_2("호크아이", 1, "두 번째 동료") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SHARPSHOOTER_2_1("호크아이", 2, "호크 게이지 회수") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SHARPSHOOTER_2_2("호크아이", 2, "호크 서포트") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setAtkPowerPercent(lvl * 5);
        }
    },
    SHARPSHOOTER_3_1("호크아이", 3, "페일 노트") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 4);
        }
    },
    SHARPSHOOTER_3_2("호크아이", 3, "최후의 표적") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 9);
        }
    },
    SHARPSHOOTER_3_3("호크아이", 3, "폭풍의 표적") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6);
        }
    },
    SHARPSHOOTER_3_4("호크아이", 3, "실버호크 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setSilverHawkBasicDmg(lvl * 5);
        }
    },
    SHARPSHOOTER_4_1("호크아이", 4, "마나 회수") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setSilverHawkSkillDmg(lvl * 2);
        }
    },
    SHARPSHOOTER_4_2("호크아이", 4, "실버호크 강습") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6);
        }
    },
    SHARPSHOOTER_4_3("호크아이", 4, "폭풍의 사냥꾼") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 8);
            effect.addMoveSpeed(12);
        }
    },
    SHARPSHOOTER_4_4("호크아이", 4, "딥러닝") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1, 2, 3 -> effect.addOutgoingDmg(3);
                case 4 -> effect.addOutgoingDmg(4);
                case 5 -> effect.addOutgoingDmg(5);
            }
        }
    },
    MACHINIST_1_1("스카우터", 1, "진화의 유산") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    MACHINIST_1_2("스카우터", 1, "아르데타인의 기술") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addMoveSpeed(10);
        }
    },
    MACHINIST_2_1("스카우터", 2, "오버 싱크") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    MACHINIST_2_2("스카우터", 2, "드론 방어 체계") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    MACHINIST_3_1("스카우터", 3, "코어 반응 증폭") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addSyncSkillDmg(lvl);
            effect.addJointSkillDmg(lvl * 3);
        }
    },
    MACHINIST_3_2("스카우터", 3, "전투 모드") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 7);
        }
    },
    MACHINIST_3_3("스카우터", 3, "기술 업그레이드") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 11 - 1);
        }
    },
    MACHINIST_3_4("스카우터", 3, "전술 재장전") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setSilverHawkBasicDmg(lvl * 5);
        }
    },
    MACHINIST_4_1("스카우터", 4, "자폭 시퀀스") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addSyncSkillDmg(lvl * 1.1 - 1.1);
        }
    },
    MACHINIST_4_2("스카우터", 4, "EX - 제로 포인트") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // X스킬 추가, 0/80/160
        }
    },
    MACHINIST_4_3("스카우터", 4, "코어 인챈트") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 3);
            effect.addOutgoingDmg(lvl * 4);
        }
    },
    MACHINIST_4_4("스카우터", 4, "최고의 합작") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addJointSkillDmg(lvl * 1.8);
        }
    },

    SORCERESS_1_1("소서리스", 1, "점화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SORCERESS_1_2("소서리스", 1, "환류") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.setCooldownReduction(3);
                case 2 -> effect.setCooldownReduction(6);
                case 3 -> effect.setCooldownReduction(10);
            }
        }
    },
    SORCERESS_2_1("소서리스", 2, "점화의 불씨") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setArcaneCritRate(lvl * 10);
        }
    },
    SORCERESS_2_2("소서리스", 2, "환류의 기운") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SORCERESS_3_1("소서리스", 3, "화력 충전") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addMainSkillDmg(lvl * 1.2);
        }
    },
    SORCERESS_3_2("소서리스", 3, "발화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.setArcaneCritDmg(18);
                case 2 -> effect.setArcaneCritDmg(36);
                case 3 -> effect.setArcaneCritDmg(55);
            }
        }
    },
    SORCERESS_3_3("소서리스", 3, "환류 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 11 - 1);
        }
    },
    SORCERESS_3_4("소서리스", 3, "해방 봉인") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
            effect.addArcaneDmg(-lvl);
        }
    },
    SORCERESS_4_1("소서리스", 4, "점멸 폭발") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addArcaneDmg(lvl);
        }
    },
    SORCERESS_4_2("소서리스", 4, "마나 순환") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addMainSkillDmg(lvl*5);
            switch (lvl) {
                case 1 -> effect.addArcaneDmg(3);
                case 2 -> effect.addArcaneDmg(6);
                case 3 -> effect.addArcaneDmg(10);
            }
        }
    },
    SORCERESS_4_3("소서리스", 4, "마력 충전") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.setAtkPowerPercent(7);
                case 2 -> effect.setAtkPowerPercent(14);
                case 3 -> effect.setAtkPowerPercent(22);
            }
        }
    },
    SORCERESS_4_4("소서리스", 4, "과부하") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.8);
        }
    },

    ARCANIST_1_1("아르카나", 1, "황후의 은총") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARCANIST_1_2("아르카나", 1, "황제의 칙령") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addEmperorDmg(lvl * 40 - 40);
        }
    },
    ARCANIST_2_1("아르카나", 2, "황후의 계략") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARCANIST_2_2("아르카나", 2, "황제의 하사품") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARCANIST_3_1("아르카나", 3, "황후의 탐욕") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addRuinSkillDmg(lvl * 0.9);
        }
    },
    ARCANIST_3_2("아르카나", 3, "황후의 연회") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addRuinStack4Dmg(lvl * 5);
        }
    },
    ARCANIST_3_3("아르카나", 3, "황제의 만찬") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addNormalSkillDmg(14);
                case 2 -> effect.addNormalSkillDmg(28);
                case 3 -> effect.addNormalSkillDmg(43);
            }
        }
    },
    ARCANIST_3_4("아르카나", 3, "황제의 자비") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addNormalSkillCritRate(lvl * 1.6);
        }
    },
    ARCANIST_4_1("아르카나", 4, "황후의 기사") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 황후의 기사 추가 0/100/200
        }
    },
    ARCANIST_4_2("아르카나", 4, "황후의 속삭임") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addRuinSkillDmg(lvl * 7 - 1);
        }
    },
    ARCANIST_4_3("아르카나", 4, "또 다른 황제") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 4.5 - 4.5);
            effect.addEmperorDmg(lvl * 40 - 40);
        }
    },
    ARCANIST_4_4("아르카나", 4, "황제의 심판") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addRuinSkillDmg(lvl * -2);
            effect.addNormalSkillDmg(lvl * 2);
        }
    },

    SUMMONER_1_1("서머너", 1, "넘치는 교감") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SUMMONER_1_2("서머너", 1, "상급 소환사") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SUMMONER_2_1("서머너", 2, "총명함") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SUMMONER_2_2("서머너", 2, "정신 집중") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addNormalSkillDmg(lvl * 5);
        }
    },
    SUMMONER_3_1("서머너", 3, "교감 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addNormalSkillDmg(2);
                case 2 -> effect.addNormalSkillDmg(4);
                case 3 -> effect.addNormalSkillDmg(6);
                case 4 -> effect.addNormalSkillDmg(9);
                case 5 -> effect.addNormalSkillDmg(12);
            }
        }
    },
    SUMMONER_3_2("서머너", 3, "정령의 교감") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(13);
                case 2 -> effect.addOutgoingDmg(26);
                case 3 -> effect.addOutgoingDmg(40);
            }
        }
    },
    SUMMONER_3_3("서머너", 3, "고대의 힘") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addCritRate(5);
                case 2 -> effect.addCritRate(10);
                case 3 -> effect.addCritRate(16);
            }
        }
    },
    SUMMONER_3_4("서머너", 3, "고대 기운 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 4);
        }
    },
    SUMMONER_4_1("서머너", 4, "정령의 분노") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 정령의 분노 스택 추가
        }
    },
    SUMMONER_4_2("서머너", 4, "교감의 결실") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 15);
        }
    },
    SUMMONER_4_3("서머너", 4, "고대의 축복") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 7 + 14);
        }
    },
    SUMMONER_4_4("서머너", 4, "고대의 속삭임") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setPhoenixDmg(lvl * 4 + 72);
        }
    },
    BARD_1_1("바드", 1, "구원의 선물") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_1_2("바드", 1, "진실된 용맹") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_2_1("바드", 2, "절실한 구원") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_2_2("바드", 2, "끝없는 찬가") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_3_1("바드", 3, "포용의 세레나데") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_3_2("바드", 3, "증폭의 세레나데") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_3_3("바드", 3, "용맹의 찬가") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_3_4("바드", 3, "전투의 찬가") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_4_1("바드", 4, "낙인의 세레나데") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_4_2("바드", 4, "합창의 세레나데") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_4_3("바드", 4, "찬가 : 템페스트") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    BARD_4_4("바드", 4, "방랑자") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SHADOWHUNTER_1_1("데모닉", 1, "멈출 수 없는 충동") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SHADOWHUNTER_1_2("데모닉", 1, "완벽한 억제") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SHADOWHUNTER_2_1("데모닉", 2, "본능 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SHADOWHUNTER_2_2("데모닉", 2, "잠식 제어") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SHADOWHUNTER_3_1("데모닉", 3, "혼돈 단련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
        }
    },
    SHADOWHUNTER_3_2("데모닉", 3, "혼돈 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritRate(lvl * 10);
        }
    },
    SHADOWHUNTER_3_3("데모닉", 3, "무기 단련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 12 - 4);
        }
    },
    SHADOWHUNTER_3_4("데모닉", 3, "잠식 흡수") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.6);
        }
    },
    SHADOWHUNTER_4_1("데모닉", 4, "침식") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 2.1);
        }
    },
    SHADOWHUNTER_4_2("데모닉", 4, "블러드 피어싱") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 블러드 피어싱 추가 0/70/140
        }
    },
    SHADOWHUNTER_4_3("데모닉", 4, "스톰 그라인딩") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // X스킬(스톰 그라인딩) 획득 10/40/80
        }
    },
    SHADOWHUNTER_4_4("데모닉", 4, "잠식 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
        }
    },
    DEATHBLADE_1_1("블레이드", 1, "버스트 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    DEATHBLADE_1_2("블레이드", 1, "신속한 일격") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    DEATHBLADE_2_1("블레이드", 2, "오브 압축") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addSurgeDmg(25);
                case 2 -> effect.addSurgeDmg(50);
                case 3 -> effect.addSurgeDmg(80);
            }
        }
    },
    DEATHBLADE_2_2("블레이드", 2, "잔재된 기운") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(lvl * 3 + 3);
            effect.addMoveSpeed(lvl * 3 + 3);
        }
    },
    DEATHBLADE_3_1("블레이드", 3, "오브 제어") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.8);
        }
    },
    DEATHBLADE_3_2("블레이드", 3, "한계 돌파") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 60스택에서 버스트의 데미지가 2배가 됨
        }
    },
    DEATHBLADE_3_3("블레이드", 3, "확고한 의지") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 16);
        }
    },
    DEATHBLADE_3_4("블레이드", 3, "검술 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
        }
    },
    DEATHBLADE_4_1("블레이드", 4, "에너지 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addSurgeDmg(lvl * 2);
        }
    },
    DEATHBLADE_4_2("블레이드", 4, "검기 압축") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addSurgeDmg(lvl * 14);
        }
    },
    DEATHBLADE_4_3("블레이드", 4, "극한의 몸놀림") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6);
        }
    },
    DEATHBLADE_4_4("블레이드", 4, "오브 순환") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.5);
        }
    },
    REAPER_1_1("리퍼", 1, "달의 소리") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 30);
            effect.addAtkSpeed(10);
        }
    },
    REAPER_1_2("리퍼", 1, "피냄새") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addCritRate(18);
                case 2 -> effect.addCritRate(20);
                case 3 -> effect.addCritRate(23);
            }
        }
    },
    REAPER_2_1("리퍼", 2, "유령 무희") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addCritRate(3);
                case 2 -> effect.addCritRate(6);
                case 3 -> effect.addCritRate(10);
            }
        }
    },
    REAPER_2_2("리퍼", 2, "굶주림") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    REAPER_3_1("리퍼", 3, "곡예사") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addCritDmg(lvl * 3);
        }
    },
    REAPER_3_2("리퍼", 3, "그림자 밟기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 21 + 94);
        }
    },
    REAPER_3_3("리퍼", 3, "갈증") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setAtkPowerPercent(lvl * 10 - 2);
        }
    },
    REAPER_3_4("리퍼", 3, "암살자의 손놀림") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addAtkSpeed(lvl);
            effect.addOutgoingDmg(lvl * 4);
        }
    },
    REAPER_4_1("리퍼", 4, "잠행") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 2 + 30);
        }
    },
    REAPER_4_2("리퍼", 4, "급소 확보") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addOutgoingDmg(25);
                case 2 -> effect.addOutgoingDmg(58);
                case 3 -> effect.addOutgoingDmg(80);
            }
        }
    },
    REAPER_4_3("리퍼", 4, "살인귀") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 20);
        }
    },
    REAPER_4_4("리퍼", 4, "혼돈 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
        }
    },
    SOULEATER_1_1("소울이터", 1, "영혼 친화력") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            switch (lvl) {
                case 1 -> effect.addCritRate(3);
                case 2 -> effect.addCritRate(8);
                case 3 -> effect.addCritRate(14);
            }
        }
    },
    SOULEATER_1_2("소울이터", 1, "그믐의 경계") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SOULEATER_2_1("소울이터", 2, "만월의 집행자") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
                effect.addOutgoingDmg(lvl * 3);
        }
    },
    SOULEATER_2_2("소울이터", 2, "죽음 연마") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 12 + 6);
        }
    },
    SOULEATER_3_1("소울이터", 3, "영혼 증폭") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.6);
        }
    },
    SOULEATER_3_2("소울이터", 3, "집행 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    SOULEATER_3_3("소울이터", 3, "허물어진 경계") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setAtkPowerPercent(lvl * 15 + 5);
        }
    },
    SOULEATER_3_4("소울이터", 3, "영혼 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.6);
        }
    },
    SOULEATER_4_1("소울이터", 4, "영혼 길잡이") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
        }
    },
    SOULEATER_4_2("소울이터", 4, "영혼 공명") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 6 - 6);
        }
    },
    SOULEATER_4_3("소울이터", 4, "영혼 참수") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 영혼 참수 추가 0/80/160
        }
    },
    SOULEATER_4_4("소울이터", 4, "영혼 제어") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl);
        }
    },
    AEROMANCER_1_1("기상술사", 1, "질풍 노도") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    AEROMANCER_1_2("기상술사", 1, "이슬비") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    AEROMANCER_2_1("기상술사", 2, "환기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    AEROMANCER_2_2("기상술사", 2, "비의 보호막") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    AEROMANCER_3_1("기상술사", 3, "자연의 흐름") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 1.2);
        }
    },
    AEROMANCER_3_2("기상술사", 3, "기민함") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.setBonusCritDmg(lvl * 40);
            effect.setBonusCritRate(lvl * 10);
        }
    },
    AEROMANCER_3_3("기상술사", 3, "맑은 날") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 10);
        }
    },
    AEROMANCER_3_4("기상술사", 3, "단련") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl);
            effect.addCritDmg(lvl * 4);
        }
    },
    AEROMANCER_4_1("기상술사", 4, "바람의 길") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            effect.addOutgoingDmg(lvl * 0.6);
        }
    },
    AEROMANCER_4_2("기상술사", 4, "공간 가르기") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // X스킬 추가 0/100/200
        }
    },
    AEROMANCER_4_3("기상술사", 4, "눈부신 나날들") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // X스킬 추가 10/40/70 * 5
        }
    },
    AEROMANCER_4_4("기상술사", 4, "수증기 충전") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
            // 4/8/12/16/20 * 3
        }
    },
    ARTIST_1_1("도화가", 1, "해의 조화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_1_2("도화가", 1, "회귀") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_2_1("도화가", 2, "만개") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_2_2("도화가", 2, "달의 조화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_3_1("도화가", 3, "멀어지는 햇살") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_3_2("도화가", 3, "해의 축복") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_3_3("도화가", 3, "달의 축복") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_3_4("도화가", 3, "달의 그림자") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_4_1("도화가", 4, "낙인 강화") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_4_2("도화가", 4, "저물어 가는 달") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_4_3("도화가", 4, "떠오르는 달") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ARTIST_4_4("도화가", 4, "방랑자") {
        @Override
        void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl) {
        }
    },
    ;

    private final String className;
    private final int tier;
    private final String nodeName;

    abstract void applyEffect(ArkpassiveEnlightenmentEffect effect, int lvl);

    ArkpassiveEnlightenment(String className, int tier, String nodeName) {
        this.className = className;
        this.tier = tier;
        this.nodeName = nodeName;
    }

    private static ArkpassiveEnlightenment of(String className, String nodeName) {
        return Arrays.stream(values())
                .filter(value -> value.className.equals(className) && value.nodeName.equals(nodeName))
                .findFirst()
                .orElse(null);
    }

    public static void applyEffect(String className, String nodeName, int lvl, ArkpassiveEnlightenmentEffect effect) {
        ArkpassiveEnlightenment target = of(className, nodeName);
        target.applyEffect(effect, lvl);
    }

}
