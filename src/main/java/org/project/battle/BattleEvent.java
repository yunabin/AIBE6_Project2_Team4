package org.project.battle;

import org.project.model.Stat;

import java.util.Random;
import java.util.function.Function;

public class BattleEvent {

    private final String eventName;
    private final String emoji;
    private final Function<Stat, Integer> statExtractor;
    private final Random random = new Random();

    public BattleEvent(String eventName, String emoji,
                       Function<Stat, Integer> statExtractor) {
        this.eventName = eventName;
        this.emoji = emoji;
        this.statExtractor = statExtractor;
    }

    public int compete(Stat my, Stat rival) {
        int myScore    = statExtractor.apply(my)    + random.nextInt(20);
        int rivalScore = statExtractor.apply(rival) + random.nextInt(20);

        System.out.println("\n" + emoji + " [종목] " + eventName);
        System.out.println("내 점수: " + myScore + " | 라이벌 점수: " + rivalScore);

        if (myScore >= rivalScore) {
            System.out.println("✅ " + eventName + " 승리!");
            return 1;
        } else {
            System.out.println("❌ " + eventName + " 패배...");
            return 0;
        }
    }
}