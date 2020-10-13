package com.floppy.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TestMain extends ApplicationAdapter {

    SpriteBatch batch;

    Player player;

    @Override
    public void create () {
        batch = new SpriteBatch();
        player = new Player((Gdx.graphics.getWidth() / 2) - 20, (Gdx.graphics.getHeight() / 2) - 20);
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float deltaTime = Gdx.graphics.getDeltaTime();

        //Update
        update(deltaTime);

        //Render
        batch.begin();
        player.render(batch);
        batch.end();
    }

    public void update(float deltaTime) {
        player.update(deltaTime);
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
