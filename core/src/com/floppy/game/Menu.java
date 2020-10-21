package com.floppy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Menu {
    private static int menuSelection;
    private static final int STATE_MAIN_MENU = 0, STATE_HIGHSCORE = 1, STATE_INFO = 2, STATE_GAME_OVER = 3;

    private float x, y;
    BitmapFont font;
    private int currentState = 0;
    boolean showMenu = true;
    private int highScore = 0;
    private Sprite gameOverUI, infoUI, highscoreUI, menuUI;
    private Sound select;
    private Sound death;

    public Menu(float x, float y) {
        this.x = x;
        this.y = y;
        font = new BitmapFont();
        gameOverUI = new Sprite(new Texture("MenuFlip.png"));
        infoUI = new Sprite(new Texture("ifPlayerPressInfo.png"));
        highscoreUI = new Sprite(new Texture("ifHighScorePressed.png"));
        menuUI = new Sprite(new Texture("menu2fixed.png"));
        menuUI.setPosition(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - menuUI.getTexture().getHeight() / 2);
        infoUI.setPosition(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - infoUI.getTexture().getHeight() / 2);
        highscoreUI.setPosition(Gdx.graphics.getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - highscoreUI.getTexture().getHeight() / 2);
        gameOverUI.setPosition(Gdx.graphics.getWidth() / 2 - gameOverUI.getTexture().getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - gameOverUI.getTexture().getHeight() / 2);
        readHighscore();
        select = Gdx.audio.newSound(Gdx.files.internal("Select.wav"));
        death = Gdx.audio.newSound(Gdx.files.internal("Death.wav"));
    }

    public void update(float dt) {
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
            highScore = Integer.valueOf(rf.next());
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
        font.draw(batch, String.valueOf(highScore), highscoreUI.getX() + highscoreUI.getTexture().getWidth() / 2,
                highscoreUI.getY() + highscoreUI.getTexture().getHeight() / 2);
    }

    private void renderInfoMenu(SpriteBatch batch) {
        infoUI.draw(batch);
    }

    private void renderGameOverMenu(SpriteBatch batch) {
        gameOverUI.draw(batch);
    }

    public void setStateGameOver() {
        currentState = STATE_GAME_OVER;
        if(showMenu == false) {
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


    /*
    public static void main(String[] args) {
        System.out.println("Press '0' to continue");
        Scanner sc = new Scanner(System.in);
        menuSelection = sc.nextInt();
         while(menuSelection == 0 ) {
             while(menuSelection == 0) {
                 System.out.println("Welcome to Flappy bird!");
                 System.out.println("1. Play");
                 System.out.println("2. High Score");
                 System.out.println("3. Help");
                 menuSelection = sc.nextInt();
             }
             if (menuSelection == 1) {
                //starta spelet
            } else if (menuSelection == 2) {
                highScore();
                System.out.println(" ");
                System.out.println("Press '0' to return to main menu");
                menuSelection = sc.nextInt();
            } else if (menuSelection == 3) {
                System.out.println("Welcome to Flappy Bird KYH edition!");
                System.out.println("To play simply press 'start' in the menu selection. ");
                System.out.println("Press 'space' to jump.");
                System.out.println("Make sure to avid all obstacles.");
                System.out.println("Have fun!");
                System.out.println(" ");
                System.out.println("Press '0' to return to main menu");
                menuSelection = sc.nextInt();
            }
            //sc.close();

    }
    }

     */

    public static void highScore() {
        File file = new File("./highscore.txt");
        try{
            Scanner rf = new Scanner(file);
            System.out.println("High Score is: " + rf.nextLine());
            rf.close();
        }catch (Exception e){
            System.out.println("No high Score yet!");
        }
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }
}

