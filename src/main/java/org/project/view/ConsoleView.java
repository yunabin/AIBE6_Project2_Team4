package org.project.view;

import org.project.model.BodyType;
import org.project.model.Character;
import org.project.model.Rival;
import org.project.training.Training;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ConsoleView {

    private final Scanner scanner = new Scanner(System.in);

    private int readInt() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.print("숫자를 입력해 주세요: ");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("숫자만 입력해 주세요: ");
            }
        }
    }

    public void printTitle() {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║      💪 근육맨 키우기 💪    ║");
        System.out.println("╚══════════════════════════════╝");
    }

    public String inputName() {
        System.out.print("근육맨 이름을 입력하세요: ");
        return scanner.nextLine();
    }

    public BodyType selectBodyType() {
        System.out.println("\n체형을 선택하세요:");
        System.out.println("1. 벌크형  (근력 80 | 지구력 40 | 체력 60)");
        System.out.println("2. 밸런스형 (근력 60 | 지구력 60 | 체력 60)");
        System.out.println("3. 슬림형  (근력 50 | 지구력 70 | 체력 50)");
        System.out.print("선택 (1~3): ");

        int choice = readInt();
        switch (choice) {
            case 1: return BodyType.BULK;
            case 2: return BodyType.BALANCE;
            case 3: return BodyType.SLIM;
            default:
                System.out.println("잘못된 입력입니다. 밸런스형으로 시작합니다.");
                return BodyType.BALANCE;
        }
    }

    public void printStatus(Character character) {
        System.out.println("\n┌──────────────────────────────┐");
        System.out.println("│        📊 현재 상태 📊      │");
        System.out.println("└──────────────────────────────┘");
        System.out.println(character.toString());
        if (character.isInjured()) {
            System.out.println("⚠️  상태: 부상 중");
        }
    }

    public Training selectTraining(List<Training> trainings) {
        System.out.println("\n훈련을 선택하세요:");

        IntStream.range(0, trainings.size())
                .forEach(i -> System.out.println(
                        (i + 1) + ". " + trainings.get(i).getName()
                                + " — " + trainings.get(i).getDescription()
                ));

        System.out.print("선택 (1~" + trainings.size() + "): ");
        int choice = readInt() - 1;

        if (choice < 0 || choice >= trainings.size()) {
            System.out.println("잘못된 입력입니다. 웨이트 훈련으로 진행합니다.");
            return trainings.get(0);
        }
        return trainings.get(choice);
    }

    public void printRivalAppear(Rival rival) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        ⚔️  라이벌 등장!       ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println(rival.toString());
    }

    public void printChampionEnding(Character character) {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║      🏆 챔피언 등극! 🏆     ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println(character.getName() + "이(가) 모든 라이벌을 꺾고");
        System.out.println("최강의 근육맨이 되었습니다!");
        System.out.println("\n최종 스탯");
        System.out.println(character.getStat().toString());
    }

    public void printGameOver() {
        System.out.println("\n╔══════════════════════════════╗");
        System.out.println("║        💀 게임 오버 💀      ║");
        System.out.println("╚══════════════════════════════╝");
        System.out.println("체력이 모두 소진됐습니다...");
        System.out.println("다시 도전해 보세요!");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }
}