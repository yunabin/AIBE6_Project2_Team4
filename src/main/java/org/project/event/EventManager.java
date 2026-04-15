package org.project.event;

import org.project.model.Character;

import java.util.List;
import java.util.Random;

public class EventManager {

    private final List<RandomEvent> events;
    private final Random random = new Random();

    public EventManager() {
        events = List.of(
                new InjuryEvent(),
                new CheatDayEvent(),
                new RivalTauntEvent()
        );
    }

    public void checkEvent(Character character) {
        events.stream()
                .filter(event -> random.nextInt(100) + 1 <= event.getProbability())
                .findFirst()
                .ifPresentOrElse(
                        event -> {
                            System.out.println("\n🎲 [이벤트 발생!]");
                            event.trigger(character);
                        },
                        () -> System.out.println("🎲 이번 턴은 특별한 이벤트가 없었습니다.")
                );
    }
}