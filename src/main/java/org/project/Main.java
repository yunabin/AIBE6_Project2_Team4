package org.project;

import org.project.game.GameManager;

public class Main {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.run();
    }
}