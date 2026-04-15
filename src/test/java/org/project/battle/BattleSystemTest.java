package org.project.battle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.model.BodyType;
import org.project.model.Character;
import org.project.model.Rival;
import org.project.model.Stat;

import static org.junit.jupiter.api.Assertions.*;

class BattleSystemTest {

    private BattleSystem battleSystem;
    private Character character;

    @BeforeEach
    void setUp() {
        battleSystem = new BattleSystem();
        character = new Character("테스트맨", BodyType.BULK);
    }

    @Test
    @DisplayName("스탯이 압도적으로 높으면 배틀에서 승리한다")
    void strongCharacterWinsBattle() {
        character.getStat().increaseStrength(9999);
        character.getStat().increaseEndurance(9999);
        character.getStat().increaseHealth(9999);

        Rival weakRival = new Rival("약한 민수",
                new Stat(1, 1, 1),
                "져도 괜찮아");

        boolean result = battleSystem.battle(character, weakRival);

        assertTrue(result);
    }

    @Test
    @DisplayName("배틀 패배 시 라이벌이 defeated 상태가 아니다")
    void losingBattleRivalNotDefeated() {
        Rival strongRival = new Rival("헬스왕",
                new Stat(9999, 9999, 9999),
                "덤벼");

        battleSystem.battle(character, strongRival);

        assertFalse(strongRival.isDefeated());
    }

    @Test
    @DisplayName("배틀 승리 시 라이벌이 defeated 상태가 된다")
    void winningBattleRivalDefeated() {
        character.getStat().increaseStrength(9999);
        character.getStat().increaseEndurance(9999);
        character.getStat().increaseHealth(9999);

        Rival weakRival = new Rival("약한 민수",
                new Stat(1, 1, 1),
                "져도 괜찮아");

        battleSystem.battle(character, weakRival);

        assertTrue(weakRival.isDefeated());
    }
}