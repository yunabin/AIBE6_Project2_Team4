package org.project.battle;

import org.project.model.Character;
import org.project.model.Rival;
import org.project.model.Stat;

import java.util.List;

public class BattleSystem {

    private final List<BattleEvent> events = List.of(
            new BattleEvent("벤치프레스 (근력 대결)",      "🏋️ ", Stat::getStrength),
            new BattleEvent("스쿼트 (지구력 대결)",        "🦵 ", Stat::getEndurance),
            new BattleEvent("데드리프트 (총 전투력 대결)", "💀 ", Stat::getTotalPower)
    );

    public boolean battle(Character character, Rival rival) {
        System.out.println("\n==============================");
        System.out.println("⚔️  배틀 시작!");
        System.out.println(character.getName() + " VS " + rival.getName());
        System.out.println("==============================");
        System.out.println("💢 " + rival.getName() + ": \"" + rival.getTaunt() + "\"");
        System.out.println();

        Stat my = character.getStat();
        Stat rv = rival.getStat();

        int myWins = events.stream()
                .mapToInt(event -> event.compete(my, rv))
                .sum();

        System.out.println("\n==============================");
        System.out.println("📊 최종 결과: " + myWins + " 승 / " + (3 - myWins) + " 패");

        if (myWins >= 2) {
            System.out.println("🏆 " + character.getName() + " 승리!");
            rival.setDefeated(true);
            return true;
        } else {
            System.out.println("💀 " + rival.getName() + " 승리...");
            character.getStat().increaseStrength(-10);
            character.getStat().increaseHealth(-10);
            System.out.println("패배 패널티: 근력 -10 | 체력 -10");
            return false;
        }
    }
}