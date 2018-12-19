package ru.jevo.animation.sprites.other;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 19.12.2018.
 */
public class BackgroundGame extends Sprite {

    private Rect serviceRect;
    private Vector2 speed = new Vector2(0,-4.5f); // скорость звезды
    private TextureRegion region;
    private float y;

    public BackgroundGame(TextureRegion region , float y) {
        super(region);
        this.region = region;
        this.y = y;
        System.out.println(this.y + "!!!!!!!!!!!!!!!");

    }

    @Override
    public void draw(SpriteBatch batch) {
        this.y -=0.008f;
       batch.draw(region,getLeft(), this.y, halfWidth, halfHeight, getWidth(), getHeight(), scale, scale, angle);
    }

    @Override
    public void resize(Rect serviceRect) {
      //  super.resize(serviceRect);
    //    this.serviceRect = serviceRect;
        setWidthProportion(serviceRect.getWidth());
        pos.set(serviceRect.pos);
    }

}
