package ru.jevo.animation.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.List;

import ru.jevo.animation.basic.BasicScreen;
import ru.jevo.animation.basic.Font;
import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.basic.Weapon;
import ru.jevo.animation.pools.other.ExplosionPool;
import ru.jevo.animation.pools.ships.ColonyShipPool;
import ru.jevo.animation.pools.ships.MilitaryLargePool;
import ru.jevo.animation.pools.ships.MilitaryMediumPool;
import ru.jevo.animation.pools.weapons.BlasterPool;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.pools.ships.MilitarySmallPool;
import ru.jevo.animation.pools.weapons.LazerPool;
import ru.jevo.animation.pools.weapons.MegaPool;
import ru.jevo.animation.pools.weapons.SimpleBlasterPool;
import ru.jevo.animation.sprites.button_titles.GameOverTitle;
import ru.jevo.animation.sprites.button_titles.NewGameTitle;
import ru.jevo.animation.sprites.other.BackgroundGame;
import ru.jevo.animation.sprites.ships.MilitaryLarge;
import ru.jevo.animation.sprites.ships.MilitaryMedium;
import ru.jevo.animation.sprites.ships.MilitarySmall;
import ru.jevo.animation.sprites.ships.Viking;
import ru.jevo.animation.sprites.other.Background;
import ru.jevo.animation.sprites.other.Star;
import ru.jevo.animation.sprites.weapon.Blaster;
import ru.jevo.animation.sprites.weapon.Bullet;
import ru.jevo.animation.sprites.weapon.Laser;
import ru.jevo.animation.sprites.weapon.Mega;
import ru.jevo.animation.sprites.weapon.SimpleBlaster;

import static ru.jevo.animation.basic.Const.BLASTER;
import static ru.jevo.animation.basic.Const.BULLET;
import static ru.jevo.animation.basic.Const.FONT_FONT_FNT;
import static ru.jevo.animation.basic.Const.FONT_FONT_PNG;
import static ru.jevo.animation.basic.Const.FRAGS;
import static ru.jevo.animation.basic.Const.HP;
import static ru.jevo.animation.basic.Const.LAZER;
import static ru.jevo.animation.basic.Const.LEVEL;
import static ru.jevo.animation.basic.Const.MEGA;
import static ru.jevo.animation.basic.Const.SIMPLE_BLASTER;
import static ru.jevo.animation.basic.Const.STAR_COUNT;
import static ru.jevo.animation.basic.Const.mainTextureAtlas;
import static ru.jevo.animation.basic.Const.FONT_SIZE;


public class GameScreen extends BasicScreen {

    private float[] animateTimer = {2f, 6f, 8f, 15f};
    private float animateTime = 0;

    Viking mViking;
    private NewGameTitle titleNewGame;
    private BackgroundGame mBackground;
    private GameOverTitle titleGameOver;
    private BulletPool bulletPool;
    private SimpleBlasterPool simpleBlasterPool;
    private LazerPool lazerPool;
    private MegaPool megaPool;
    private BlasterPool blasterPool;
    private MilitarySmallPool mMilitarySmallPool;
    private MilitaryMediumPool mMilitaryMediumPool;
    private MilitaryLargePool mMilitaryLargePool;
    private ColonyShipPool mColonyShipPool;
    private ExplosionPool mExplosionPool;
    private List<Ship> ships = new ArrayList<Ship>();
    private List<Weapon> weapons = new ArrayList<Weapon>();
    private Font mFont;
    public static int mFrags;
    public static int mHp;
    public static int mLevel = 1;
    private StringBuilder sbFrags = new StringBuilder();
    private StringBuilder sbHp = new StringBuilder();
    private StringBuilder sbLevel = new StringBuilder();


