package ru.jevo.animation.basic;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 03.12.2018.
 */
public class Ship extends Sprite {

    public static final int ENEMY_COUNT = 5;
    protected float speedFire = 0;
    protected float animateTimer = 0;
    protected Fireable fireBehavior;
    protected TextureAtlas enemyTextureAtlas;

    public void setFireBehavior(Fireable fireBehavior) {
        this.fireBehavior = fireBehavior;
    }

    public float getSpeedFire() {
        return speedFire;
    }


    // public Fire[] shipFire;
    public Vector2 speed = new Vector2();
    protected Vector2 speedVector = new Vector2();

    // TODO добавить ускорение
    // TODO проработать скорость
    // TODO сделать огонь
    // TODO изучить delta

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

    public void set(
            Vector2 speedShip,
            float height,
            Rect serviceRect
    ) {


      //  System.out.println(region);
        this.regions[0] = getAtlas().findRegion("family5/military_small");
       // this.pos.set(m.pos.x, owner.getTop());
        this.speed.set(speedShip);
        setHeightProportion(height);
        this.mServiceRect = serviceRect;

       // dropSound.play();
    }


    public void performFire(SpriteBatch batch) {
        fireBehavior.fire(batch);
    }


    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
    }

}
