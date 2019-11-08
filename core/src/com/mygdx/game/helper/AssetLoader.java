package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class AssetLoader {
    public static Texture grass;
    public static Texture road;

    public static void load() {
        grass = new Texture(Gdx.files.internal("grass.png"));
        road = new Texture(Gdx.files.internal("road.png"));
    }
}
