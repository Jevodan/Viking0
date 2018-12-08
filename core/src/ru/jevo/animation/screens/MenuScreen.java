package ru.jevo.animation.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.GameViking;
import ru.jevo.animation.basic.BasicScreen;
import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.service.Anima;
import ru.jevo.animation.service.LineAlg;
import ru.jevo.animation.sprites.Background;
import ru.jevo.animation.sprites.ExitButton;
import ru.jevo.animation.sprites.Star;
import ru.jevo.animation.sprites.StartButton;

/**
 * Created by Alexander on 25.11.2018.
 */
public class MenuScreen extends BasicScreen {

    private Texture mTitle;
    private StartButton mStartButton;
    private ExitButton mExitButton;

    public MenuScreen(GameViking game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("images/bg.png");
        menuTextureAtlas = new TextureAtlas("atlas/menuAtlas.tpack");
        mBackground = new Background(new TextureRegion(bgTexture));
        rainMusic.setLooping(true); // повторение музыки
        rainMusic.play();
        mStar = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i] = new Star(menuTextureAtlas);
        mStartButton = new StartButton(menuTextureAtlas, game);
        mExitButton = new ExitButton(menuTextureAtlas);

        //  Anima.setTexture(); заполняем массив текстурами спрайта моего крейсера
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        mBackground.resize(serviceRect);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].resize(serviceRect);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw(delta);
    }

    public void update(float delta) {
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].update(delta);
    }

    public void draw(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        mBackground.draw(batch);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].draw(batch);
        mStartButton.draw(batch);
        mExitButton.draw(batch);
        batch.end();
    }


    @Override
    public void hide() {
        System.out.println("HIDE2");
        dispose();
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        menuTextureAtlas.dispose();
        rainMusic.dispose();
//        mTitle.dispose();
        super.dispose();
        System.out.println("DISPOSE");
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mStartButton.touchDown(touch, pointer);
        mExitButton.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mStartButton.touchUp(touch, pointer);
        mExitButton.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean scrolled(int amount) {
        return super.scrolled(amount);
    }

}
