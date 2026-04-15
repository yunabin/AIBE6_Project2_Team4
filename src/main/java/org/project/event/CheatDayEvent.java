package org.project.event;

import org.project.model.Character;

public class CheatDayEvent implements RandomEvent {

    @Override
    public void trigger(Character character) {
        character.getStat().increaseHealth(30);
        character.getStat().increaseStrength(-5);
        System.out.println("🍕 치팅데이 발생!");
        System.out.println("맛있는 거 먹었더니... 체력 +30 | 근력 -5");
    }

    @Override
    public String getName() { return "치팅데이"; }

    @Override
    public int getProbability() { return 20; }  // 20% 확률
}