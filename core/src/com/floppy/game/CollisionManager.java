package com.floppy.game;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for checking for any collisions in the game scene
 *
 */
public class CollisionManager {

    Player player;
    List<Obstacle> obstacleHitboxes;

    public CollisionManager(Player player, ArrayList<Obstacle> obstacleHitboxes) {
        this.player = player;
        this.obstacleHitboxes = obstacleHitboxes;
    }

    public void update() {
        checkCollisions();
    }

    void checkCollisions() {
        for(Obstacle o : obstacleHitboxes) {
            if(o.checkPlayerCollision(player.getHitbox())) {
                player.setAlive(false);
            }
        }
    }
}
