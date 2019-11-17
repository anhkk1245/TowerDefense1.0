package com.mygdx.game.gamestate.playstage;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.entities.movable.*;
import com.mygdx.game.entities.tile.Tower;
import com.mygdx.game.helper.Box;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameWorld {
    public static List<Enemy> EnemyList;
    public static List<Tower> tower;
    public static List<Bullet> bulletList;
    public Box box;

    public static final Vector2[] wayPoints = new Vector2[] {
            new Vector2(0*64,9*64 + 32),
            new Vector2(7*64 + 32, 9*64 + 32),
            new Vector2(7*64 + 32, 4*64 + 32),
            new Vector2(10*64 + 32, 4*64 + 32),
            new Vector2(10*64 + 32, 10*64 + 32),
            new Vector2(13*64 , 10*64 + 32),
    };

    public static final int[][] MAP_SPRITES_1 = new int[][] {
            {2,3,0,0,0,0,0,0,0,0,0,0,0},
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
        EnemyList = new ArrayList<>();
        tower = new ArrayList<>();
        bulletList = new ArrayList<>();
        box = new Box();
    }

    public void update(float delta) {
        //normalEnemy.update(wayPoints, 0);
    }


    public void createEnemy(int id) {
        if(id == 1) {
            EnemyList.add(new NormalEnemy(wayPoints[0].x, wayPoints[0].y));
        }
        else if(id == 2) {
            EnemyList.add(new TankerEnemy(wayPoints[0].x, wayPoints[0].y));
        }
        else if(id == 3) {
            EnemyList.add(new SmallerEnemy(wayPoints[0].x, wayPoints[0].y));
        }
        else if(id == 4) {
            EnemyList.add(new BossEnemy(wayPoints[0].x, wayPoints[0].y));
        }
    }

    public void resetEnemy() {
        EnemyList.clear();
    }

    public String[] getWaveInfo() {
        String[] mapInfo = new String[0];
        try {
            FileReader fr = new FileReader("play_state_1.txt");
            BufferedReader bf = new BufferedReader(new FileReader("play_state_1.txt"));
            int size = Integer.parseInt(bf.readLine());
            mapInfo = new String[size];
            for(int i=0;i<size;i++) {
                mapInfo[i] = bf.readLine();
            }
            fr.close();
            return mapInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapInfo;
    }

    //public Tower getTower() {return tower;}
}
