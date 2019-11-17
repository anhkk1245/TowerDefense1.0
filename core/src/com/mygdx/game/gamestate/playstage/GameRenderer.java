package com.mygdx.game.gamestate.playstage;

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
import com.mygdx.game.helper.AssetLoader;
import com.mygdx.game.helper.Box;
import com.mygdx.game.helper.Wave;

public class GameRenderer {
    private GameWorld world;
    private Wave wave;
    private Box box;

    private Texture grass;
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

    private Sprite normalGun;
    private Sprite sniperGun;
    private Sprite machineGun;

    private int[][] MAP_SPRITES_1 ;
    private Vector2[] wayPoints;

    private ShapeRenderer shapeRenderer;

    private float timer ;

    private SpriteBatch batch;

    private FreeTypeFontGenerator fontGenerator;
    private FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
    private BitmapFont font;

    public GameRenderer(GameWorld world) {
        this.world = world;
        MAP_SPRITES_1 = world.MAP_SPRITES_1;
        wayPoints = world.wayPoints;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        timer = 0;
        wave = new Wave(this.world);
        box = world.box;
        initObject();
        initAsset();
        initFont();

    }

    public void initFont() {
        fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("font/COMICATE.TTF"));
        fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.size = 50;
        fontParameter.borderWidth = 3;
        fontParameter.borderColor = Color.WHITE;
        fontParameter.color = Color.BLACK;
        font = fontGenerator.generateFont(fontParameter);
    }


    public void initAsset() {
        grass = AssetLoader.grass;
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

        normalGun = new Sprite(normalTowerGun);
        sniperGun = new Sprite(sniperTowerGun);
        machineGun = new Sprite(machineTowerGun);
    }

    public void initObject() {

    }

    public void render() {
        Gdx.gl.glClearColor(0, 0, 0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        drawMap(batch);
        drawIcon(batch);

        for (int i = GameWorld.tower.size() - 1;i>=0;i--) {
            if(GameWorld.tower.get(i).isActive()) {
                GameWorld.tower.get(i).draw(batch, tower);
                if (GameWorld.tower.get(i).getId() == 1) GameWorld.tower.get(i).drawGun(batch, normalGun);
                else if (GameWorld.tower.get(i).getId() == 2) GameWorld.tower.get(i).drawGun(batch, sniperGun);
                else if (GameWorld.tower.get(i).getId() == 3) GameWorld.tower.get(i).drawGun(batch, machineGun);
                GameWorld.tower.get(i).shot();
            }
        }

        if(box.isActive()) {
            box.draw(batch, sellButton);
        }

        if(wave.waveNumber <= this.world.getWaveInfo().length) {
            wave.spawnEnemies();

            for (int i = GameWorld.EnemyList.size() - 1;i>=0;i--) {
                if(i >= GameWorld.EnemyList.size()) {
                    i=GameWorld.EnemyList.size() - 1;
                    if(i == -1) break;
                }
                if (GameWorld.EnemyList.get(i).isActive()){
                    GameWorld.EnemyList.get(i).update(wayPoints);
                    if (GameWorld.EnemyList.get(i).getId() == 1) {
                        GameWorld.EnemyList.get(i).draw(batch, NormalEnemy);
                    } else if (GameWorld.EnemyList.get(i).getId() == 2) {
                        GameWorld.EnemyList.get(i).draw(batch, TankerEnemy);
                    } else if (GameWorld.EnemyList.get(i).getId() == 3) {
                        GameWorld.EnemyList.get(i).draw(batch, SmallerEnemy);
                    } else if (GameWorld.EnemyList.get(i).getId() == 4) {
                        GameWorld.EnemyList.get(i).draw(batch, BossEnemy);
                    }
                    GameWorld.EnemyList.get(i).createHealthBar(batch, healthBarBackground, healthBar);
                }
                else GameWorld.EnemyList.remove(i);
            }

            // ket thuc 1 round hoan thoi gian roi san sinh tiep
            if (!wave.waveSpawning) {
                timer += Gdx.graphics.getDeltaTime();
                if (timer >= 10) {
                    wave.nextWave();
                    timer = 0;
                }
            }
        }

        font.draw(batch, "WAVE: \n" + wave.waveNumber +"\n\nmoney: \n " + GameWorld.playerMoney + "\n\nescaped:\n0/10", 64*13, 64*11 );

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
                if (MAP_SPRITES_1[i][j] == 1) {
                    batch.draw(road, j * 64, (11 - i ) * 64, 64, 64);
                } else if (MAP_SPRITES_1[i][j] == 0) {
                    batch.draw(grass, j * 64, (11 - i) * 64, 64, 64);
                } else if (MAP_SPRITES_1[i][j] == 2) {
                    batch.draw(water_1, j * 64, (11 - i) * 64, 64, 64);
                }  else if (MAP_SPRITES_1[i][j] == 3) {
                    batch.draw(water_2, j * 64, (11 - i) * 64, 64, 64);
                }
            }
        }
    }

    private void drawIcon(SpriteBatch batch) {
        for(int i = 0;i<GameWorld.icon.size();i++) {
            batch.draw(tower, GameWorld.icon.get(i).getX(),GameWorld.icon.get(i).getY(),64,64);
            if(i==0) batch.draw(normalTowerGun, GameWorld.icon.get(i).getX(),GameWorld.icon.get(i).getY(),64,64);
            else if(i==1) batch.draw(sniperTowerGun, GameWorld.icon.get(i).getX(),GameWorld.icon.get(i).getY(),64,64);
            else batch.draw(machineTowerGun, GameWorld.icon.get(i).getX(),GameWorld.icon.get(i).getY(),64,64);
        }
    }

    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }

}
