package jmna.loacalc.calculator.arkpassive.enlightenment;

import jmna.loacalc.calculator.arkpassive.enlightenment.ArkpassiveEnlightenmentEffect;

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
}
