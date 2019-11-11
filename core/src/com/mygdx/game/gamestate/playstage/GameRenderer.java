package com.mygdx.game.gamestate.playstage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.entities.movable.Enemy;
import com.mygdx.game.entities.movable.NormalEnemy;
import com.mygdx.game.entities.tile.Tower;
import com.mygdx.game.helper.AssetLoader;

import java.util.ArrayList;
import java.util.List;

public class GameRenderer {
    private GameWorld world;
    private Texture grass;
    private Texture road;
    private Texture NormalEnemy;
    private Texture tower;
    private Texture towerGun;
    private int[][] MAP_SPRITES_1 ;
    private Vector2[] wayPoints;
    private ShapeRenderer shapeRenderer;
    private List<NormalEnemy> listEnemy = new ArrayList<>();
    private Tower tower0;
    private float timer ;

    private SpriteBatch batch;



    public GameRenderer(GameWorld world) {
        this.world = world;
        MAP_SPRITES_1 = world.MAP_SPRITES_1;
        wayPoints = world.wayPoints;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        timer = 0;
        initObject();
        initAsset();

    }

    public void initAsset() {
        grass = AssetLoader.grass;
        road = AssetLoader.road;
        NormalEnemy = AssetLoader.NormalEnemy;
        tower = AssetLoader.tower;
        towerGun = AssetLoader.towerGun;
    }

    public void initObject() {
        listEnemy.add(new NormalEnemy(wayPoints[0].x, wayPoints[0].y, 10));
        tower0 = world.getTower();
    }
    int wayPointIndex = 0;

    public void render() {
        Gdx.gl.glClearColor(160/255.0f, 82/255.0f, 45/255.0f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        drawMap(batch);
        drawIcon(batch);

        tower0.draw(batch, tower);
        tower0.draw(batch, towerGun);
        //if(Gdx.input.isTouched())
        //tower0.update(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        timer += Gdx.graphics.getDeltaTime();
        if(timer >= 1.5) {
            listEnemy.add(new NormalEnemy(wayPoints[0].x, wayPoints[0].y, 10));
            timer = 0;
        }

        for(int i =0;i<listEnemy.size();i++) {
            listEnemy.get(i).update(wayPoints);
            if(listEnemy.get(i).isActive()) {
                //listEnemy.get(i).update(wayPoints);
                listEnemy.get(i).draw(batch, NormalEnemy);
            }
            else {
                listEnemy.remove(i);
            }
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
                if (MAP_SPRITES_1[i][j] == 1) {
                    batch.draw(road, j * 64, (11 - i ) * 64, 64, 64);
                } else if (MAP_SPRITES_1[i][j] == 0) {
                    batch.draw(grass, j * 64, (11 - i) * 64, 64, 64);
                }
            }
        }
    }

    private void drawIcon(SpriteBatch batch) {
        batch.draw(tower, 64*3,64,64,64);
        batch.draw(towerGun,64*3,64,64,64);
    }

}
