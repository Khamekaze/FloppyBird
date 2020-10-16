package com.floppy.game;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {

    Player player;
    List<Obstacle> obstacleHitboxes;

    public CollisionManager(Player player, ArrayList<Obstacle> obstacleHitboxes) {
        this.player = player;
        this.obstacleHitboxes = obstacleHitboxes;
    }

    public void update(float dt) {
        checkCollisions();
    }

    void checkCollisions() {
        for(Obstacle o : obstacleHitboxes) {
            if(o.checkPlayerCollision(player.getHitbox())) {
                System.out.println("OBSTACLE HIT");
            }
        }
    }
}
