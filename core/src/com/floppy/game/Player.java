package com.floppy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private float x, y;
    private float width, height;
    private float yVelocity = 0f;
    private float gravity = 900f;
    private float flapVelocity = 360f;
    private float maxYVelocity = -1600f;

    Texture img;
    Sprite playerSprite;

    public Player(float x, float y) {
        this.x = x;
        this.y = y;

        width = 100f;
        height = 100f;

        img = new Texture("sprites/badlogic.jpg");
        playerSprite = new Sprite(img);
        playerSprite.setSize(width, height);
    }

    public void update(float dt) {
        flap();
        applyGravity(dt);
        playerSprite.setPosition(x, y);
    }

    public void render(SpriteBatch batch) {
        playerSprite.draw(batch);
    }

    void applyGravity(float dt) {
        yVelocity -= gravity * dt;
        if(yVelocity < maxYVelocity) {
            yVelocity = maxYVelocity;
        }

        y += yVelocity * dt;
    }

    void flap() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            yVelocity = flapVelocity;
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
