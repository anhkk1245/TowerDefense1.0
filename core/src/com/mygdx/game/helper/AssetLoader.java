package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.entities.movable.TankerEnemy;

public class AssetLoader {
    public static Texture sand;
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
    public static Texture normalBullet;
    public static Texture sniperBullet;
    public static Texture machineBullet;
    public static Texture sellButton;
    public static Texture healthBar;
    public static Texture healthBarBackground;
    public static Texture water_1;
    public static Texture water_2;
    public static Texture skill;
    public static Texture sos;
    public static Texture save;
    public static Texture load;
    public static Texture start;
    public static Texture nextStage;
    public static Texture over;
    public static Texture explosionTexture;
    public static Animation<TextureRegion> explosion;

    public static Music startGame;
    public static Music gameOver;
    public static Music flash;
    public static Music shoot;
    public static Music click;
    public static Music bang;
    public static Music spotEnemy;
    public static Music bonus;
    public static Music notEnoughMoney;
    public static Music win;
    public static Music lose;
    public static Music inGame;

    public static void load() {
        sand = new Texture(Gdx.files.internal("road/sand.png"));
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
        normalBullet = new Texture(Gdx.files.internal("bullet/normal.png"));
        sniperBullet = new Texture(Gdx.files.internal("bullet/sniper.png"));
        machineBullet = new Texture(Gdx.files.internal("bullet/machine.png"));

        sellButton = new Texture(Gdx.files.internal("button/sell_button.png"));

        healthBar = new Texture(Gdx.files.internal("healthbar.png"));
        healthBarBackground = new Texture(Gdx.files.internal("emptyhealthbar.png"));

        water_1 = new Texture(Gdx.files.internal("road/2.png"));
        water_2 = new Texture(Gdx.files.internal("road/3.png"));

        skill = new Texture(Gdx.files.internal("pow/ultimate_skill.png"));
        sos = new Texture(Gdx.files.internal("pow/sos.png"));
        save = new Texture(Gdx.files.internal("pow/save.png"));
        load = new Texture(Gdx.files.internal("pow/resume.png"));

        start = new Texture(Gdx.files.internal("start.png"));
        nextStage = new Texture(Gdx.files.internal("black.png"));
        over = new Texture(Gdx.files.internal("gameover.png"));
        explosionTexture = new Texture(Gdx.files.internal("Explosion.png"));

        TextureRegion[][] tmp = TextureRegion.split(explosionTexture, 96, 96);
        TextureRegion[] explosive = new TextureRegion[12];
        int index = 0;
        for(int i =0;i<12;i++) {
            explosive[index++] = tmp[0][i];
        }
        explosion = new Animation<TextureRegion>(0.025f,explosive);

        startGame = Gdx.audio.newMusic(Gdx.files.internal("audio/start.mp3"));
        gameOver = Gdx.audio.newMusic(Gdx.files.internal("audio/over.mp3"));
        flash = Gdx.audio.newMusic(Gdx.files.internal("audio/flash.mp3"));
        click = Gdx.audio.newMusic(Gdx.files.internal("audio/click.mp3"));
        shoot = Gdx.audio.newMusic(Gdx.files.internal("audio/shoot.mp3"));
        bang = Gdx.audio.newMusic(Gdx.files.internal("audio/bang.mp3"));
        spotEnemy = Gdx.audio.newMusic(Gdx.files.internal("audio/spot_enemy.mp3"));
        bonus = Gdx.audio.newMusic(Gdx.files.internal("audio/coin.mp3"));
        notEnoughMoney = Gdx.audio.newMusic(Gdx.files.internal("audio/money.mp3"));
        win = Gdx.audio.newMusic(Gdx.files.internal("audio/you_win.mp3"));
        lose = Gdx.audio.newMusic(Gdx.files.internal("audio/you_lose.mp3"));
        inGame = Gdx.audio.newMusic(Gdx.files.internal("audio/ingame.mp3"));
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
        skill.dispose();
        sos.dispose();
        save.dispose();
        load.dispose();
        normalBullet.dispose();
        machineBullet.dispose();
        sniperBullet.dispose();
        start.dispose();
        startGame.dispose();
        gameOver.dispose();
        bang.dispose();
        click.dispose();
        flash.dispose();
        shoot.dispose();
        spotEnemy.dispose();
        bonus.dispose();
        notEnoughMoney.dispose();
        inGame.dispose();
        explosionTexture.dispose();
        win.dispose();
        lose.dispose();
    }
}
