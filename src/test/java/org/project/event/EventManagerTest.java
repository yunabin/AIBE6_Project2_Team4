package org.project.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.model.BodyType;
import org.project.model.Character;

import static org.junit.jupiter.api.Assertions.*;

class EventManagerTest {

    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character("테스트맨", BodyType.BALANCE);
    }

    @Test
    @DisplayName("부상 이벤트 발동 시 부상 상태가 된다")
    void injuryEventSetsInjured() {
        // given
        InjuryEvent injuryEvent = new InjuryEvent();

        // when
        injuryEvent.trigger(character);

        // then
        assertTrue(character.isInjured());
    }

    @Test
    @DisplayName("부상 이벤트 발동 시 체력이 20 감소한다")
    void injuryEventDecreasesHealth() {
        int before = character.getStat().getHealth();
        InjuryEvent injuryEvent = new InjuryEvent();

        injuryEvent.trigger(character);

        assertEquals(before - 20, character.getStat().getHealth());
    }

    @Test
    @DisplayName("치팅데이 이벤트 발동 시 체력이 30 증가한다")
    void cheatDayEventIncreasesHealth() {
        int before = character.getStat().getHealth();
        CheatDayEvent cheatDayEvent = new CheatDayEvent();

        cheatDayEvent.trigger(character);

        assertEquals(before + 30, character.getStat().getHealth());
    }

    @Test
    @DisplayName("라이벌 도발 이벤트 발동 시 근력이 15 증가한다")
    void rivalTauntEventIncreasesStrength() {
        int before = character.getStat().getStrength();
        RivalTauntEvent rivalTauntEvent = new RivalTauntEvent();

        rivalTauntEvent.trigger(character);

        assertEquals(before + 15, character.getStat().getStrength());
    }
}