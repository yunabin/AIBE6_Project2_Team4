package org.project.model;

public enum BodyType {

    BULK("벌크형", 80, 40, 60),
    BALANCE("밸런스형", 60, 60, 60),
    SLIM("슬림형", 50, 70, 50);

    private final String displayName;
    private final int initStrength;
    private final int initEndurance;
    private final int initHealth;

    BodyType(String displayName, int initStrength, int initEndurance, int initHealth) {
        this.displayName = displayName;
        this.initStrength = initStrength;
        this.initEndurance = initEndurance;
        this.initHealth = initHealth;
    }


    public Stat createInitialStat() {
        return new Stat(initStrength, initEndurance, initHealth);
    }

    public String getDisplayName() { return displayName; }
}
