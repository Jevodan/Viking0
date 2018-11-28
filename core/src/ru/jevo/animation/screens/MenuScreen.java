package ru.jevo.animation.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.GameViking;
import ru.jevo.animation.basic.BasicScreen;
import ru.jevo.animation.service.Anima;
import ru.jevo.animation.service.LineAlg;

/**
 * Created by Alexander on 25.11.2018.
 */
public class MenuScreen extends BasicScreen {

    final GameViking game;
    final private Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/main.mp3"));

    public MenuScreen(final GameViking game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();

        Anima.setTexture();
        rainMusic.setLooping(true); // повторение музыки
        rainMusic.play();
        System.out.println("SHOW");
    }

    @Override
    public void render(float delta) {
       // System.out.println("RENDER");
        super.render(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(Anima.currentFrame, LineAlg.position.x, LineAlg.position.y);
        batch.end();

        LineAlg.setNewPosition();

        if (Gdx.input.isTouched()) {
           Anima.setCurrentFrame(LineAlg.getAngle());
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        System.out.println("DISPOSE");
    }

    @Override
    public void hide() {
        System.out.println("HIDE2");
        dispose();
    }

}
