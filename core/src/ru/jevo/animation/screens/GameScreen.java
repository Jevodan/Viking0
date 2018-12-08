package ru.jevo.animation.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.BasicScreen;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.pools.ships.MilitarySmallPool;
import ru.jevo.animation.sprites.ships.MilitarySmall;
import ru.jevo.animation.sprites.ships.Viking;
import ru.jevo.animation.sprites.Background;
import ru.jevo.animation.sprites.Star;


public class GameScreen extends BasicScreen {

    protected float animateTimer = 0;

    Viking mViking;
    MilitarySmall[] mMilitarySmalls;
    TextureAtlas mainTextureAtlas;
    private BulletPool bulletPool;
    private MilitarySmallPool mMilitarySmallPool;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("images/bg.png");
        menuTextureAtlas = new TextureAtlas("atlas/menuAtlas.tpack");
        mainTextureAtlas = new TextureAtlas("atlas/mainAtlas.tpack");
        mBackground = new Background(new TextureRegion(bgTexture));
        rainMusic.setLooping(true); // повторение музыки
        rainMusic.play();
        mStar = new Star[STAR_COUNT];
        mMilitarySmalls = new MilitarySmall[5];
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i] = new Star(menuTextureAtlas);
        bulletPool = new BulletPool();
        mMilitarySmallPool = new MilitarySmallPool();
        mViking = new Viking(mainTextureAtlas, bulletPool);
        /*
        for (int i = 0; i < ENEMY_COUNT; i++) {
            mMilitarySmalls[i] = new MilitarySmall(enemyTextureAtlas);
        }


        for (int i = 0; i < ENEMY_COUNT; i++) {
            mMilitarySmalls[i].setFireBehavior(new SimpleBlaster());
        }
        */
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw(delta);
    }

    private void checkCollisions() {
    }

    public void update(float delta) {
        mViking.update(delta);
        bulletPool.updateActiveSprites(delta);
        mMilitarySmallPool.updateActiveSprites(delta);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].update(delta);

        animateTimer += delta;
        if (animateTimer > 8) {
            animateTimer = 0f;
            System.out.println("МИЛИТАРИСМОЛЛ ПОШЕЛ");
            MilitarySmall mMilitarySmallShip = mMilitarySmallPool.obtain();
            mMilitarySmallShip.speed.set(0, -0.8f);
            mMilitarySmallShip.pos.set(MathUtils.random(serviceRect.getLeft(), serviceRect.getRight()), serviceRect.getTop());
            mMilitarySmallShip.set(new Vector2(0, -1.5f), 1f, serviceRect);
        }
    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        mMilitarySmallPool.freeAllDestroyedActiveSprites();
    }

    public void draw(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        mBackground.draw(batch);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].draw(batch);
        mViking.draw(batch);
        bulletPool.drawActiveSprites(batch);
        mMilitarySmallPool.drawActiveSprites(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        mBackground.resize(serviceRect);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].resize(serviceRect);
        mViking.resize(serviceRect);
    }

    @Override
    public boolean keyDown(int keycode) {
        mViking.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        mViking.keyUp(keycode);
        return super.keyUp(keycode);
    }

    @Override
    public boolean keyTyped(char character) {
        return super.keyTyped(character);
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        super.touchDown(screenX, screenY, pointer, button);
        mViking.touchDown(touch, pointer);
        return false;
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

    @Override
    public void dispose() {
        bgTexture.dispose();
        menuTextureAtlas.dispose();
        mainTextureAtlas.dispose();
        rainMusic.dispose();
        rainMusic.dispose();
        bulletPool.dispose();
        mMilitarySmallPool.dispose();
        super.dispose();
    }

}
