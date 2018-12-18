package ru.jevo.animation.sprites.weapon;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.basic.Sprite;
import ru.jevo.animation.basic.Weapon;
import ru.jevo.animation.service.Rect;

/**
 * Created by Alexander on 08.12.2018.
 */
public class Bullet extends Weapon {

    public Bullet() {
        regions = new TextureRegion[1];
        getSpeedBul().set(0, 2.5f);
        dropSound = Gdx.audio.newSound(Gdx.files.internal("sounds/weapon/weapon1_blaster.wav"));
        dropSound.play();
    }

}
