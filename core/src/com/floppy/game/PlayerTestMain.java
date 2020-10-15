package com.floppy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayerTestMain extends ApplicationAdapter {

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    CollisionManager collisionManager;

    Obstacle obstacle;
    ObstacleManager obstacleManager;
    Player player;

    @Override
    public void create () {
        batch = new SpriteBatch();
        player = new Player((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2), 100f, 100f);
        obstacleManager = new ObstacleManager();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager(player, obstacleManager.getObstacles());
        obstacle = new Obstacle(600f, 0f, 0f, 0f);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        //Update
        update(deltaTime);

        //Render hitboxes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0f, 0f, 0f, 1f);
        shapeRenderer.rect(player.getHitbox().getX(), player.getHitbox().getY(), player.getHitbox().getWidth(), player.getHitbox().getHeight());
        shapeRenderer.end();
        //Render sprites
        batch.begin();
        player.render(batch);
        obstacleManager.render(batch);
        //obstacle.render(batch);
        batch.end();
    }

    public void update(float deltaTime) {
        player.update(deltaTime);
        //obstacle.update(deltaTime);
        obstacleManager.update(deltaTime);
        collisionManager.update(deltaTime);
    }

    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
    }
}
