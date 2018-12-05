package ru.jevo.animation.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.jevo.animation.GameViking;
import ru.jevo.animation.basic.BasicScreen;
import ru.jevo.animation.ships.MilitarySmall;
import ru.jevo.animation.ships.Viking;
import ru.jevo.animation.sprites.Background;
import ru.jevo.animation.sprites.Star;
import ru.jevo.animation.weapon.SimpleBlaster;

import static com.badlogic.gdx.Input.Keys.LEFT;



public class GameScreen extends BasicScreen {

    public static final int ENEMY_COUNT = 5;

    Viking mViking;
    MilitarySmall[] mMilitarySmalls;
    TextureAtlas mainTextureAtlas;
    TextureAtlas enemyTextureAtlas;

    public GameScreen(GameViking game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("images/bg.png");
        enemyTextureAtlas = new TextureAtlas("atlas/enemy1/enemy_pack1.atlas");
        menuTextureAtlas = new TextureAtlas("atlas/menuAtlas.tpack");
        mainTextureAtlas = new TextureAtlas("atlas/mainAtlas.tpack");
        mBackground = new Background(new TextureRegion(bgTexture));
        rainMusic.setLooping(true); // повторение музыки
        rainMusic.play();
        mStar = new Star[STAR_COUNT];
        mMilitarySmalls = new MilitarySmall[5];
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i] = new Star(menuTextureAtlas);
        mViking = new Viking(mainTextureAtlas);
        for (int i = 0; i < ENEMY_COUNT; i++) {
            mMilitarySmalls[i] = new MilitarySmall(enemyTextureAtlas);
        }
        for (int i = 0; i < ENEMY_COUNT; i++) {
            mMilitarySmalls[i].setFireBehavior(new SimpleBlaster());
        }
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
        for (int i = 0; i < ENEMY_COUNT; i++){
            mMilitarySmalls[i].update(delta);
            mMilitarySmalls[i].getFireBehavior().update(delta);
        }

    }

    public void draw(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        mBackground.draw(batch);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].draw(batch);
        mViking.draw(batch);
        for (int i = 0; i < ENEMY_COUNT; i++) {
            mMilitarySmalls[i].draw(batch);
            mMilitarySmalls[i].getFireBehavior().fire(batch);
        }

        batch.end();
        drive(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        mBackground.resize(serviceRect);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].resize(serviceRect);
        mViking.resize(serviceRect);
        for (int i = 0; i < ENEMY_COUNT; i++) {
            mMilitarySmalls[i].resize(serviceRect);
            mMilitarySmalls[i].getFireBehavior().resize(mMilitarySmalls[i]);
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == LEFT)
            System.out.println("ВЛЕВО");
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return super.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return super.keyTyped(character);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return super.touchDragged(screenX, screenY, pointer);
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return super.mouseMoved(screenX, screenY);
    }

    @Override
    public boolean scrolled(int amount) {
        return super.scrolled(amount);
    }

    private void drive(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            mViking.speed.set(-1, 0);
            mViking.update(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            mViking.speed.set(1, 0);
            mViking.update(delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            mViking.speed.set(0, 1);
            mViking.update(delta);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            mViking.speed.set(0, -1);
            mViking.update(delta);
        }
    }
}
