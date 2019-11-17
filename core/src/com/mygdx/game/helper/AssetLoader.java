package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entities.movable.TankerEnemy;

public class AssetLoader {
    public static Texture grass;
    public static Texture road;
    public static Texture NormalEnemy;
    public static Texture tankerEnemy;
    public static Texture smallerEnemy;
    public static Texture bossEnemy;
    public static Texture tower;
    public static Texture normalTowerGun;
    public static Texture sniperTowerGun;
    public static Texture machineTowerGun;
    public static Texture bullet;
    public static Texture sellButton;
    public static Texture healthBar;
    public static Texture healthBarBackground;
    public static Texture water_1;
    public static Texture water_2;

    public static void load() {
        grass = new Texture(Gdx.files.internal("road/grass_tile_3.png"));
        road = new Texture(Gdx.files.internal("road/road.png"));

        NormalEnemy = new Texture(Gdx.files.internal("enemy/normal_enemy.png"));
        tankerEnemy = new Texture(Gdx.files.internal("enemy/tanker_enemy.png"));
        smallerEnemy = new Texture(Gdx.files.internal("enemy/smaller_enemy.png"));
        bossEnemy = new Texture(Gdx.files.internal("enemy/boss_enemy.png"));



        tower = new Texture(Gdx.files.internal("tower/tower.png"));
        normalTowerGun = new Texture(Gdx.files.internal("tower/normal.png"));
        sniperTowerGun = new Texture(Gdx.files.internal("tower/sniper.png"));
        machineTowerGun = new Texture(Gdx.files.internal("tower/machine.png"));

        bullet = new Texture(Gdx.files.internal("bullet/bullet.png"));

        sellButton = new Texture(Gdx.files.internal("button/sell_button.png"));

        healthBar = new Texture(Gdx.files.internal("healthbar.png"));
        healthBarBackground = new Texture(Gdx.files.internal("emptyhealthbar.png"));

        water_1 = new Texture(Gdx.files.internal("road/2.png"));
        water_2 = new Texture(Gdx.files.internal("road/3.png"));
    }

    public static void dispose() {
        grass.dispose();
        road.dispose();
        NormalEnemy.dispose();
        tankerEnemy.dispose();
        smallerEnemy.dispose();
        bossEnemy.dispose();
        tower.dispose();
        sniperTowerGun.dispose();
        machineTowerGun.dispose();
        sellButton.dispose();
        healthBar.dispose();
        healthBarBackground.dispose();
        water_1.dispose();
        water_2.dispose();
    }
}
