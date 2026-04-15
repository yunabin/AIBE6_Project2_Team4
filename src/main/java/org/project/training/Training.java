package org.project.training;
import org.project.model.Character;


public interface Training {
    void execute(Character character);
    String getName();
    String getDescription();
}
