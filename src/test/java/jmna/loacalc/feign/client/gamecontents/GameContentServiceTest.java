package jmna.loacalc.feign.client.gamecontents;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GameContentServiceTest {

    @Autowired
    private GameContentService gameContentService;

    @Test
    void getChallengeAbyssDungeon() {
        List<ChallengeAbyssDungeon> challengeAbyssDungeon = gameContentService.getChallengeAbyssDungeon();

        System.out.println("challengeAbyssDungeon = " + challengeAbyssDungeon);

        Assertions.assertThat(challengeAbyssDungeon).isNotNull();
    }

    @Test
    void getChallengeGuardianRaid() {
        ChallengeGuardianRaid challengeGuardianRaid = gameContentService.getChallengeGuardianRaid();

        System.out.println("challengeGuardianRaid = " + challengeGuardianRaid);

        Assertions.assertThat(challengeGuardianRaid).isNotNull();
    }

    @Test
    void getGameContentCalendar() {
        List<GameContentsCalendar> gameContentsCalendar = gameContentService.getGameContentsCalendar();

        System.out.println("gameContentsCalendar = " + gameContentsCalendar);

        Assertions.assertThat(gameContentsCalendar).isNotNull();
    }

}