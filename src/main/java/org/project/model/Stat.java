package org.project.model;

public class Stat {

    private int strength;
    private int endurance;
    private int health;

    public Stat(int strength, int endurance, int health) {
        this.strength = strength;
        this.endurance = endurance;
        this.health = health;
    }


    public void increaseStrength(int amount) {
        this.strength = Math.max(0, this.strength + amount);
    }

    public void increaseEndurance(int amount) {
        this.endurance = Math.max(0, this.endurance + amount);
    }

    public void increaseHealth(int amount) {
        this.health = Math.max(0, this.health + amount);
    }

    public int getTotalPower() {
        return strength * 2 + endurance + health;
    }


    public int getStrength()  { return strength; }
    public int getEndurance() { return endurance; }
    public int getHealth()    { return health; }

    @Override
    public String toString() {
        return String.format("근력: %d | 지구력: %d | 체력: %d | 총 전투력: %d",
                strength, endurance, health, getTotalPower());
    }
}
