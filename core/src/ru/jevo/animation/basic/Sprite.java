package ru.jevo.animation.basic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.jevo.animation.service.Rect;
import ru.jevo.animation.service.Regions;


/**
 * Created by Alexander on 02.12.2018.
 */
public abstract class Sprite extends Rect {



    public static final float SCALE_DEFAULT = 1f;
    protected float angle;
    protected float scale = SCALE_DEFAULT;
    protected TextureRegion[] regions;
    protected int currentFrame;
    protected Rect mServiceRect;
    protected boolean isDestroyed;
    protected float timerAnimation;

    public Sprite() {
    }

    public Sprite(TextureRegion region) {
        regions = new TextureRegion[1];
        regions[0] = region;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public Sprite(TextureRegion region, int cols, int rows, int frames) {
        regions = Regions.split(region, cols, rows, frames);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(
                regions[currentFrame],  // текущий регион
                getLeft(),              // точка отрисовки
                getBottom(),
                halfWidth,              // точки вращения
                halfHeight,
                getWidth(),
                getHeight(),            // Ширина и высота
                scale,                  // масштаб x y
                scale,
                angle
        );
    }

    public void setHeightProportion(float height) {
        setHeight(height);
        float aspect = regions[currentFrame].getRegionWidth() / (float) regions[currentFrame].getRegionHeight();
        setWidth(height * aspect);
    }

    public void setWidthProportion(float width) {
        setWidth(width);
        float aspect = regions[currentFrame].getRegionHeight() / (float) regions[currentFrame].getRegionWidth();
        setHeight(width * aspect);
    }

    public void resize(Rect serviceRect) {
        this.mServiceRect = serviceRect;
    }

    public void update(float delta) {

    }

    public boolean touchDown(Vector2 touch, int pointer) {
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        return false;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }


    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.isDestroyed = destroyed;
    }
}
