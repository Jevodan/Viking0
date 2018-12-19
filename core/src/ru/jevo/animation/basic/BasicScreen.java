package ru.jevo.animation.basic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.service.MatrixUtils;
import ru.jevo.animation.service.Rect;
import ru.jevo.animation.sprites.other.Background;
import ru.jevo.animation.sprites.other.Star;

import static ru.jevo.animation.basic.Const.MY_SCREEN_SIZE;
import static ru.jevo.animation.basic.Const.STAR_COUNT;
/**
 * Created by Alexander on 25.11.2018.
 */
public class BasicScreen implements Screen, InputProcessor {

    public TextureAtlas menuTextureAtlas = new TextureAtlas("atlas/menuAtlas.tpack");

    final protected Game game;
    final protected Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("sounds/main.mp3"));
    protected Texture bgTexture;
    protected Background mBackground;
    protected Star[] mStar;

    protected SpriteBatch batch;
    final protected OrthographicCamera mCamera = new OrthographicCamera(); //показывает определенную область мира игры.
    protected Rect screenRect;
    protected Rect serviceRect;
    protected Rect glRect;

    protected Matrix3 screenToService; //матрица преобразования обычного экрана в наш  с удобными координатами
    protected Matrix4 serviceToGl; // матрица преобразования нашего экрана в openGL

    final public static Vector2 touch = new Vector2();

    public BasicScreen(final Game game) {
        this.game = game;
        this.screenRect = new Rect();
        this.serviceRect = new Rect();
        this.glRect = new Rect(0, 0, 1f, 1f);
        this.screenToService = new Matrix3();
        this.serviceToGl = new Matrix4();
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        mStar = new Star[STAR_COUNT];
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i] = new Star(menuTextureAtlas);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {
        System.out.println("Начальные координаты: x: " + width + " y: " + height);
        screenRect.setSize(width, height);
        screenRect.setLeft(0);
        screenRect.setBottom(0);
        System.out.println("координаты screenRect: x: " + screenRect.pos.x + "y: " + screenRect.pos.y);
        final float aspect =  width / (float) height;
        serviceRect.setSize(MY_SCREEN_SIZE * aspect, MY_SCREEN_SIZE);
        System.out.println("координаты serviceRect: x: " + serviceRect.pos.x + "y: " + serviceRect.pos.y);
        System.out.println("размеры serviceRect: x: " + serviceRect.getWidth() + "y: " + serviceRect.getHeight());
        MatrixUtils.calcTransitionMatrix(serviceToGl, serviceRect, glRect);
        batch.setProjectionMatrix(serviceToGl); // устанавливаем новую матрицу, не единичную
        MatrixUtils.calcTransitionMatrix(screenToService, screenRect, serviceRect);
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

    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenRect.getHeight() - screenY).mul(screenToService);
        touchDown(touch, pointer);
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer)
    {
        System.out.println(touch);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, screenRect.getHeight() - screenY).mul(screenToService);
        touchUp(touch, pointer);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
