package ru.jevo.animation.sprites.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.Const;
import ru.jevo.animation.basic.Fire;
import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.basic.Weapon;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 03.12.2018.
 */
public class SimpleBlaster extends Weapon {

    public SimpleBlaster() {
        regions = new TextureRegion[1];
        this.regions[0] = getRegion();
        this.damage = 5;
        setHeightProportion(0.15f);
        getSpeedBul().set(0, 5f);
        dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/simple_blaster.wav"));
        dropSound.play();
    }

    @Override
    protected TextureRegion getRegion() {
        return this.mainTextureAtlas.findRegion("bulletMainShip");
    }


}
