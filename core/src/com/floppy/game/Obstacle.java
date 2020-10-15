package com.floppy.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacle extends Hitbox {
    private Texture tubeTop;
    private Texture tubeBot;
    private Sprite tubeTopSprite;
    private Sprite tubeBotSprite;
    private Vector2 positionTubeTop;
    private Vector2 positionTubeBot;
    private static final int tubeOpening = 300;
    private static final int lowestOpening = 120;
    private static final int randomBoundary = 330;
    private float speed = 200f;
    private Random randomNumber;

    public Obstacle(float x, float y, float width, float height){
        super(x, y, width, height);
        tubeTop = new Texture("TubeDown.png");
        tubeBot = new Texture("Tube.png");
        tubeTopSprite = new Sprite(tubeTop);
        tubeBotSprite = new Sprite(tubeBot);
        randomNumber = new Random();

        positionTubeTop = new Vector2(x,randomNumber.nextInt(randomBoundary) + tubeOpening + lowestOpening);
        positionTubeBot = new Vector2(x, positionTubeTop.y - tubeOpening - tubeBot.getHeight());

        tubeTopSprite.setPosition(positionTubeTop.x, positionTubeTop.y);
        tubeBotSprite.setPosition(positionTubeBot.x, positionTubeBot.y);
    }

    public void update(float dt) {
        x -= speed * dt;
        positionTubeBot.set(x, positionTubeBot.y);
        positionTubeTop.set(x, positionTubeTop.y);
        tubeTopSprite.setPosition(positionTubeTop.x, positionTubeTop.y);
        tubeBotSprite.setPosition(positionTubeBot.x, positionTubeBot.y);
    }

    public void render(SpriteBatch batch) {
        tubeTopSprite.draw(batch);
        tubeBotSprite.draw(batch);
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
