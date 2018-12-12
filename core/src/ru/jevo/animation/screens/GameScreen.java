package ru.jevo.animation.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import ru.jevo.animation.basic.BasicScreen;
import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.pools.ships.ColonyShipPool;
import ru.jevo.animation.pools.ships.MilitaryLargePool;
import ru.jevo.animation.pools.ships.MilitaryMediumPool;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.pools.ships.MilitarySmallPool;
import ru.jevo.animation.pools.weapons.SimpleBlasterPool;
import ru.jevo.animation.sprites.ships.ColonyShip;
import ru.jevo.animation.sprites.ships.MilitaryLarge;
import ru.jevo.animation.sprites.ships.MilitaryMedium;
import ru.jevo.animation.sprites.ships.MilitarySmall;
import ru.jevo.animation.sprites.ships.Viking;
import ru.jevo.animation.sprites.Background;
import ru.jevo.animation.sprites.Star;


public class GameScreen extends BasicScreen {

    private float[] animateTimer = {1f, 6f, 8f, 10f};
    private float animateTime = 0;

    Viking mViking;
    TextureAtlas mainTextureAtlas;
    private BulletPool bulletPool;
    private SimpleBlasterPool blasterPool;
    private MilitarySmallPool mMilitarySmallPool;
    private MilitaryMediumPool mMilitaryMediumPool;
    private MilitaryLargePool mMilitaryLargePool;
    private ColonyShipPool mColonyShipPool;

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
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i] = new Star(menuTextureAtlas);
        bulletPool = new BulletPool();
        blasterPool = new SimpleBlasterPool();
        mMilitarySmallPool = new MilitarySmallPool(bulletPool);
        mMilitaryMediumPool = new MilitaryMediumPool(bulletPool);
        mMilitaryLargePool = new MilitaryLargePool(bulletPool);
        mColonyShipPool = new ColonyShipPool(blasterPool);
        mViking = new Viking(mainTextureAtlas, bulletPool);
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
        mMilitaryMediumPool.updateActiveSprites(delta);
        mMilitaryLargePool.updateActiveSprites(delta);
        mColonyShipPool.updateActiveSprites(delta);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].update(delta);
        System.out.println(animateTime + " " + animateTimer[0]);

        animateTime += delta;
        if (animateTimer[0] < animateTime) {
            animateTimer[0] += Math.sqrt(animateTimer[0]);
            MilitarySmall mMilitarySmallShip = mMilitarySmallPool.obtain();
            init(mMilitarySmallShip);
        }
        if (animateTimer[1] < animateTime) {
            animateTimer[1] += animateTimer[1];
            MilitaryMedium mMilitaryMediumShip = mMilitaryMediumPool.obtain();
            init(mMilitaryMediumShip);
        }

        if (animateTimer[2] < animateTime) {
            animateTimer[2] += animateTimer[2];
            MilitaryLarge mMilitaryLargeShip = mMilitaryLargePool.obtain();
            init(mMilitaryLargeShip);
        }

        /* пока убрал колониальный корабль
        if (animateTimer[3] < animateTime) {
            animateTimer[3] += animateTimer[3];
            ColonyShip mColonyShip = mColonyShipPool.obtain();
            init(mColonyShip);
        }
        */

    }

    private void init(Ship ship) {
        ship.pos.set(MathUtils.random(serviceRect.getLeft(), serviceRect.getRight()), serviceRect.getTop());
        ship.set(serviceRect);
    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveSprites();
        mMilitarySmallPool.freeAllDestroyedActiveSprites();
        mMilitaryMediumPool.freeAllDestroyedActiveSprites();
        mMilitaryLargePool.freeAllDestroyedActiveSprites();
        mColonyShipPool.freeAllDestroyedActiveSprites();
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
        mMilitaryMediumPool.drawActiveSprites(batch);
        mMilitaryLargePool.drawActiveSprites(batch);
        mColonyShipPool.drawActiveSprites(batch);
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
        mColonyShipPool.dispose();
        mMilitaryLargePool.dispose();
        mMilitaryMediumPool.dispose();
        super.dispose();
    }

}
