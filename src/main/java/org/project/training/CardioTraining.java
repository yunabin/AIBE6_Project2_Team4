package org.project.training;
import org.project.model.Character;

public class CardioTraining implements Training {

    @Override
    public void execute(Character character) {
        if (character.isInjured()) {
            System.out.println("⚠️  부상 중이라 유산소 훈련을 할 수 없습니다!");
            return;
        }
        character.getStat().increaseEndurance(10); // 지구력 +10
        character.getStat().increaseStrength(2);   // 근력 소폭 +2
        System.out.println("🏃  유산소 훈련 완료!");
        System.out.println("지구력 +10 | 근력 +2");
    }

    @Override
    public String getName() { return "유산소 훈련"; }

    @Override
    public String getDescription() { return "지구력을 올리고 근력도 소폭 상승합니다."; }
}
