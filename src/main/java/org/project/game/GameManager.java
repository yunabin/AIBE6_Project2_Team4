package org.project.game;

import org.project.battle.BattleSystem;
import org.project.event.EventManager;
import org.project.model.BodyType;
import org.project.model.Character;
import org.project.model.Rival;
import org.project.training.DietManagement;
import org.project.training.Training;
import org.project.view.ConsoleView;

import java.util.List;

public class GameManager {

    private final ConsoleView view = new ConsoleView();
    private final EventManager eventManager = new EventManager();
    private final BattleSystem battleSystem = new BattleSystem();
    private final RivalManager rivalManager = new RivalManager();

    private Character character;

    private final List<Training> trainings = List.of(
            new org.project.training.WeightTraining(),
            new org.project.training.CardioTraining(),
            new DietManagement()
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
        while (true) {
            view.printStatus(character);

            if (character.getStat().getHealth() <= 0) {
                view.printGameOver();
                break;
            }

            if (rivalManager.isAllDefeated()) {
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
                Rival currentRival = rivalManager.getCurrentRival();
                view.printRivalAppear(currentRival);

                if (battleSystem.battle(character, currentRival)) {
                    rivalManager.moveNext();
                }
            }
        }
    }
}