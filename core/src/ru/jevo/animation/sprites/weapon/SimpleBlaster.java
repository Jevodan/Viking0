package ru.jevo.animation.sprites.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.Fire;
import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.basic.Weapon;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 03.12.2018.
 */
public class SimpleBlaster extends Weapon {

    private Ship owner;
    private int damage;
    private Vector2 speedBul = new Vector2(0, 2.5f);
    Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapon1_blaster.wav"));;


    public SimpleBlaster() {
        regions = new TextureRegion[1];
    }


    @Override
    public void resize(Rect shipRect) {
        super.resize(shipRect);
        System.out.println(shipRect.getLeft() + "----- " + shipRect.pos.y);
        pos.set(shipRect.pos);
    }

    @Override
    public void update(float delta) {
          super.update(delta);
        pos.mulAdd(speedBul, delta); //скорость привязана к частоте кадров
      //  System.out.println(speed);
    }

    public void set(
            Ship owner,
            TextureRegion region,
            float height,
            Rect serviceRect,
            int damage
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(owner.pos.x, owner.getTop());
        setHeightProportion(height);
        this.mServiceRect = serviceRect;
        this.damage = damage;
        dropSound.play();
    }

    public void setSpeedBul(Vector2 speedBul) {
        this.speedBul = speedBul;
    }

    public Vector2 getSpeedBul() {
        return speedBul;
    }
}
