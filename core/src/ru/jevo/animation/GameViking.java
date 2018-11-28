package ru.jevo.animation;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.Desktop;

import java.util.Iterator;

import ru.jevo.animation.screens.GameScreen;
import ru.jevo.animation.screens.MenuScreen;

/**
 * Created by Alexander on 23.11.2018.
 */
public class GameViking extends Game {

    @Override
    public void create() {
        System.out.println("GOOOOOOOOOOOOO");
        setScreen(new MenuScreen(this));
    }



}
