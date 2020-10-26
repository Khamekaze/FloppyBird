package com.floppy.game;

import com.floppy.game.ObstacleHitbox;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FloppyBirdTests {
    private final ByteArrayOutputStream errorContent = new ByteArrayOutputStream();

    @Test
    void testHitboxCollision() {
        ObstacleHitbox hitBox1 = new ObstacleHitbox(0f, 0f, 10f, 10f);
        ObstacleHitbox hitBox2 = new ObstacleHitbox(0f, 0f, 10f, 10f);
        assertTrue(hitBox1.checkCollision(hitBox2.getHitbox()));
    }

    @Test
    @DisplayName("Could read high score")
    void testIfHighScoreCanRead(){
    System.setErr(new PrintStream(errorContent));
    File file = new File("../highscore.txt");
        assertTrue(file.canRead());
        System.err.println("Could not read File");
    }

    @Test
    @DisplayName("Testing if high score makes new high score")
    void testIfSetNewHighScore(){
    Score score = new Score(new ObstacleManager(0), "highscore");
    //assertEquals(20, score.)
        for(int i = 0; i<100; i++){
            score.addPoint();
        }
        score.writeNewHighscore();
        File file = new File ("./highscore.txt");
        Scanner sc;
        int noll = 0;
        try {
            sc = new Scanner (file);
            noll = sc.nextInt();
        }   catch (IOException e){
            System.err.println("Could not read file");
        }
        assertEquals(100, noll);
    }

}

