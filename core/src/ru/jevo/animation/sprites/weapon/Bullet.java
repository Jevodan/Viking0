package ru.jevo.animation.sprites.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.jevo.animation.basic.Weapon;


/**
 * Created by Alexander on 08.12.2018.*
 *
 */

public class Bullet extends Weapon {

    public Bullet() {
        regions = new TextureRegion[1];
        this.regions[0] = getRegion();
        this.damage = 5;
        getSpeedBul().set(0, 2.5f);
        setHeightProportion(0.15f);
        dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/weapon1_blaster.wav"));
        dropSound.play();
    }

    @Override
    protected TextureRegion getRegion() {
        return this.mainTextureAtlas.findRegion("bulletEnemy");
    }

}
