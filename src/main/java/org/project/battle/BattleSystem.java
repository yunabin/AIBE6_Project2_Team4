package org.project.battle;

import org.project.model.Character;
import org.project.model.Rival;
import org.project.model.Stat;

import java.util.Random;

public class BattleSystem {

    private final Random random = new Random();

    public boolean battle(Character character, Rival rival) {
        System.out.println("\n==============================");
        System.out.println("⚔️  배틀 시작!");
        System.out.println(character.getName() + " VS " + rival.getName());
        System.out.println("==============================");

        System.out.println("💢 " + rival.getName() + ": \"" + rival.getTaunt() + "\"");
        System.out.println();

        int myWins = 0;

        myWins += benchPress(character.getStat(), rival.getStat());
        myWins += squat(character.getStat(), rival.getStat());
        myWins += deadLift(character.getStat(), rival.getStat());


        System.out.println("\n==============================");
        System.out.println("📊 최종 결과: " + myWins + " 승 / " + (3 - myWins) + " 패");

        if (myWins >= 2) {
            System.out.println("🏆 " + character.getName() + " 승리!");
            rival.setDefeated(true);
            return true;
        } else {
            System.out.println("💀 " + rival.getName() + " 승리...");
            // 패배 패널티
            character.getStat().increaseStrength(-10);
            character.getStat().increaseHealth(-10);
            System.out.println("패배 패널티: 근력 -10 | 체력 -10");
            return false;
        }
    }


    private int benchPress(Stat my, Stat rival) {
        System.out.println("\n🏋️  [종목 1] 벤치프레스 (근력 대결)");

        int myScore = my.getStrength() + random.nextInt(20);
        int rivalScore = rival.getStrength() + random.nextInt(20);

        System.out.println("내 점수: " + myScore + " | 라이벌 점수: " + rivalScore);

        if (myScore >= rivalScore) {
            System.out.println("✅ 벤치프레스 승리!");
            return 1;
        } else {
            System.out.println("❌ 벤치프레스 패배...");
            return 0;
        }
    }


    private int squat(Stat my, Stat rival) {
        System.out.println("\n🦵  [종목 2] 스쿼트 (지구력 대결)");

        int myScore = my.getEndurance() + random.nextInt(20);
        int rivalScore = rival.getEndurance() + random.nextInt(20);

        System.out.println("내 점수: " + myScore + " | 라이벌 점수: " + rivalScore);

        if (myScore >= rivalScore) {
            System.out.println("✅ 스쿼트 승리!");
            return 1;
        } else {
            System.out.println("❌ 스쿼트 패배...");
            return 0;
        }
    }


    private int deadLift(Stat my, Stat rival) {
        System.out.println("\n💀  [종목 3] 데드리프트 (총 전투력 대결)");

        int myScore = my.getTotalPower() + random.nextInt(20);
        int rivalScore = rival.getTotalPower() + random.nextInt(20);

        System.out.println("내 점수: " + myScore + " | 라이벌 점수: " + rivalScore);

        if (myScore >= rivalScore) {
            System.out.println("✅ 데드리프트 승리!");
            return 1;
        } else {
            System.out.println("❌ 데드리프트 패배...");
            return 0;
        }
    }
}