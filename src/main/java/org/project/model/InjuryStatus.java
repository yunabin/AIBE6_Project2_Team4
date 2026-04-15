package org.project.model;

public class InjuryStatus {

    private boolean injured;
    private int remainingTurns;

    public InjuryStatus() {
        this.injured = false;
        this.remainingTurns = 0;
    }


    public void occur() {
        this.injured = true;
        this.remainingTurns = 1;
    }


    public void nextTurn() {
        if (!injured) return;

        remainingTurns--;
        if (remainingTurns <= 0) {
            this.injured = false;
        }
    }

    public boolean isInjured()  { return injured; }

    @Override
    public String toString() {
        if (!injured) return "정상";
        return "부상 중 (남은 턴: " + remainingTurns + ")";
    }
}