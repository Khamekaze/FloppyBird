package com.floppy.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacle {
    private Texture tubeTop;
    private Texture tubeBot;
    private Vector2 positionTubeTop;
    private Vector2 positionTubeBot;
    private static final int tubeOpening = 100;
    private static final int lowestOpening = 120;
    private static final int randomBoundary = 130;
    private Random randomNumber;

    public Obstacle(float x){
        tubeTop = new Texture("TubeDown.png");
        tubeBot = new Texture("Tube.png");
        randomNumber = new Random();

        positionTubeTop = new Vector2(x,randomNumber.nextInt(randomBoundary) + tubeOpening + lowestOpening);
        positionTubeBot = new Vector2(x, positionTubeTop.y - tubeOpening - tubeBot.getHeight());
    }

    public Texture getTubeTop() {
        return tubeTop;
    }

    public Texture getTubeBot() {
        return tubeBot;
    }

    public Vector2 getPositionTubeTop() {
        return positionTubeTop;
    }

    public Vector2 getPositionTubeBot() {
        return positionTubeBot;
    }
}
