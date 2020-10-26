package com.floppy.game;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;


public class HighScore {
    private int highScore;
    private final String highscoreFile;

    public HighScore(String highscoreFile){
        this.highscoreFile = highscoreFile;
        highScore = readHighScoreFile();
    }

    public void writeNewHighscore(int numberOfObstaclesPassed) {
        if(numberOfObstaclesPassed > highScore){
            try {
                FileWriter fw = new FileWriter("./" + highscoreFile + ".txt");
                fw.write(String.valueOf(numberOfObstaclesPassed));
                fw.close();
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Could not save High Score!");
            }
        }
    }

    public int readHighScoreFile () {
        File file = new File("./" + highscoreFile + ".txt");
        try{
            if(file.exists()) {
                Scanner rf = new Scanner(file);
                if(rf.hasNextInt()) {
                    highScore = rf.nextInt();
                }
                rf.close();
            } else {
                file.createNewFile();
                highScore = 0;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return highScore;
    }
    public int getHighScore(){return highScore;}

}
