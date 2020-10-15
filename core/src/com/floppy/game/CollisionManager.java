package com.floppy.game;

import java.util.ArrayList;
import java.util.List;

public class CollisionManager {

    Player player;
    List<Hitbox> obstacleHitboxes;

    public CollisionManager(Player player, ArrayList<Hitbox> obstacleHitboxes) {
        this.player = player;
        this.obstacleHitboxes = obstacleHitboxes;
    }

    public void update(float dt) {
        checkCollisions();
    }

    void checkCollisions() {
        for(Hitbox h : obstacleHitboxes) {
            if(h.checkPlayerCollision(player.getHitbox())) {
                System.out.println("OBSTACLE HIT");
            }
        }
    }
}
