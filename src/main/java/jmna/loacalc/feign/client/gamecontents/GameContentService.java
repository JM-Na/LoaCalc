package jmna.loacalc.feign.client.gamecontents;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameContentService {

    private final GameContentClient gameContentClient;

    public GameContentService(GameContentClient gameContentClient) {
        this.gameContentClient = gameContentClient;
    }

    public List<ChallengeAbyssDungeon> getChallengeAbyssDungeon() {
        return gameContentClient.getChallengeAbyssDungeon();
    }

    public ChallengeGuardianRaid getChallengeGuardianRaid() {
        return gameContentClient.getChallengeGuardianRaid();
    }

    public List<GameContentsCalendar> getGameContentsCalendar() {
        return gameContentClient.getGameContentsCalendar();
    }
}
