package com.floppy.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Obstacle {
    private final Sprite tubeTopSprite, tubeBotSprite;
    private final Vector2 positionTubeTop, positionTubeBot;
    private static final int tubeOpening = 240;
    private static final int lowestOpening = 70;
    private static final int randomBoundary = 340;
    private float xPos;
    private boolean hasGivenScore = false;

    private final ObstacleHitbox topHitbox;
    private final ObstacleHitbox botHitbox;

    public Obstacle(float x){
        xPos = x;
        tubeTopSprite = new Sprite(new Texture("TubeDown.png"));
        tubeBotSprite = new Sprite(new Texture("Tube.png"));

        Random randomNumber = new Random();

        positionTubeTop = new Vector2(x,randomNumber.nextInt(randomBoundary) + tubeOpening + lowestOpening);
        positionTubeBot = new Vector2(x, positionTubeTop.y - tubeOpening - tubeBotSprite.getTexture().getHeight());

        tubeTopSprite.setPosition(positionTubeTop.x, positionTubeTop.y);
        tubeBotSprite.setPosition(positionTubeBot.x, positionTubeBot.y);

        topHitbox = new ObstacleHitbox(positionTubeTop.x, positionTubeTop.y, tubeTopSprite.getWidth(), tubeTopSprite.getHeight());
        botHitbox = new ObstacleHitbox(positionTubeBot.x, positionTubeBot.y, tubeBotSprite.getWidth(), tubeBotSprite.getHeight());
    }

    public void update(float dt) {
        xPos -= 230f * dt;
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
        return topHitbox.checkCollision(h) || botHitbox.checkCollision(h);
    }

    public float getXPosition() {
        return xPos;
    }

    public boolean isHasGivenScore() {
        return hasGivenScore;
    }

    public void giveScore() {
        hasGivenScore = true;
    }
}
