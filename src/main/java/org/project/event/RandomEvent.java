package org.project.event;

import org.project.model.Character;

public interface RandomEvent {
    void trigger(Character character);
    String getName();
    int getProbability();
}