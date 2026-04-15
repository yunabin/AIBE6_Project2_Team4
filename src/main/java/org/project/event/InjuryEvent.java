package org.project.event;

import org.project.model.Character;

public class InjuryEvent implements RandomEvent {

    @Override
    public void trigger(Character character) {
        character.setInjured();
        character.getStat().increaseHealth(-20);
        System.out.println("😱 부상이 발생했습니다!");
        System.out.println("다음 턴은 훈련할 수 없습니다. 체력 -20");
    }

    @Override
    public String getName() { return "부상"; }

    @Override
    public int getProbability() { return 15; }
}