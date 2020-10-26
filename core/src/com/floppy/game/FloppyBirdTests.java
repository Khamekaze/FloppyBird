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
    HighScore hs = new HighScore("testHighScore.txt");
    hs.writeNewHighscore(1000);
    assertEquals(1000, hs.readHighScoreFile());
    hs.writeNewHighscore(999);
    assertEquals(1000, hs.readHighScoreFile());
    hs.writeNewHighscore(1001);
    assertEquals(1001, hs.readHighScoreFile());
    }

}

