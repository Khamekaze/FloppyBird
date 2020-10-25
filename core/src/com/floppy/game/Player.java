package com.floppy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.List;

public class Player extends Hitbox {
    private float yVelocity = 0f;
    private final float gravity;
    private final float flapVelocity;
    private final float maxNegativeYVelocity;
    private float flapAnimationDelay = 0.1f;
    private boolean isAlive = true;
    private final Sound flap;

    private boolean hasStarted = false;
    private boolean hasFlapped = false;

    private List<Sprite> playerSprites;
    private int spriteIndex = 0;

    public Player(float x, float y, float width, float height) {
        super(x, y, width, height);

        gravity = 1400f;
        flapVelocity = 550f;
        maxNegativeYVelocity = -1600f;

        hitBox.width = width * 0.57f;
        hitBox.height = height * 0.57f;

        instantiatePlayerSprites();

        flap = Gdx.audio.newSound(Gdx.files.internal("Flap.wav"));
    }

    public void update(float dt) {
        flap();
        animatePlayer(dt);
        if(hasStarted) {
            applyGravity(dt);
        }
        playerSprites.get(spriteIndex).setPosition(x, y);
        if(y >= 730f || y <= -60f) {
            isAlive = false;
        }
        super.update(dt);
    }

    public void render(SpriteBatch batch) {
        playerSprites.get(spriteIndex).draw(batch);
    }

    private void applyGravity(float dt) {
        yVelocity -= gravity * dt;
        if(yVelocity < maxNegativeYVelocity) {
            yVelocity = maxNegativeYVelocity;
        }

        y += yVelocity * dt;
    }

    private void flap() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if(!hasStarted) {
                hasStarted = true;
            }
            if(!hasFlapped) {
                yVelocity = flapVelocity;
                flap.play();
                hasFlapped = true;
            }

        } else {
            hasFlapped = false;
        }
    }

    private void animatePlayer(float dt) {
        if(flapAnimationDelay > 0f) {
            flapAnimationDelay -= dt;
        } else {
            spriteIndex--;
            if(spriteIndex < 0) {
                spriteIndex = 2;
            }
            flapAnimationDelay = 0.1f;
        }
    }

    private void instantiatePlayerSprites() {
        float sizeModifier = 0.75f;

        Sprite playerSprite1 = new Sprite(new Texture("sprites/Bird1.png"));
        playerSprite1.setSize(width * sizeModifier, height * sizeModifier);
        Sprite playerSprite2 = new Sprite(new Texture("sprites/bird2.png"));
        playerSprite2.setSize(width * sizeModifier, height * sizeModifier);
        Sprite playerSprite3 = new Sprite(new Texture("sprites/Bird3.png"));
        playerSprite3.setSize(width * sizeModifier, height * sizeModifier);

        playerSprites = new ArrayList<>();
        playerSprites.add(playerSprite1);
        playerSprites.add(playerSprite2);
        playerSprites.add(playerSprite3);
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
