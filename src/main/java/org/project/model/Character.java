package org.project.model;

public class Character {

    private final String name;
    private final BodyType bodyType;
    private Stat stat;
    private int turnCount;
    private boolean injured;

    public Character(String name, BodyType bodyType) {
        this.name = name;
        this.bodyType = bodyType;
        this.stat = bodyType.createInitialStat();
        this.turnCount = 0;
        this.injured = false;
    }


    public void nextTurn() {
        this.turnCount++;
        if (injured) {
            System.out.println("부상 중이라 이번 턴은 훈련할 수 없습니다...");
            this.injured = false;
        }
    }

    public boolean isInjured()          { return injured; }
    public void setInjured(boolean injured) { this.injured = injured; }

    public String getName()     { return name; }
    public BodyType getBodyType() { return bodyType; }
    public Stat getStat()       { return stat; }
    public int getTurnCount()   { return turnCount; }

    @Override
    public String toString() {
        return String.format("=== %s (%s) ===\n%s\n턴: %d",
                name, bodyType.getDisplayName(), stat.toString(), turnCount);
    }
}