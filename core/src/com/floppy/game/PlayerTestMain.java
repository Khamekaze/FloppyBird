package com.floppy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class PlayerTestMain extends ApplicationAdapter {

    SpriteBatch batch;
    ShapeRenderer shapeRenderer;
    CollisionManager collisionManager;

    Obstacle obstacle;
    ObstacleManager obstacleManager;
    Player player;
    Score scoreManager;
    private Texture background;
    Menu menu;
    boolean startGame = false;

    @Override
    public void create () {
        background = new Texture("bg.png");
        batch = new SpriteBatch();
        player = new Player((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2), 131, 93f);
        obstacleManager = new ObstacleManager();
        shapeRenderer = new ShapeRenderer();
        collisionManager = new CollisionManager(player, obstacleManager.getObstacles());
        obstacle = new Obstacle(600f, 0f, 0f, 0f);
        menu = new Menu(100f, 400f);
        scoreManager = new Score(obstacleManager);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        //Update
        update(deltaTime);

        //Render sprites
        batch.begin();
        batch.draw(background, 0,0, 1281, 720);
        player.render(batch);
        obstacleManager.render(batch);
        //obstacle.render(batch);
        menu.render(batch);
        scoreManager.render(batch);
        batch.end();
        //Render hitboxes
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(0f, 0f, 0f, 1f);
        shapeRenderer.rect(player.getHitbox().getX(), player.getHitbox().getY(), player.getHitbox().getWidth(), player.getHitbox().getHeight());
        shapeRenderer.rect(obstacleManager.obstacles.get(1).getTopHitbox().x,
                obstacleManager.obstacles.get(1).getTopHitbox().y,
                obstacleManager.obstacles.get(1).getTopHitbox().width,
                obstacleManager.obstacles.get(1).getTopHitbox().height);
        shapeRenderer.end();

    }

    public void update(float deltaTime) {
        if(player.isAlive()) {
            player.update(deltaTime);
            //obstacle.update(deltaTime);
            if(startGame) {
                obstacleManager.update(deltaTime);
                menu.hideMenu();
            }
        } else {
            scoreManager.setShowScore();
            menu.setStateGameOver();
            if(Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                restartGame();
            }
        }
        collisionManager.update(deltaTime);

        menu.update(deltaTime);
        scoreManager.update(deltaTime);

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !startGame) {
            startGame = true;
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
        shapeRenderer.dispose();
    }

    void restartGame() {
        player = null;
        player = new Player((Gdx.graphics.getWidth() / 2), (Gdx.graphics.getHeight() / 2), 131, 93f);
        obstacleManager = null;
        obstacleManager = new ObstacleManager();
        collisionManager = null;
        collisionManager = new CollisionManager(player, obstacleManager.getObstacles());
        menu.hideMenu();
        scoreManager = null;
        scoreManager = new Score(obstacleManager);
        scoreManager.resetScore();
    }
}
