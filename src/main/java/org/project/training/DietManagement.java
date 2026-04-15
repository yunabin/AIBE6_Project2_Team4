package org.project.training;
import org.project.model.Character;

public class DietManagement implements Training {

    @Override
    public void execute(Character character) {
        character.getStat().increaseHealth(15);    // 체력 +15
        character.getStat().increaseStrength(3);   // 근력 소폭 +3
        System.out.println("🥗  식단 관리 완료!");
        System.out.println("체력 +15 | 근력 +3");
    }

    @Override
    public String getName() { return "식단 관리"; }

    @Override
    public String getDescription() { return "체력을 회복하고 근력도 소폭 상승합니다."; }
}
