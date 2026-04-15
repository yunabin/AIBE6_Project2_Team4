package org.project.training;

import org.project.model.Character;

public abstract class Training {

    public final void execute(Character character) {
        if (canNotExecute(character)) {
            System.out.println("⚠️  " + getName() + "을(를) 할 수 없습니다!");
            return;
        }
        doExecute(character);
    }

    protected abstract void doExecute(Character character);
    public abstract String getName();
    public abstract String getDescription();

    protected boolean canNotExecute(Character character) {
        return character.isInjured();
    }
}