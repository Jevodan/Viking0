package ru.jevo.animation.basic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.pools.other.ExplosionPool;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.pools.weapons.SimpleBlasterPool;
import ru.jevo.animation.service.Rect;
import ru.jevo.animation.sprites.other.Explosion;

/**
 * Created by Alexander on 03.12.2018.
 */
public abstract class Ship extends Sprite {

    protected ExplosionPool mExplosionPool;
    protected float speedFire = 0;
    protected float animateTimer = 0;
    protected Fireable fireBehavior;
    protected TextureAtlas enemyTextureAtlas;
    protected TextureAtlas mainTextureAtlas = new TextureAtlas("atlas/mainAtlas.tpack");
    protected TextureRegion region;
    protected BulletPool bulletPool;
    protected SimpleBlasterPool BlasterPool;

    protected int hP;

    public void setFireBehavior(Fireable fireBehavior) {
        this.fireBehavior = fireBehavior;
    }

    public float getSpeedFire() {
        return speedFire;
    }

    public Vector2 speed = new Vector2();
    protected Vector2 speedVector = new Vector2();

    public Ship(TextureRegion region) {
        super(region);
        setAngle(180);
    }

    public Fireable getFireBehavior() {

        return fireBehavior;
    }

    public TextureAtlas getAtlas() {
        return enemyTextureAtlas;
    }

    public Ship(TextureRegion region, int cols, int rows, int frames) {
        super(region, cols, rows, frames);
        setAngle(180);
    }

    public Ship(){
        this.enemyTextureAtlas = new TextureAtlas("atlas/enemy1/enemy_pack1.atlas");
    }
    public void set(Rect serviceRect) {  this.mServiceRect = serviceRect; }

    public void performFire(SpriteBatch batch) {
        fireBehavior.fire(batch);
    }


    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
    }

    public abstract TextureRegion getRegion();

    public TextureAtlas getMainTextureAtlas() {
        return mainTextureAtlas;
    }


    public void boom(ExplosionPool exp) {
        Explosion explosion = exp.obtain();
        explosion.set(getHeight(), pos);
    }

    public void damage(int damage, ExplosionPool exp) {
        sethP(gethP() - damage);
        if (gethP() <=0) {
            this.setDestroyed(true);
            boom(exp);
        }
    }

    public int gethP() {
        return hP;
    }

    public void sethP(int hP) {
        this.hP = hP;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y);
    }

}
