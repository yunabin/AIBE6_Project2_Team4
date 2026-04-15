package org.project.event;

import org.project.model.Character;

public class RivalTauntEvent implements RandomEvent {

    @Override
    public void trigger(Character character) {
        character.getStat().increaseStrength(15);
        character.getStat().increaseEndurance(10);
        System.out.println("💢 라이벌이 도발합니다!");
        System.out.println("\"라이벌한테 질 수 없어!\"");
        System.out.println("열받아서 추가 운동을 수행했다! 근력 +15 | 지구력 +10");
    }

    @Override
    public String getName() { return "라이벌 도발"; }

    @Override
    public int getProbability() { return 25; }
}