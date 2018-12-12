package ru.jevo.animation.sprites.ships;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.basic.Ship;
import ru.jevo.animation.pools.weapons.BulletPool;
import ru.jevo.animation.service.Rect;
import ru.jevo.animation.sprites.weapon.Bullet;

/**
 * Created by Alexander on 03.12.2018.
 */
public class Viking extends Ship {

    private boolean pressedLeft;
    private boolean pressedRight;
    private TextureAtlas atlas;

    public Viking(TextureAtlas atlas, BulletPool bulletPool) {
        super(atlas.findRegion("viking"), 1, 2, 2);
        setHeightProportion(1f);
        setAngle(0);
        speedVector.set(1.5f, 0);
        this.bulletPool = bulletPool;
        this.atlas = atlas;
    }


    @Override
    public void resize(Rect serviceRect) {
        super.resize(serviceRect);
        System.out.println(mServiceRect.getHeight() + "????");
        pos.set(0, -mServiceRect.getHalfHeight() + this.getHalfHeight());
    }

    @Override
    public TextureRegion getRegion() {
        return this.enemyTextureAtlas.findRegion("viking");
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        check();
        pos.mulAdd(speed, delta); //скорость привязана к частоте кадров
    }

    private void check() {
        if (pos.x + this.getHalfWidth() > mServiceRect.getHalfWidth()) {
            stop();
            pos.x -= 0.01f;
        }
        if (pos.x - this.getHalfWidth() < -mServiceRect.getHalfWidth()) {
            stop();
            pos.x += 0.01f;
        }

    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (pos.x > touch.x) goLeft();
        if (pos.x < touch.x) goRight();
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        return super.touchUp(touch, pointer);

    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                if (pressedLeft)
                    goLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                if (pressedRight)
                    goRight();
                break;
        }

        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight)
                    goRight();
                else
                    stop();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft)
                    goLeft();
                else
                    stop();
                break;
            case Input.Keys.UP:
                shoot();
                break;
        }
        return false;
    }

    private void stop() {
        speed.setZero();
    }

    private void goRight() {
        speed.set(speedVector);
    }

    private void goLeft() {
        speed.set(speedVector).rotate(180);
        System.out.println(mServiceRect.getHalfWidth());
        //  currentFrame = 1;
    }

    private void shoot() {
        System.out.println("огонь");

        float angleRot = -90f;
        for (int i = 0; i < 3; i++) {
            Bullet bullet = bulletPool.obtain();
            angleRot += 45f;
            System.out.println("Угол:" + angleRot);
            bullet.setSpeedBul(bullet.getSpeedBul().rotate(angleRot));
            bullet.set(this, atlas.findRegion("bulletMainShip"),  0.1f, mServiceRect, 1);
        }
    }

}
