package com.floppy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Score {
    private static int numberOfObstaclesPassed = 0;
    private static int highScore;
    private int newHighscore = 0;
    private static String userAnswer;
    private static int status;
    private boolean showScore = false;
    ObstacleManager obstacleManager;
    BitmapFont font;
    private Sprite highscoreText;

    public Score(ObstacleManager obstacles) {
        obstacleManager = obstacles;
        font = new BitmapFont();
        highscoreText = new Sprite(new Texture("NewHighScore.png"));
        highscoreText.setPosition(Gdx.graphics.getWidth() / 2 - highscoreText.getTexture().getWidth() / 2,
                Gdx.graphics.getHeight() / 2 - highscoreText.getTexture().getHeight() / 2);
        readHighScoreFile();
    }

    public void update(float dt) {
        for(Obstacle o : obstacleManager.getObstacles()) {
            if(o.getXPosition() <= 400f && !o.isHasGivenScore()) {
                points();
                o.giveScore();
                System.out.println(numberOfObstaclesPassed);
            }
        }
    }

    public void render(SpriteBatch batch) {
        if(showScore) {
            font.draw(batch, String.valueOf(numberOfObstaclesPassed), 300f, 600f);
            if(numberOfObstaclesPassed > highScore) {
                highscoreText.draw(batch);
                font.draw(batch, String.valueOf(numberOfObstaclesPassed),
                        highscoreText.getX() + highscoreText.getTexture().getWidth() / 2,
                        highscoreText.getY() + highscoreText.getTexture().getHeight() / 2 - 15f);
            }
        } else {
            font.draw(batch, String.valueOf(numberOfObstaclesPassed), 540f, 700f);
        }
    }

    public void setShowScore() {
        if(!showScore) {
            showScore = true;
            compareScore();
        }
    }

    /*
    public static void main(String[] args) {
        readHighScoreFile();
        Scanner sc = new Scanner(System.in);
        status = sc.nextInt();
       while(status == 1) {
           while(status == 1){
            //points();
            //System.out.println(getNumberOfObstaclesPassed());
            status = sc.nextInt();
        }
        higherPoints();
        System.out.println("highest score is: " + getHighScore());
        System.out.println("Would you like to play again? yes(y) or no(n)?: ");
        String yes = "y";
        String no = "n";
        userAnswer = sc.next();
        if(userAnswer.equals(yes)){
            playAgain();
            resetScore();
        }else if(userAnswer.equals(no)){
            status = 2;
        }
       }

    }
    */

    void compareScore() {
        if(numberOfObstaclesPassed > highScore) {
            newHighscore = numberOfObstaclesPassed;
        } else {
            newHighscore = highScore;
        }

        writeNewHighscore();
    }

    public void writeNewHighscore() {
        if(numberOfObstaclesPassed > highScore){
            try {
                FileWriter fw = new FileWriter("./highscore.txt");
                fw.write(String.valueOf(numberOfObstaclesPassed));
                fw.close();
            }catch (Exception e){
                System.out.println("Could not save High Score!");
            }
        }
    }
    public void readHighScoreFile () {
        File file = new File("./highscore.txt");
        try{
            Scanner rf = new Scanner(file);
            highScore = Integer.valueOf(rf.next());
            rf.close();
        }catch (Exception e){

        }
    }
    public void resetScore (){numberOfObstaclesPassed = 0; }
    public static void playAgain() { status = 1; }
    public void points() {numberOfObstaclesPassed++;}
    public int getNumberOfObstaclesPassed() {return numberOfObstaclesPassed;}
    public static int getHighScore() {return highScore;}
}