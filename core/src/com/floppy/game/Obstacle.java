package com.floppy.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacle {
    private Texture tubeTop;
    private Texture tubeBot;
    private Sprite tubeTopSprite;
    private Sprite tubeBotSprite;
    private Vector2 positionTubeTop;
    private Vector2 positionTubeBot;
    private static final int tubeOpening = 250;
    private static final int lowestOpening = 120;
    private static final int randomBoundary = 330;
    private float speed = 200f;
    private float xPos;
    private Random randomNumber;

    private ObstacleHitbox topHitbox;
    private ObstacleHitbox botHitbox;

    public Obstacle(float x, float y, float width, float height){
        xPos = x;
        tubeTop = new Texture("TubeDown.png");
        tubeBot = new Texture("Tube.png");
        tubeTopSprite = new Sprite(tubeTop);
        tubeBotSprite = new Sprite(tubeBot);
        randomNumber = new Random();

        positionTubeTop = new Vector2(x,randomNumber.nextInt(randomBoundary) + tubeOpening + lowestOpening);
        positionTubeBot = new Vector2(x, positionTubeTop.y - tubeOpening - tubeBot.getHeight());

        tubeTopSprite.setPosition(positionTubeTop.x, positionTubeTop.y);
        tubeBotSprite.setPosition(positionTubeBot.x, positionTubeBot.y);

        topHitbox = new ObstacleHitbox(positionTubeTop.x, positionTubeTop.y, tubeTopSprite.getWidth(), tubeTopSprite.getHeight());
        botHitbox = new ObstacleHitbox(positionTubeBot.x, positionTubeBot.y, tubeBotSprite.getWidth(), tubeBotSprite.getHeight());
    }

    public void update(float dt) {
        xPos -= speed * dt;
        positionTubeBot.set(xPos, positionTubeBot.y);
        positionTubeTop.set(xPos, positionTubeTop.y);
        tubeTopSprite.setPosition(positionTubeTop.x, positionTubeTop.y);
        tubeBotSprite.setPosition(positionTubeBot.x, positionTubeBot.y);
        topHitbox.setPosition(positionTubeTop);
        botHitbox.setPosition(positionTubeBot);
        topHitbox.update(dt);
        botHitbox.update(dt);
    }

    public void render(SpriteBatch batch) {
        tubeTopSprite.draw(batch);
        tubeBotSprite.draw(batch);
    }

    public boolean checkPlayerCollision(Rectangle h) {
        if(topHitbox.checkPlayerCollision(h) || botHitbox.checkPlayerCollision(h)) {
            return true;
        }
        return false;
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

    public float getXPosition() {
        return xPos;
    }

    public ObstacleHitbox getTopHitbox() {
        return topHitbox;
    }
}
