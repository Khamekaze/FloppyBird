package com.floppy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;

public class Player extends Hitbox {
    private float yVelocity = 0f;
    private float gravity = 1400f;
    private float flapVelocity = 550f;
    private float maxYVelocity = -1600f;

    private boolean hasStarted = false;
    private boolean flapped = false;

    Texture img;
    Sprite playerSprite;

    public Player(float x, float y, float width, float height) {
        super(x, y, width, height);

        hitBox.width = width * 0.85f;
        hitBox.x += width * 0.1f;

        img = new Texture("sprites/bird.png");
        playerSprite = new Sprite(img);
        playerSprite.setSize(width, height);
    }

    public void update(float dt) {

        flap();
        if(hasStarted) {
            applyGravity(dt);
        }
        playerSprite.setPosition(x, y);
        super.update(dt);
        hitBox.x += width * 0.1f;
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
            if(!hasStarted) {
                hasStarted = true;
            }
            if(!flapped) {
                yVelocity = flapVelocity;
                System.out.println("FLAP");
                flapped = true;
            }

        } else {
            flapped = false;
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

    public boolean hasStarted() {
        return hasStarted;
    }
}
