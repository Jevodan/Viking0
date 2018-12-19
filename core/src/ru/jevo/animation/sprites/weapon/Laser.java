package ru.jevo.animation.sprites.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.jevo.animation.basic.Weapon;

/**
 * Created by Alexander on 16.12.2018.
 */
public class Laser extends Weapon {

    public Laser() {
        regions = new TextureRegion[1];
        this.regions[0] = getRegion();
        this.damage = 15;
        getSpeedBul().set(0, 10f);
        setHeightProportion(0.4f);
        dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/laser.wav"));
        dropSound.play();
    }

    @Override
    protected TextureRegion getRegion() {
        return this.mainTextureAtlas.findRegion("laser");
    }
}
