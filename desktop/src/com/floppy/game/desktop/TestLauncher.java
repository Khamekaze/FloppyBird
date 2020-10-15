package com.floppy.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.floppy.game.PlayerTestMain;

public class TestLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 1080;
        config.height = 720;

        new LwjglApplication(new PlayerTestMain(), config);
    }
}
