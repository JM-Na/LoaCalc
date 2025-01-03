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
            if (lvl==3)
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
            if (lvl==3 || lvl ==1)
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
