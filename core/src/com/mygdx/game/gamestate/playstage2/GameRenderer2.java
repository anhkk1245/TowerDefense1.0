package com.mygdx.game.gamestate.playstage2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.gamestate.startstage.StartStage;
import com.mygdx.game.helper.AssetLoader;
import com.mygdx.game.helper.Box;
import com.mygdx.game.helper.Wave;

public class GameRenderer2 {
    private GameWorld2 world;
    private Wave wave;
    private Box box;

    private Texture sand;
    private Texture road;
    private Texture NormalEnemy;
    private Texture TankerEnemy;
    private Texture SmallerEnemy;
    private Texture BossEnemy;
    private Texture tower;
    private Texture normalTowerGun;
    private Texture sniperTowerGun;
    private Texture machineTowerGun;
    private Texture sellButton;
    private Texture healthBar;
    private Texture healthBarBackground;
    private Texture water_1;
    private Texture water_2;
    private Texture bullet;
    private Texture skill;
    private Texture sos;
    private Texture save;
    private Texture load;

    private Sprite plane;

    private int[][] MAP_SPRITES_2 ;
    private Vector2[] wayPoints;

    private ShapeRenderer shapeRenderer;

    public static float timer = StartStage.timer;
    private float timer2 = 0;
    private float timer3 = 0;

    private SpriteBatch batch;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;

    public GameRenderer2(GameWorld2 world) {
        this.world = world;
        MAP_SPRITES_2 = world.MAP_SPRITES_2;
        wayPoints = world.wayPoints;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        wave = new Wave(this.world);
        box = world.box;
        initObject();
        initAsset();
        initFont();

    }

    public void initFont() {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font/Pacifico.ttf"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 38;
        fontParameter.borderWidth = 1;
        fontParameter.borderColor = Color.BLACK;
        fontParameter.color = Color.BLACK;
        font = fontGenerator.generateFont(fontParameter);
    }


    public void initAsset() {
        sand = AssetLoader.sand;
        road = AssetLoader.road;
        NormalEnemy = AssetLoader.NormalEnemy;
        TankerEnemy = AssetLoader.tankerEnemy;
        SmallerEnemy = AssetLoader.smallerEnemy;
        BossEnemy = AssetLoader.bossEnemy;
        tower = AssetLoader.tower;
        normalTowerGun = AssetLoader.normalTowerGun;
        machineTowerGun = AssetLoader.machineTowerGun;
        sniperTowerGun = AssetLoader.sniperTowerGun;
        sellButton = AssetLoader.sellButton;
        healthBar = AssetLoader.healthBar;
        healthBarBackground = AssetLoader.healthBarBackground;
        water_1 = AssetLoader.water_1;
        water_2 = AssetLoader.water_2;
        bullet = AssetLoader.bullet;
        skill = AssetLoader.skill;
        sos = AssetLoader.sos;
        save = AssetLoader.save;
        load = AssetLoader.load;

    }

    public void initObject() {

    }

