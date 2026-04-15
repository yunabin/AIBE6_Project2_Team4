package org.project.training;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.project.model.BodyType;
import org.project.model.Character;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTest {

    private Character character;

    @BeforeEach
    void setUp() {
        character = new Character("테스트맨", BodyType.BALANCE);

    }

    @Test
    @DisplayName("웨이트 훈련 시 근력이 10 증가한다")
    void weightTrainingIncreasesStrength() {
        int before = character.getStat().getStrength();
        Training training = new WeightTraining();

        training.execute(character);

        assertEquals(before + 10, character.getStat().getStrength());
    }

    @Test
    @DisplayName("웨이트 훈련 시 체력이 5 감소한다")
    void weightTrainingDecreasesHealth() {
        int before = character.getStat().getHealth();
        Training training = new WeightTraining();

        training.execute(character);

        assertEquals(before - 5, character.getStat().getHealth());
    }

    @Test
    @DisplayName("부상 중에는 웨이트 훈련이 실행되지 않는다")
    void weightTrainingBlockedWhenInjured() {
        character.setInjured(true);
        int before = character.getStat().getStrength();
        Training training = new WeightTraining();

        training.execute(character);

        assertEquals(before, character.getStat().getStrength());
    }

    @Test
    @DisplayName("식단 관리는 부상 중에도 실행된다")
    void dietManagementWorksWhenInjured() {
        character.setInjured(true);
        int before = character.getStat().getHealth();
        Training training = new DietManagement();

        training.execute(character);

        assertTrue(character.getStat().getHealth() > before);
    }
}