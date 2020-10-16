package com.floppy.game;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileWriter;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Score {
    private static int numberOfObstaclesPassed = 0;
    private static int highScore;
    private static String userAnswer;
    private static int status;

    public static void main(String[] args) {
        readHighScoreFile();
        Scanner sc = new Scanner(System.in);
        status = sc.nextInt();
       while(status == 1) {
           while(status == 1){
            points();
            System.out.println(getNumberOfObstaclesPassed());
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
    public static void higherPoints() {
        if(numberOfObstaclesPassed > highScore){
            highScore = numberOfObstaclesPassed;
            try {
                FileWriter fw = new FileWriter("./highscore.txt");
                fw.write(String.valueOf(highScore)); //kom ihåg
                fw.close();
            }catch (Exception e){
                System.out.println("Could not save High Score!");
            }
        }
    }
    public static void readHighScoreFile () {
        File file = new File("./highscore.txt");
        try{
            Scanner rf = new Scanner(file);
            highScore = Integer.valueOf(rf.next());
            rf.close();
        }catch (Exception e){

        }
    }
    public static void resetScore (){numberOfObstaclesPassed = 0; }
    public static void playAgain() { status = 1; }
    public static void points() {numberOfObstaclesPassed++;}
    public static int getNumberOfObstaclesPassed() {return numberOfObstaclesPassed;}
    public static int getHighScore() {return highScore;}
}