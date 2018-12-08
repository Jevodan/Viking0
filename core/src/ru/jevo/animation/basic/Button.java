package ru.jevo.animation.basic;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alexander on 06.12.2018.
 */
public abstract class Button extends Sprite {

    private static final float PRESS_SCALE = 0.9f;
    private int pointer;
    private boolean pressed;

    public Button(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        System.out.println("дошло");
        if (pressed || !isMe(touch))
            return false;

        pressed = true;
        this.pointer = pointer;
        setScale(PRESS_SCALE);
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
       if (this.pointer != pointer || !pressed)
           return false;
       if (isMe(touch))
           actionPerformed();
       pressed = false;
       setScale(SCALE_DEFAULT);
       return false;
    }

    protected abstract void actionPerformed();

}
