package ru.jevo.animation.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import ru.jevo.animation.AnimationTest;
import ru.jevo.animation.GameViking;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Drop";
        final float aspect = 3f / 4f;
        config.width = 350;
        config.height = (int) (config.width / aspect);
        config.resizable = false;
        config.y = 300;
        config.x = 700;

        new LwjglApplication(new GameViking(), config);
    }
}
