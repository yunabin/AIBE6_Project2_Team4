package org.project.training;
import org.project.model.Character;

public class WeightTraining implements Training {

    @Override
    public void execute(Character character) {
        if (character.isInjured()) {
            System.out.println("⚠️  부상 중이라 웨이트 훈련을 할 수 없습니다!");
            return;
        }
        character.getStat().increaseStrength(10);  // 근력 +10
        character.getStat().increaseHealth(-5);    // 체력 -5 (힘드니까!)
        System.out.println("🏋️  웨이트 훈련 완료!");
        System.out.println("근력 +10 | 체력 -5");
    }

    @Override
    public String getName() { return "웨이트 훈련"; }

    @Override
    public String getDescription() { return "근력을 올리지만 체력을 소모합니다." ; }
}