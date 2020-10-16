package com.floppy.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class ObstacleManager {
    ArrayList<Obstacle> obstacles = new ArrayList<>();
    boolean removed = false;
    private float startOffset = 800f;

    public ObstacleManager (){
        obstacles.add(new Obstacle(startOffset + 600f,0 ,0 , 0));
        obstacles.add(new Obstacle(startOffset + 1000f,0 ,0 , 0));
        obstacles.add(new Obstacle(startOffset + 1400f,0 ,0 , 0));
        obstacles.add(new Obstacle(startOffset + 1800f,0 ,0 , 0));
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

    public void removeObstacles(Obstacle obstacle){
        if(obstacle.getXPosition() <= -200f && !removed){
            obstacles.remove(obstacle);
            obstacles.add(new Obstacle(1400, 0, 0,0 ));
            removed = true;
        }
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}


