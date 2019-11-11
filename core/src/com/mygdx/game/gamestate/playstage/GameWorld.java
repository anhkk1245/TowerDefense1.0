package com.mygdx.game.gamestate.playstage;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.movable.NormalEnemy;
import com.mygdx.game.entities.tile.Tower;

public class GameWorld {
    private NormalEnemy normalEnemy;
    private Tower tower;

    public static final Vector2[] wayPoints = new Vector2[] {
            new Vector2(0*64,9*64 + 32),
            new Vector2(7*64 + 32, 9*64 + 32),
            new Vector2(7*64 + 32, 4*64 + 32),
            new Vector2(10*64 + 32, 4*64 + 32),
            new Vector2(10*64 + 32, 10*64 + 32),
            new Vector2(13*64 - 30, 10*64 + 32),
    };

    public static final int[][] MAP_SPRITES_1 = new int[][] {
            {0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,1,1},
            {1,1,1,1,1,1,1,1,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0},
    };

    public GameWorld() {
        //normalEnemy = new NormalEnemy(wayPoints[0].x,wayPoints[0].y);
        tower = new Tower(64*3, 64);
    }

    public void update(float delta) {
        //normalEnemy.update(wayPoints, 0);
        tower.update();
    }

    public NormalEnemy getNormalEnemy() {return normalEnemy;}

    public Tower getTower() {
        return tower;
    }
}
