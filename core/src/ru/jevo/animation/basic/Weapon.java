package ru.jevo.animation.basic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 16.12.2018.
 */
public abstract class Weapon extends Sprite {

    protected Ship owner;
    protected boolean side;
    protected int damage;
    protected Vector2 speedBul = new Vector2();
    protected Sound dropSound;
    protected TextureAtlas mainTextureAtlas = new TextureAtlas("atlas/mainAtlas.tpack");


    public void set(
            Ship owner,
            Rect serviceRect,
            boolean side
    ) {
        this.owner = owner;
        this.pos.set(owner.pos.x, (!side) ? owner.getTop() : owner.getBottom());
        this.mServiceRect = serviceRect;
        dropSound.play();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(speedBul, delta);
        if (isOutside(mServiceRect)) {
            setAngle(0);
            setSpeedBul(new Vector2(0, 2.5f));
            setDestroyed(true);
        }
    }

    public Vector2 getSpeedBul() {
        return speedBul;
    }

    public void setSpeedBul(Vector2 speedBul) {
        this.speedBul = speedBul;
    }

    public Ship getOwner() {
        return owner;
    }

    protected abstract TextureRegion getRegion();

    public void setOwner(Ship owner) {
        this.owner = owner;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
