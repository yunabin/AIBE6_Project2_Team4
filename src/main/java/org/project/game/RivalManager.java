package org.project.game;

import org.project.model.Rival;
import org.project.model.Stat;

import java.util.List;

public class RivalManager {

    private final List<Rival> rivals;
    private int rivalIndex = 0;

    public RivalManager() {
        rivals = List.of(
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
    }


    public Rival getCurrentRival() {
        return rivals.get(rivalIndex);
    }

    public void moveNext() {
        rivalIndex++;
    }

    public boolean isAllDefeated() {
        return rivalIndex >= rivals.size();
    }
}