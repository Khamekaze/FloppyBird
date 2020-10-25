package com.floppy.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * The superclass for any object that needs collision detection
 *
 */
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
        hitBox.x = x + 8f;
        hitBox.y = y + 8f;
    }

    public Rectangle getHitbox() {
        return hitBox;
    }

    /**
     * Checks if an objects hitbox is intersecting another objects hitbox
     *
     * @param otherHitbox - The hitbox of another object
     * @return true if the two hitboxes are intersecting, otherwise false
     */
    public boolean checkCollision(Rectangle otherHitbox) {
        return hitBox.overlaps(otherHitbox);
    }

    public void setPosition(Vector2 pos) {
        x = pos.x;
        y = pos.y;
    }
}
