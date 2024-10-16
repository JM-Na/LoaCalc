package jmna.loacalc.feign.client.gamecontents;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "GameContentClient", url = "${smilegate.url}" + "/gamecontents")
public interface GameContentClient {

    @GetMapping("/challenge-abyss-dungeons")
    List<ChallengeAbyssDungeon> getChallengeAbyssDungeon();

    @GetMapping("/challenge-guardian-raids")
    ChallengeGuardianRaid getChallengeGuardianRaid();

    @GetMapping("/calendar")
    List<GameContentsCalendar> getGameContentsCalendar();
}
