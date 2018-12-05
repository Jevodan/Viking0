package ru.jevo.animation.basic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 03.12.2018.
 */
public class Ship extends Sprite {

    protected float speedFire = 0;
    protected Fireable fireBehavior;

    public void setFireBehavior(Fireable fireBehavior) {
        this.fireBehavior = fireBehavior;
    }

    public float getSpeedFire() {
        return speedFire;
    }

    public Fireable getFireBehavior() {

        return fireBehavior;
    }

    // public Fire[] shipFire;
    public Vector2 speed = new Vector2();

    // TODO добавить ускорение
    // TODO проработать скорость
    // TODO сделать огонь
    // TODO изучить delta

    public Ship(TextureRegion region) {
        super(region);
        setAngle(180);
    }

    public void performFire(SpriteBatch batch) {
        fireBehavior.fire(batch);
    }


    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
    }

}
