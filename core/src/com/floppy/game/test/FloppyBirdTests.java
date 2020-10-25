package com.floppy.game.test;

import com.floppy.game.ObstacleHitbox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FloppyBirdTests {

    @Test
    void testHitboxCollision() {
        ObstacleHitbox hitBox1 = new ObstacleHitbox(0f, 0f, 10f, 10f);
        ObstacleHitbox hitBox2 = new ObstacleHitbox(0f, 0f, 10f, 10f);
        assertTrue(hitBox1.checkCollision(hitBox2.getHitbox()));
    }

}
