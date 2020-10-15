package com.floppy.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;


public class ObstacleManager {
    ArrayList<Obstacle> obstacles = new ArrayList<>();

    public ObstacleManager (){
        obstacles.add(new Obstacle(600f,0 ,0 , 0));
        obstacles.add(new Obstacle(1000f,0 ,0 , 0));
        obstacles.add(new Obstacle(1400f,0 ,0 , 0));
        obstacles.add(new Obstacle(1800f,0 ,0 , 0));
    }
    public void update(float dt) {
        for(int i = obstacles.size()-1; i >=0; --i){
            removeObstacles(obstacles.get(i));
        }
        for(Obstacle o: obstacles){
            o.update(dt);
        }

    }
    public void render(SpriteBatch batch){
        for(Obstacle o: obstacles){
            o.render(batch);
        }
    }
    public void removeObstacles(Obstacle obstacle){
        if(obstacle.x <= -200f){
            obstacles.remove(obstacle);
            obstacles.add(new Obstacle(1400, 0, 0,0 ));
        }
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }
}


