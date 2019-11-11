package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture grass;
    public static Texture road;
    public static Texture NormalEnemy;
    public static Texture tower;
    public static Texture towerGun;

    public static void load() {
        grass = new Texture(Gdx.files.internal("grass.png"));
        road = new Texture(Gdx.files.internal("road.png"));
        NormalEnemy = new Texture(Gdx.files.internal("WALK_000.png"));
        tower = new Texture(Gdx.files.internal("tower.png"));
        towerGun = new Texture(Gdx.files.internal("tower_gun.png"));
    }

    public static void dispose() {
        grass.dispose();
        road.dispose();
        NormalEnemy.dispose();
    }
}
