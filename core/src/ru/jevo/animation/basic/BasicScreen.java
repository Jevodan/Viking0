package ru.jevo.animation.basic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Alexander on 25.11.2018.
 */
public class BasicScreen implements Screen {

    protected SpriteBatch batch;
    final protected OrthographicCamera mCamera = new OrthographicCamera(); //показывает определенную область мира игры.


    @Override
    public void show() {
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        System.out.println("HIDE");
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
