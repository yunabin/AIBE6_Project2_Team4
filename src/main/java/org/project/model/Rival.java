package org.project.model;

public class Rival {

    private final String name;
    private final Stat stat;
    private final String taunt;
    private boolean defeated;

    public Rival(String name, Stat stat, String taunt) {
        this.name = name;
        this.stat = stat;
        this.taunt = taunt;
        this.defeated = false;
    }

    public String getName()     { return name; }
    public Stat getStat()       { return stat; }
    public String getTaunt()    { return taunt; }
    public boolean isDefeated() { return defeated; }
    public void setDefeated(boolean defeated) { this.defeated = defeated; }

    @Override
    public String toString() {
        return String.format("=== 라이벌: %s ===\n%s", name, stat.toString());
    }
}