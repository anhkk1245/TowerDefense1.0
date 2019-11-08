package com.mygdx.game.gamestate.playstage;

import com.badlogic.gdx.math.Vector2;

public class GameWorld {

    public static final Vector2[] wayPoints = new Vector2[] {
            new Vector2(0*64,9*64 + 32),
            new Vector2(7*64 + 32, 9*64 + 32),
            new Vector2(7*64 + 32, 4*64 + 32),
            new Vector2(10*64 + 32, 4*64 + 32),
            new Vector2(10*64 + 32, 10*64 + 32),
            new Vector2(13*64, 10*64 + 32),
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

    public GameWorld() {}

    public void update(float delta) {

    }
}
