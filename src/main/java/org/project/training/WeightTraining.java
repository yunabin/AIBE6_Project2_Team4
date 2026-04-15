package org.project.training;

import org.project.model.Character;

public class WeightTraining extends Training {  // implements → extends!

    @Override
    protected void doExecute(Character character) {  // execute → doExecute!
        character.getStat().increaseStrength(10);
        character.getStat().increaseHealth(-5);
        System.out.println("🏋️  웨이트 훈련 완료!");
        System.out.println("근력 +10 | 체력 -5");
    }

    @Override
    public String getName() { return "웨이트 훈련"; }

    @Override
    public String getDescription() { return "근력을 올리지만 체력을 소모합니다."; }
}