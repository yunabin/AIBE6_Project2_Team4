package org.project.model;

public class Character {

    private final String name;
    private final BodyType bodyType;
    private final Stat stat;
    private int turnCount;
    private final InjuryStatus injuryStatus;

    public Character(String name, BodyType bodyType) {
        this.name = name;
        this.bodyType = bodyType;
        this.stat = bodyType.createInitialStat();
        this.turnCount = 0;
        this.injuryStatus = new InjuryStatus();
    }

    public void nextTurn() {
        this.turnCount++;
        injuryStatus.nextTurn();
    }

    public boolean isInjured()  { return injuryStatus.isInjured(); }
    public void setInjured()    { injuryStatus.occur(); }

    public String getName()       { return name; }
    public BodyType getBodyType() { return bodyType; }
    public Stat getStat()         { return stat; }
    public int getTurnCount()     { return turnCount; }

    @Override
    public String toString() {
        return String.format("=== %s (%s) ===\n%s\n턴: %d",
                name, bodyType.getDisplayName(), stat.toString(), turnCount);
    }
}