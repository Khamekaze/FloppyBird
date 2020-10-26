package com.floppy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * The main game class, instantiated through the DesktopLauncher class.
 *
 * Responsible for instantiating, updating and rendering the game scene and any
 * objects withing it.
 *
 * Extends the ApplicationAdapter class from the GDX library
 *
 */
public class Main extends ApplicationAdapter {

    SpriteBatch batch;
    CollisionManager collisionManager;
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
        player = new Player((Gdx.graphics.getWidth() / 2 - 150f), (Gdx.graphics.getHeight() / 2), 131, 93f);
        obstacleManager = new ObstacleManager(4);
        collisionManager = new CollisionManager(player, obstacleManager.getObstacles());
        menu = new Menu();
        scoreManager = new Score(obstacleManager, "highscore");
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        update(deltaTime);

        batch.begin();
        batch.draw(background, 0,0, 1281, 720);
        player.render(batch);
        obstacleManager.render(batch);
        menu.render(batch);
        scoreManager.render(batch);
        batch.end();
    }

    private void update(float deltaTime) {
        if(player.isAlive()) {
            player.update(deltaTime);
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

        collisionManager.update();
        menu.update();
        scoreManager.update();

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !startGame) {
            startGame = true;
        }
    }

    @Override
    public void dispose () {
        batch.dispose();
    }

    /**
     * Discards and recreates the scene objects needed to restart the game
     *
     */
    private void restartGame() {
        player = null;
        player = new Player((Gdx.graphics.getWidth() / 2) - 150f, (Gdx.graphics.getHeight() / 2), 131, 93f);
        obstacleManager = null;
        obstacleManager = new ObstacleManager(4);
        collisionManager = null;
        collisionManager = new CollisionManager(player, obstacleManager.getObstacles());
        menu.hideMenu();
        menu.playRestartSound();
        scoreManager = null;
        scoreManager = new Score(obstacleManager, "highscore");
        scoreManager.resetScore();
    }
}
