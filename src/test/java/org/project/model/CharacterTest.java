package org.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character("테스트맨", BodyType.BULK);
    }

    @Test
    @DisplayName("벌크형 캐릭터의 초기 근력은 80이다")
    void bulkInitialStrength() {
        assertEquals(80, character.getStat().getStrength());
    }

    @Test
    @DisplayName("캐릭터 생성 시 부상 상태가 아니다")
    void initialNotInjured() {
        assertFalse(character.isInjured());
    }

    @Test
    @DisplayName("부상 설정 후 부상 상태가 된다")
    void setInjured() {
        character.setInjured(true);

        assertTrue(character.isInjured());
    }

    @Test
    @DisplayName("nextTurn 호출 시 턴이 1 증가한다")
    void nextTurnIncreaseTurnCount() {
        character.nextTurn();

        assertEquals(1, character.getTurnCount());
    }

    @Test
    @DisplayName("부상 상태에서 nextTurn 호출 시 부상이 회복된다")
    void nextTurnRecoverInjury() {
        character.setInjured(true);

        character.nextTurn();

        assertFalse(character.isInjured());
    }

    @Test
    @DisplayName("슬림형 캐릭터의 초기 지구력은 70이다")
    void slimInitialEndurance() {
        Character slimCharacter = new Character("슬림맨", BodyType.SLIM);
        assertEquals(70, slimCharacter.getStat().getEndurance());
    }
}