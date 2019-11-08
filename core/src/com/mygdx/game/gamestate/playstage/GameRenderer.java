package com.mygdx.game.gamestate.playstage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.helper.AssetLoader;

public class GameRenderer {
    private GameWorld world;
    Texture grass;
    Texture road;
    int[][] MAP_SPRITES_1 ;
    Vector2[] wayPoints;
    ShapeRenderer shapeRenderer;

    SpriteBatch batch;


    public GameRenderer(GameWorld world) {
        this.world = world;
        MAP_SPRITES_1 = world.MAP_SPRITES_1;
        wayPoints = world.wayPoints;
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        initAsset();
    }

    public void initAsset() {
        grass = AssetLoader.grass;
        road = AssetLoader.road;
    }

    public void render() {
        drawMap();
        drawWayPoints();
    }

    private void drawWayPoints() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);
        for(int i = 0;i<wayPoints.length;i++) {
            shapeRenderer.circle(wayPoints[i].x,wayPoints[i].y,3);
        }
        shapeRenderer.end();
    }

    private void drawMap() {
        batch.begin();
        for(int i = 0; i < 10;i++) {
            for (int j = 0; j < 13; j++) {
                if (MAP_SPRITES_1[i][j] == 1) {
                    batch.draw(road, j * 64, (11 - i ) * 64, 64, 64);
                } else if (MAP_SPRITES_1[i][j] == 0) {
                    batch.draw(grass, j * 64, (11 - i) * 64, 64, 64);
                }
            }
        }
        batch.end();
    }

}
