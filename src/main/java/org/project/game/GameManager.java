package org.project.game;

import org.project.battle.BattleSystem;
import org.project.event.EventManager;
import org.project.model.BodyType;
import org.project.model.Character;
import org.project.model.Rival;
import org.project.model.Stat;
import org.project.training.CardioTraining;
import org.project.training.DietManagement;
import org.project.training.Training;
import org.project.training.WeightTraining;
import org.project.view.ConsoleView;

import java.util.List;

public class GameManager {

    private final ConsoleView view = new ConsoleView();
    private final EventManager eventManager = new EventManager();
    private final BattleSystem battleSystem = new BattleSystem();

    private Character character;

    private final List<Training> trainings = List.of(
            new WeightTraining(),
            new CardioTraining(),
            new DietManagement()
    );

    private final List<Rival> rivals = List.of(
            new Rival("헬스 초보 김민수",
                    new Stat(50, 40, 50),
                    "나도 한 달 됐거든?"),
            new Rival("3년차 안득근",
                    new Stat(80, 70, 70),
                    "나는 걸음마보다 데드리프트를 먼저 배웠지."),
            new Rival("근손실 공포증 박손실",
                    new Stat(120, 100, 100),
                    "방금 너 숨 쉬었지? 그거 유산소야. 근손실 온다고!")
    );

    public void run() {
        view.printTitle();
        initCharacter();
        gameLoop();
    }

    private void initCharacter() {
        String name = view.inputName();
        BodyType bodyType = view.selectBodyType();
        this.character = new Character(name, bodyType);
        view.printMessage("\n" + character.getName()
                + " (" + character.getBodyType().getDisplayName()
                + ") 으로 시작합니다! 💪");
    }

    private void gameLoop() {
        int rivalIndex = 0;

        while (true) {
            view.printStatus(character);

            if (character.getStat().getHealth() <= 0) {
                view.printGameOver();
                break;
            }

            if (rivalIndex >= rivals.size()) {
                view.printChampionEnding(character);
                break;
            }

            boolean injuredThisTurn = character.isInjured();
            character.nextTurn();
            view.printMessage("\n🔔 [ " + character.getTurnCount() + " 턴 ]");

            if (injuredThisTurn) {
                view.printMessage("⚠️  부상 중입니다! 식단 관리만 가능합니다.");
                new DietManagement().execute(character);
            } else {
                Training training = view.selectTraining(trainings);
                training.execute(character);
            }

            eventManager.checkEvent(character);

            if (character.getTurnCount() % 5 == 0) {
                Rival currentRival = rivals.get(rivalIndex);
                view.printRivalAppear(currentRival);
                if (battleSystem.battle(character, currentRival)) {
                    rivalIndex++;
                }
            }
        }
    }
}