package com.floppy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Polygon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Hitbox {
    private float yVelocity = 0f;
    private float gravity = 1400f;
    private float flapVelocity = 550f;
    private float maxYVelocity = -1600f;
    private float flapDelay = 0.1f;

    private boolean hasStarted = false;
    private boolean flapped = false;

    List<Sprite> playerSprites;
    int spriteIndex = 0;

    public Player(float x, float y, float width, float height) {
        super(x, y, width, height);

        hitBox.width = width * 0.65f;
        hitBox.x += width * 3000f;
        hitBox.height = height * 0.7f;

        float sizeModifier = 0.75f;

        Texture birdTex1 = new Texture("sprites/Bird1.png");
        Texture birdTex2 = new Texture("sprites/bird2.png");
        Texture birdTex3 = new Texture("sprites/Bird3.png");
        Sprite playerSprite1 = new Sprite(birdTex1);
        playerSprite1.setSize(width * sizeModifier, height * sizeModifier);
        Sprite playerSprite2 = new Sprite(birdTex2);
        playerSprite2.setSize(width * sizeModifier, height * sizeModifier);
        Sprite playerSprite3 = new Sprite(birdTex3);
        playerSprite3.setSize(width * sizeModifier, height * sizeModifier);

        playerSprites = new ArrayList<>();
        playerSprites.add(playerSprite1);
        playerSprites.add(playerSprite2);
        playerSprites.add(playerSprite3);
    }

    public void update(float dt) {

        flap();
        animatePlayer(dt);
        if(hasStarted) {
            applyGravity(dt);
        }
        playerSprites.get(spriteIndex).setPosition(x, y);
        super.update(dt);
    }

    public void render(SpriteBatch batch) {
        playerSprites.get(spriteIndex).draw(batch);
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

    public void animatePlayer(float dt) {
        if(flapDelay > 0f) {
            flapDelay -= dt;
        } else {
            spriteIndex--;
            if(spriteIndex < 0) {
                spriteIndex = 2;
            }
            flapDelay = 0.1f;
        }

        /*
        if(flapped) {
            if(spriteIndex < playerSprites.size() - 1) {
                spriteIndex++;
            } else {

            }
        }
        */
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