    private int sourceY = 0;


    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bgTexture = new Texture("images/bg_level1.jpg");
        titleNewGame = new NewGameTitle(mainTextureAtlas);
        titleGameOver = new GameOverTitle(mainTextureAtlas);
        mBackground = new BackgroundGame(new TextureRegion(bgTexture), 5 * glRect.getBottom());
        rainMusic.setLooping(true); // повторение музыки
        rainMusic.play();
        mExplosionPool = new ExplosionPool(mainTextureAtlas);
        bulletPool = BulletPool.getInstance();
        simpleBlasterPool = SimpleBlasterPool.getInstance();
        lazerPool = LazerPool.getInstance();
        megaPool = MegaPool.getInstance();
        blasterPool = BlasterPool.getInstance();
        mMilitarySmallPool = MilitarySmallPool.getInstance(BULLET);
        mMilitaryMediumPool = MilitaryMediumPool.getInstance(LAZER);
        mMilitaryLargePool = MilitaryLargePool.getInstance(BLASTER);
       // mColonyShipPool = new ColonyShipPool(MEGA);
        mViking = new Viking(mainTextureAtlas, SIMPLE_BLASTER);
        mFont = new Font(FONT_FONT_FNT, FONT_FONT_PNG);
        mFont.setFontSize(FONT_SIZE);
        mHp = mViking.gethP();
    }

    public void printInfo() {
        sbFrags.setLength(0);
        sbHp.setLength(0);
        sbLevel.setLength(0);
        mFont.draw(batch, sbFrags.append(FRAGS).append(mFrags), serviceRect.getLeft(), serviceRect.getTop());
        mFont.draw(batch, sbHp.append(HP).append(mViking.gethP()), serviceRect.pos.x, serviceRect.getTop(), Align.center);
        mFont.draw(batch, sbLevel.append(LEVEL).append(mLevel), serviceRect.getRight(), serviceRect.getTop(), Align.right);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (mViking.gethP() > 0) {
            update(delta);
            checkCollisions();
            deleteAllDestroyed();
            draw(delta);
        } else {
            titleGameOver.update(delta);
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            mBackground.draw(batch);
            titleGameOver.draw(batch);
            batch.end();
        }
    }

    private void checkCollisions() {
        collisionShipsInit();
        collisionWeaponsInit();
        collisionTaran(mExplosionPool);
        collisionBulletWithEnemy();
        collisionBulletWithViking();
    }

    private void collisionBulletWithViking() {
        for (Weapon weap : weapons) {
            if (weap.isDestroyed() || weap.getOwner() == mViking) {
                continue;
            }
            if (mViking.isBulletCollision(weap)) {
                weap.setDestroyed(true);
                mViking.damage(weap.getDamage(), mExplosionPool);
                mViking.setCurrentFrame(1);
                mHp -= weap.getDamage();
            }
        }
    }

    private void collisionBulletWithEnemy() {
        for (Ship enemy : ships) {
            if (enemy.isDestroyed()) {
                continue;
            }
            for (Weapon weap : weapons) {
                if (weap.getOwner() != mViking || weap.isDestroyed()) {
                    continue;
                }
                if (enemy.isBulletCollision(weap)) {
                    enemy.damage(weap.getDamage(), mExplosionPool);
                    weap.setSpeedBul(new Vector2(0, 2.5f));
                    weap.setDestroyed(true);
                }
            }
        }
    }

    private void collisionTaran(ExplosionPool exp) {
        for (Ship enemy : ships) {
            if (enemy.isDestroyed())
                continue;

            float minDist = enemy.getHalfWidth() + mViking.getHalfWidth();
            if (enemy.pos.dst2(mViking.pos) < minDist * minDist) {
                enemy.setDestroyed(true);
                enemy.boom(mExplosionPool);
                mViking.damage(Viking.DAMAGE_TARAN, exp);
                mFrags++;
                return;
            }
        }
    }

    private void collisionShipsInit() {
        ships.clear();
        List<MilitarySmall> minPool = mMilitarySmallPool.getactivePool();
        List<MilitaryMedium> medPool = mMilitaryMediumPool.getactivePool();
        List<MilitaryLarge> largePool = mMilitaryLargePool.getactivePool();
        ships.addAll(minPool);
        ships.addAll(medPool);
        ships.addAll(largePool);
    }

    private void collisionWeaponsInit() {
        weapons.clear();
        List<Bullet> bulletsPool = bulletPool.getactivePool();
        List<SimpleBlaster> simpleBlastersPool = simpleBlasterPool.getactivePool();
        List<Laser> lazersPool = lazerPool.getactivePool();
        List<Blaster> blastersPool = blasterPool.getactivePool();
        List<Mega> megasPool = megaPool.getactivePool();
        weapons.addAll(megasPool);
        weapons.addAll(blastersPool);
        weapons.addAll(lazersPool);
        weapons.addAll(bulletsPool);
        weapons.addAll(simpleBlastersPool);
    }

    public void update(float delta) {
        mBackground.update(delta);
        mViking.update(delta);
        titleNewGame.update(delta);
        mExplosionPool.updateActiveSprites(delta);
        bulletPool.updateActiveSprites(delta);
        simpleBlasterPool.updateActiveSprites(delta);
        lazerPool.updateActiveSprites(delta);
        megaPool.updateActiveSprites(delta);
        blasterPool.updateActiveSprites(delta);
        mMilitarySmallPool.updateActiveSprites(delta);
        mMilitaryMediumPool.updateActiveSprites(delta);
        mMilitaryLargePool.updateActiveSprites(delta);

        //    mColonyShipPool.updateActiveSprites(delta);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].update(delta);
        //  System.out.println(animateTime + " " + animateTimer[0]);

        animateTime += delta;
        if (animateTimer[0] < animateTime) {
            animateTimer[0] += 10 / Math.sqrt(animateTimer[0]);
            MilitarySmall mMilitarySmallShip = mMilitarySmallPool.obtain();
            init(mMilitarySmallShip);

        }

        if (animateTimer[1] < animateTime) {
            animateTimer[1] += 100 / Math.sqrt(animateTimer[1]);
            MilitaryMedium mMilitaryMediumShip = mMilitaryMediumPool.obtain();
            init(mMilitaryMediumShip);
        }

        if (animateTimer[2] < animateTime) {
            animateTimer[2] += 200 / Math.sqrt(animateTimer[2]);
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

    @Override
    public void pause() {
        System.out.println("Пауза");
    }

    @Override
    public void resume() {
        super.resume();
    }

    private void init(Ship ship) {
        ship.pos.set(MathUtils.random(serviceRect.getLeft(), serviceRect.getRight()), serviceRect.getTop() + 1f);
        ship.set(serviceRect);
    }

    public void deleteAllDestroyed() {
        mExplosionPool.freeAllDestroyedActiveSprites();
        bulletPool.freeAllDestroyedActiveSprites();
        simpleBlasterPool.freeAllDestroyedActiveSprites();
        lazerPool.freeAllDestroyedActiveSprites();
        megaPool.freeAllDestroyedActiveSprites();
        blasterPool.freeAllDestroyedActiveSprites();
        mMilitarySmallPool.freeAllDestroyedActiveSprites();
        mMilitaryMediumPool.freeAllDestroyedActiveSprites();
        mMilitaryLargePool.freeAllDestroyedActiveSprites();
        //    mColonyShipPool.freeAllDestroyedActiveSprites();
    }

    public void draw(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        mBackground.draw(batch);
        titleNewGame.draw(batch);
        for (int i = 0; i < STAR_COUNT; i++)
            mStar[i].draw(batch);
        mViking.draw(batch);
        bulletPool.drawActiveSprites(batch);
        simpleBlasterPool.drawActiveSprites(batch);
        lazerPool.drawActiveSprites(batch);
        megaPool.drawActiveSprites(batch);
        blasterPool.drawActiveSprites(batch);
        mMilitarySmallPool.drawActiveSprites(batch);
        mMilitaryMediumPool.drawActiveSprites(batch);
        mMilitaryLargePool.drawActiveSprites(batch);
        //   mColonyShipPool.drawActiveSprites(batch);
        mExplosionPool.drawActiveSprites(batch);
        printInfo();
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
        simpleBlasterPool.dispose();
        lazerPool.dispose();
        blasterPool.dispose();
        megaPool.dispose();
        mMilitarySmallPool.dispose();
        //    mColonyShipPool.dispose();
        mMilitaryLargePool.dispose();
        mMilitaryMediumPool.dispose();
        mExplosionPool.dispose();
        mFont.dispose();
        super.dispose();
    }

}
