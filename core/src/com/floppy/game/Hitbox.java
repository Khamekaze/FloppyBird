package com.floppy.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Hitbox {
    Rectangle hitBox;

    protected float x, y, width, height;

    public Hitbox(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.x -= (width / 2);
        this.y -= (height / 2);
        hitBox = new Rectangle(x, y, width, height);
    }

    public void update(float dt) {
        hitBox.x = x;
        hitBox.y = y;
    }

    public Rectangle getHitbox() {
        return hitBox;
    }

    public boolean checkPlayerCollision(Rectangle playerHitbox) {
        if(hitBox.overlaps(playerHitbox)) {
            return true;
        }
        return false;
    }

    public void setPosition(Vector2 pos) {
        x = pos.x;
        y = pos.y;
    }
}
