package ru.jevo.animation.service;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Alexander on 30.11.2018.
 */


public class Rect {

    public Vector2 pos = new Vector2(); // позиция по центру
    protected float halfWidth; // половина ширины
    protected float halfHeight; // половина высоты

    public Rect(float x, float y, float halfWidth, float halfHeight) {
        pos.set(x, y);
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
    }

    public Rect(Rect rect) {
        this(rect.pos.x, rect.pos.y, rect.getHalfWidth(), rect.getHalfHeight());
    }

    public Rect() {

    }

    public void setSize(float width, float height) {
        this.halfWidth = width / 2f;
        this.halfHeight = height / 2f;
    }

    public void setLeft(float left) {
        pos.x = left + halfWidth;
    }

    public void setRight(float right) {
        pos.x = right - halfWidth;
    }

    public void setBottom(float bottom) {
        pos.y = bottom + halfHeight;
    }

    public void setTop(float top) {
        pos.y = top - halfHeight;
    }

    public float getWidth() {
        return halfWidth * 2f;
    }

    public float getHeight() {
        return halfHeight * 2f;
    }

    public float getLeft() {
        return pos.x - halfWidth;
    }

    public float getTop() {
        return pos.y + halfHeight;
    }

    public float getRight() {
        return pos.x + halfWidth;
    }

    public float getBottom() {
        return pos.y - halfHeight;
    }

    public void setWidth(float width) {
        this.halfWidth = width / 2f;
    }

    public void setHeight(float height) {
        this.halfHeight = height / 2f;
    }

    public float getHalfWidth() {
        return halfWidth;
    }

    public float getHalfHeight() {
        return halfHeight;
    }

    public void set(Rect from) {
        pos.set(from.pos);
        halfWidth = from.halfWidth;
        halfHeight = from.halfHeight;
    }



    public boolean isMe(Vector2 touch) {
        System.out.println(touch.y + " " + getBottom());
        return touch.x >= getLeft() && touch.x <= getRight() && touch.y >= getBottom() && touch.y <= getTop();
    }

    public boolean isOutside(Rect other) {
        return getLeft() > other.getRight() || getRight() < other.getLeft() || getBottom() > other.getTop() || getTop() < other.getBottom();
    }
}
