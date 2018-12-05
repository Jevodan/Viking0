package ru.jevo.animation.basic;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 05.12.2018.
 */
public abstract class Fire extends Sprite {

    final protected static TextureAtlas  mainTextureAtlas = new TextureAtlas("atlas/mainAtlas.tpack");

    public Vector2 speed = new Vector2();
    protected Ship shipRect;

    public Fire(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect ship) {
        super.resize(ship);
       // speed.set(0.1f,-10f);
     //   System.out.println(ship.getTop());
     //   pos.set(0, ship.getTop());
    }

    @Override
    public void update(float delta) {
      //  super.update(delta);
      //  pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
      //  System.out.println("ghj");
    }
}
