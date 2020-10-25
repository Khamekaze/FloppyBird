package com.floppy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.util.Scanner;

/**
 * Responsible for displaying different menus within the game
 */
public class Menu {
    private static final int STATE_MAIN_MENU = 0, STATE_HIGHSCORE = 1, STATE_INFO = 2, STATE_GAME_OVER = 3;

    private final BitmapFont font;
    private int currentState = 0;
    private boolean showMenu = true;
    private int highScore = 0;
    private final Sprite gameOverUI, infoUI, highscoreUI, menuUI;
    private final Sound select, death;

    public Menu() {
        readHighscore();

        font = new BitmapFont();
        gameOverUI = new Sprite(new Texture("MenuFlip.png"));
        infoUI = new Sprite(new Texture("ifPlayerPressInfo.png"));
        highscoreUI = new Sprite(new Texture("ifHighScorePressed.png"));
        menuUI = new Sprite(new Texture("menu2fixed.png"));

        setMenuPositions();

        select = Gdx.audio.newSound(Gdx.files.internal("Select.wav"));
        death = Gdx.audio.newSound(Gdx.files.internal("Death.wav"));
    }

    public void update() {
        menuInput();
    }

    public void render(SpriteBatch batch) {
        if(showMenu) {
            switch(currentState) {
                case 0:
                    renderMainMenu(batch);
                    break;
                case 1:
                    renderHighscoreMenu(batch);
                    break;
                case 2:
                    renderInfoMenu(batch);
                    break;
                case 3:
                    renderGameOverMenu(batch);
            }
        }
    }

    private void readHighscore() {
        File file = new File("./highscore.txt");
        try{
            Scanner rf = new Scanner(file);
            highScore = rf.nextInt();
            rf.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void menuInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            currentState = 0;
            select.play();
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            if(currentState == STATE_MAIN_MENU) {
                currentState = STATE_HIGHSCORE;
            }
            select.play();
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            if(currentState == STATE_MAIN_MENU) {
                currentState = STATE_INFO;
            }
            select.play();
        }
    }

    private void renderMainMenu(SpriteBatch batch) {
        menuUI.draw(batch);
    }

    private void renderHighscoreMenu(SpriteBatch batch) {
        highscoreUI.draw(batch);
        font.draw(batch, String.valueOf(highScore), highscoreUI.getX() + (float)highscoreUI.getTexture().getWidth() / 2,
                highscoreUI.getY() + (float)highscoreUI.getTexture().getHeight() / 2);
    }

    private void renderInfoMenu(SpriteBatch batch) {
        infoUI.draw(batch);
    }

    private void renderGameOverMenu(SpriteBatch batch) {
        gameOverUI.draw(batch);
    }

    private void setMenuPositions() {
        menuUI.setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 2 - (float)menuUI.getTexture().getHeight() / 2);
        infoUI.setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 2 - (float)infoUI.getTexture().getHeight() / 2);
        highscoreUI.setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 2 - (float)highscoreUI.getTexture().getHeight() / 2);
        gameOverUI.setPosition((float)Gdx.graphics.getWidth() / 2 - (float)gameOverUI.getTexture().getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 2 - (float)gameOverUI.getTexture().getHeight() / 2);
    }

    public void setStateGameOver() {
        currentState = STATE_GAME_OVER;
        if(!showMenu) {
            showMenu = true;
            death.play();
        }
    }

    public void hideMenu() {
        showMenu = false;
    }

    public void playRestartSound() {
        select.play();
    }
}