    public void render() {
        Gdx.gl.glClearColor(216/255.0f, 224/255.0f, 222/255.0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        drawMap(batch);
        drawIcon(batch);

        for (int i = GameWorld2.tower.size() - 1;i>=0;i--) {
            if(GameWorld2.tower.get(i).isActive()) {
                GameWorld2.tower.get(i).draw(batch, tower);
                if (GameWorld2.tower.get(i).getId() == 1) GameWorld2.tower.get(i).drawGun(batch);
                else if (GameWorld2.tower.get(i).getId() == 2) GameWorld2.tower.get(i).drawGun(batch);
                else if (GameWorld2.tower.get(i).getId() == 3) GameWorld2.tower.get(i).drawGun(batch);
            }
            if(GameWorld2.tower.get(i).isPlanted()) GameWorld2.tower.get(i).shot(batch);
        }

        if(GameWorld2.plane.isActive()) {
            timer3 += Gdx.graphics.getDeltaTime();
            GameWorld2.plane.update();
            GameWorld2.plane.draw(batch, skill, 70, 70);
            if(GameWorld2.plane.getX() < 10* 64) {
                AssetLoader.bang.play();
                batch.draw(AssetLoader.explosion.getKeyFrame(timer3,true), GameWorld2.plane.getX() + 64, GameWorld2.plane.getY()-450,300,750);
                if(timer3>1) GameWorld2.resetEnemy();
            }
        }
        else {
            timer3 = 0;
            GameWorld2.plane.setPlanePosition(13*64,8*64);
        }

        if(box.isActive()) {
            box.draw(batch, sellButton, 96, 40);
            timer2 += Gdx.graphics.getDeltaTime();
            if(timer2 > 3) {
                box.setActive(false);
                timer2 = 0;
            }
        }

        if(wave.waveNumber <= this.world.getWaveInfo().length) {
            if(!StartStage.justLoad) {
                wave.spawnEnemies();
            }

            for (int i = 0;i < GameWorld2.EnemyList.size();i++) {
                if (GameWorld2.EnemyList.get(i).isActive()){
                    GameWorld2.EnemyList.get(i).findPath(wayPoints);
                    if (GameWorld2.EnemyList.get(i).getId() == 1) {
                        GameWorld2.EnemyList.get(i).draw(batch, NormalEnemy);
                    } else if (GameWorld2.EnemyList.get(i).getId() == 2) {
                        GameWorld2.EnemyList.get(i).draw(batch, TankerEnemy);
                    } else if (GameWorld2.EnemyList.get(i).getId() == 3) {
                        GameWorld2.EnemyList.get(i).draw(batch, SmallerEnemy);
                    } else if (GameWorld2.EnemyList.get(i).getId() == 4) {
                        GameWorld2.EnemyList.get(i).draw(batch, BossEnemy);
                    }
                    GameWorld2.EnemyList.get(i).createHealthBar(batch, healthBarBackground, healthBar);
                }
                else {
                    GameWorld2.EnemyList.remove(i);
                    i--;
                }
            }

            // ket thuc 1 round hoan thoi gian roi san sinh tiep
            if (!wave.waveSpawning) {
                timer += Gdx.graphics.getDeltaTime();
                if (timer >= 24) {
                    wave.nextWave();
                    timer = 0;
                }
            }
        }

        if(wave.waveNumber <= 10) {
            font.draw(batch, "WAVE:\n" + Wave.waveNumber + "\nMONEY:\n " + GameWorld2.playerMoney + "\nESCAPED:\n" + GameWorld2.escapedEnemy + "/10", 64 * 13, 64 * 11);
        }

        batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        drawWayPoints(shapeRenderer);
        shapeRenderer.end();

        //normalEnemy.update(wayPoints, 0);
    }

    private void drawWayPoints(ShapeRenderer shapeRenderer) {
        for(int i = 0;i<wayPoints.length;i++) {
            shapeRenderer.circle(wayPoints[i].x,wayPoints[i].y,3);
        }
    }

    private void drawMap(SpriteBatch batch) {
        for(int i = 0; i < 10;i++) {
            for (int j = 0; j < 13; j++) {
                if (MAP_SPRITES_2[i][j] == 1) {
                    batch.draw(road, j * 64, (11 - i ) * 64, 64, 64);
                } else if (MAP_SPRITES_2[i][j] == 0) {
                    batch.draw(sand, j * 64, (11 - i) * 64, 64, 64);
                } else if (MAP_SPRITES_2[i][j] == 2) {
                    batch.draw(water_1, j * 64, (11 - i) * 64, 64, 64);
                }  else if (MAP_SPRITES_2[i][j] == 3) {
                    batch.draw(water_2, j * 64, (11 - i) * 64, 64, 64);
                }
            }
        }
    }

    private void drawIcon(SpriteBatch batch) {
        for(int i = 0;i<GameWorld2.icon.size();i++) {
            batch.draw(tower, GameWorld2.icon.get(i).getX(),GameWorld2.icon.get(i).getY(),64,64);
            if(i==0) batch.draw(normalTowerGun, GameWorld2.icon.get(i).getX(),GameWorld2.icon.get(i).getY(),64,64);
            else if(i==1) batch.draw(sniperTowerGun, GameWorld2.icon.get(i).getX(),GameWorld2.icon.get(i).getY(),64,64);
            else batch.draw(machineTowerGun, GameWorld2.icon.get(i).getX(),GameWorld2.icon.get(i).getY(),64,64);
        }
        for(int i = 0;i<GameWorld2.iconBox.size();i++) {
            if(GameWorld2.iconBox.get(i).getId() == 1)
                GameWorld2.iconBox.get(i).draw(batch, sos, 64, 64);
            else if(GameWorld2.iconBox.get(i).getId() == 2)
                GameWorld2.iconBox.get(i).draw(batch, save, 64, 64);
            else if(GameWorld2.iconBox.get(i).getId() == 3)
                GameWorld2.iconBox.get(i).draw(batch, load, 64, 64);
        }
    }

    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }

}