package org.project.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

    class StatTest {

        private Stat stat;

        @BeforeEach
        void setUp() {
            stat = new Stat(60, 60, 60);
        }

        @Test
        @DisplayName("근력이 정상적으로 증가한다")
        void increaseStrength() {
            int before = stat.getStrength();
            stat.increaseStrength(10);
            assertEquals(before + 10, stat.getStrength());
        }

        @Test
        @DisplayName("스탯은 0 아래로 내려가지 않는다")
        void statCannotBelowZero() {
            Stat weakStat = new Stat(5, 5, 5);
            weakStat.increaseStrength(-100);
            assertEquals(0, weakStat.getStrength());
        }

        @Test
        @DisplayName("총 전투력은 근력x2 + 지구력 + 체력이다")
        void totalPower() {
            Stat stat = new Stat(50, 30, 20);
            int totalPower = stat.getTotalPower();
            assertEquals(50 * 2 + 30 + 20, totalPower);
        }
    }

