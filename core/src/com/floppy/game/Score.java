package com.floppy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Responsible for manipulating and rendering the players score
 * as well as saving any new highscore to a local file.
 */
public class Score {
    private int numberOfObstaclesPassed = 0;
    private boolean showScore = false;
    private final ObstacleManager obstacleManager;
    private final BitmapFont font;
    private final Sprite highscoreText;
    private final Sound passTube;
    private String highscoreFile;
    HighScore hs;

    public Score(ObstacleManager obstacles, String highscoreFile) {
        hs = new HighScore(highscoreFile);
        this.highscoreFile = highscoreFile;
        obstacleManager = obstacles;
        font = new BitmapFont();
        highscoreText = new Sprite(new Texture("NewHighScore.png"));
        highscoreText.setPosition((float)Gdx.graphics.getWidth() / 2 - (float)highscoreText.getTexture().getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 2 - (float)highscoreText.getTexture().getHeight() / 2);
        passTube = Gdx.audio.newSound(Gdx.files.internal("PassTube2.wav"));
    }

    /**
     * Iterates through the obstacles in the scene.
     * If any obstacle is far enough to the left a point will be added
     * to the counter.
     */
    public void update() {
        for(Obstacle o : obstacleManager.getObstacles()) {
            if(o.getXPosition() <= 300f && !o.isHasGivenScore()) {
                addPoint();
                o.giveScore();
                passTube.play();
            }
        }
    }

    public void render(SpriteBatch batch) {
        if(showScore) {
            if(numberOfObstaclesPassed > hs.getHighScore()) {
                highscoreText.draw(batch);

            }
            font.draw(batch, String.valueOf(numberOfObstaclesPassed),
                    highscoreText.getX() + (float)highscoreText.getTexture().getWidth() / 2,
                    highscoreText.getY() + (float)highscoreText.getTexture().getHeight() / 2 - 15f);
        } else {
            font.draw(batch, String.valueOf(numberOfObstaclesPassed), 540f, 700f);
        }
    }

    public void setShowScore() {
        if(!showScore) {
            showScore = true;
           hs.writeNewHighscore(numberOfObstaclesPassed);
        }
    }




    public void resetScore (){numberOfObstaclesPassed = 0; }
    public void addPoint() {numberOfObstaclesPassed++;}
}