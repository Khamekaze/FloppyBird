package com.floppy.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * ObstacleManager updates, renders and removes/adds obstacles to the scene as needed
 *
 */
public class ObstacleManager {
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private boolean removed = false;

    public ObstacleManager(int numberOfObstacles){
        float startOffset = 800f;
        for(int i = 0; i < numberOfObstacles; i++) {
            obstacles.add(new Obstacle(startOffset + 600f + (400f * i)));
        }
    }

    public void update(float dt) {
        removed = false;
        for(Obstacle o: obstacles){
            o.update(dt);
        }

        for(int i = obstacles.size()-1; i >=0; --i){
            removeObstacles(obstacles.get(i));
        }
    }

    public void render(SpriteBatch batch){
        for(Obstacle o: obstacles){
            o.render(batch);
        }
    }

    /**
     * Checks the x-position of the obstacle and removes it from the list
     * if it is far enough to the left.
     *
     * Once an obstacle is removed a new obstacle is added to the list
     *
     * @param obstacle - the obstacle to be evaluated
     */
    public void removeObstacles(Obstacle obstacle){
        if(obstacle.getXPosition() <= -200f && !removed){
            obstacles.remove(obstacle);
            obstacles.add(new Obstacle(1400));
            removed = true;
        }
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}


