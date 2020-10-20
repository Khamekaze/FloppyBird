package com.floppy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.io.File;
import java.io.FileReader;
import java.util.Scanner;


public class Menu {
    private static int menuSelection;
    private static final int STATE_MAIN_MENU = 0, STATE_HIGHSCORE = 1, STATE_INFO = 2;

    private float x, y;
    BitmapFont font;
    private int currentState = 0;

    public Menu(float x, float y) {
        this.x = x;
        this.y = y;
        font = new BitmapFont();

    }

    public void update(float dt) {
        menuInput();
    }

    public void render(SpriteBatch batch) {
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
        }
    }

    private void menuInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            currentState = 0;
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.H)) {
            if(currentState == STATE_MAIN_MENU) {
                currentState = STATE_HIGHSCORE;
            }
        } else if(Gdx.input.isKeyJustPressed(Input.Keys.I)) {
            if(currentState == STATE_MAIN_MENU) {
                currentState = STATE_INFO;
            }
        }
    }

    private void renderMainMenu(SpriteBatch batch) {
        font.draw(batch, "Welcome to Floppy Bird!", x, y);
        font.draw(batch, "SPACE to start", x, y - 25f);
        font.draw(batch, "Press H to see highscores", x,  y - 50f);
        font.draw(batch, "Press I for info", x, y - 75f);
    }

    private void renderHighscoreMenu(SpriteBatch batch) {
        font.draw(batch, "HIGHSCORES\n" +
                "\n" +
                "Press ESC to return to main menu.", x, y);
    }

    private void renderInfoMenu(SpriteBatch batch) {
        font.draw(batch, "Welcome to Flappy Bird KYH edition!\n" +
                "To play simply press SPACE in the menu selection.\n" +
                "Press SPACE to jump and avoid the obstacles.\n" +
                "\n" +
                "Press ESC to return to main menu.", x, y);
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

