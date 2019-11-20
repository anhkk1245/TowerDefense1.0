package com.mygdx.game.gamestate.playstage2;

import com.badlogic.gdx.math.FloatCounter;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.movable.*;
import com.mygdx.game.entities.tile.*;
import com.mygdx.game.gamestate.playstage.GameRenderer;
import com.mygdx.game.helper.Box;
import com.mygdx.game.helper.Wave;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameWorld2 {
    public static List<Enemy> EnemyList;
    public static List<Tower> tower;
    public static List<Tower> icon;
    public static List<Box> iconBox;
    public static int playerMoney = 100;
    public static int escapedEnemy = 0;
    public Box box;
    public static Box plane;

    public static boolean isActive = false;

    public static final Vector2[] wayPoints = new Vector2[]{
            new Vector2(0 * 64, 3 * 64),
            new Vector2(2*64, 3*64),
            new Vector2(2*64, 10 * 64),
            new Vector2(4*64, 10 * 64),
            new Vector2(4 * 64, 4 * 64),
            new Vector2(7 * 64, 4 * 64),
            new Vector2(7 * 64, 9 * 64),
            new Vector2(9 * 64, 9 * 64),
            new Vector2(9 * 64, 3 * 64),
            new Vector2(11 * 64, 3 * 64),
            new Vector2(11 * 64, 10 * 64),
            new Vector2(13 * 64, 10 * 64),
    };

    public static final int[][] MAP_SPRITES_2 = new int[][]{
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };

    public GameWorld2() {
        EnemyList = new ArrayList<>();
        tower = new ArrayList<>();
        icon = new ArrayList<>();
        iconBox = new ArrayList<>();
        iconBox.add(new Box(64 * 11, 64, 1));
        iconBox.add(new Box(64 * 14, 64, 2));
        iconBox.add(new Box(64 * 14, 64 * 3, 3));
        icon.add(new NormalTower(64 * 2, 64));
        icon.add(new SniperTower(64 * 5, 64));
        icon.add(new MachineGunTower(64 * 8, 64));
        box = new Box();
        plane = new Box(12 * 64, 64 * 2);
    }

    public void update(float delta) {
        //normalEnemy.update(wayPoints, 0);
    }


    public void createEnemy(int id) {
        if (id == 1) {
            EnemyList.add(new NormalEnemy(wayPoints[0].x, wayPoints[0].y));
        } else if (id == 2) {
            EnemyList.add(new TankerEnemy(wayPoints[0].x, wayPoints[0].y));
        } else if (id == 3) {
            EnemyList.add(new SmallerEnemy(wayPoints[0].x, wayPoints[0].y));
        } else if (id == 4) {
            EnemyList.add(new BossEnemy(wayPoints[0].x, wayPoints[0].y));
        }
    }

    public static void createEnemy(int id, int hp, int countWayPoint, float x, float y, String direction, int distanceTraveled) {
        if (id == 1) {
            EnemyList.add(new NormalEnemy(hp, countWayPoint, x, y, direction, distanceTraveled));
        } else if (id == 2) {
            EnemyList.add(new TankerEnemy(hp, countWayPoint, x, y, direction, distanceTraveled));
        } else if (id == 3) {
            EnemyList.add(new SmallerEnemy(hp, countWayPoint, x, y, direction, distanceTraveled));
        } else if (id == 4) {
            EnemyList.add(new BossEnemy(hp, countWayPoint, x, y, direction, distanceTraveled));
        }
    }

    public static void createTower(int id, float x, float y) {
        if (id == 1) tower.add(new NormalTower(x, y));
        else if (id == 2) tower.add(new SniperTower(x, y));
        else if (id == 3) tower.add(new MachineGunTower(x, y));
    }

    public static void resetEnemy() {
        EnemyList.clear();
    }

    public String[] getWaveInfo() {
        String[] mapInfo = new String[0];
        try {
            FileReader fr = new FileReader("play_state_2.txt");
            BufferedReader bf = new BufferedReader(new FileReader("play_state_2.txt"));
            int size = Integer.parseInt(bf.readLine());
            mapInfo = new String[size];
            for (int i = 0; i < size; i++) {
                mapInfo[i] = bf.readLine();
            }
            fr.close();
            return mapInfo;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapInfo;
    }

    public static void save() {
        try {
            FileWriter writer = new FileWriter("save.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(GameRenderer2.timer + "\n");
            bufferedWriter.write(escapedEnemy + "\n");
            bufferedWriter.write(Wave.waveNumber + "\n");
            bufferedWriter.write(EnemyList.size() + "\n");
            for (int i = 0; i < EnemyList.size(); i++) {
                bufferedWriter.write(EnemyList.get(i).getId() + " " + EnemyList.get(i).getHp() + " " + EnemyList.get(i).countWayPoint + " " + EnemyList.get(i).getX() + " " + EnemyList.get(i).getY() + " " + EnemyList.get(i).getDirection() + " " + EnemyList.get(i).getDistanceTraveled() + "\n");
            }
            bufferedWriter.write(tower.size() + "\n");
            for (int i = 0; i < tower.size(); i++) {
                bufferedWriter.write(tower.get(i).getId() + " " + tower.get(i).getX() + " " + tower.get(i).getY() + "\n");
            }
            bufferedWriter.write(playerMoney + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("save.txt"));
            tower.clear();
            EnemyList.clear();
            GameRenderer2.timer = Float.parseFloat(bf.readLine());
            escapedEnemy = Integer.parseInt(bf.readLine());
            Wave.waveNumber = Integer.parseInt(bf.readLine());
            int EnemyLiseSize = Integer.parseInt(bf.readLine());
            for (int i = 0; i < EnemyLiseSize; i++) {
                String[] s = bf.readLine().split(" ");
                int id = Integer.parseInt(s[0]);
                int hp = Integer.parseInt(s[1]);
                int countWayPoint = Integer.parseInt(s[2]) - 1;
                float x = Float.parseFloat(s[3]);
                float y = Float.parseFloat(s[4]);
                int distanceTraveled = Integer.parseInt(s[6]);
                createEnemy(id, hp, countWayPoint, x, y, s[5], distanceTraveled);
            }
            int towerSize = Integer.parseInt(bf.readLine());
            for (int i = 0; i < towerSize; i++) {
                String[] s = bf.readLine().split(" ");
                int id = Integer.parseInt(s[0]);
                float x = Float.parseFloat(s[1]);
                float y = Float.parseFloat(s[2]);
                createTower(id, x, y);
                tower.get(i).isDragged();
            }
            playerMoney = Integer.parseInt(bf.readLine());
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetWorld() {
        tower.clear();
        resetEnemy();
        iconBox.clear();
        icon.clear();
        Wave.resetWave();
    }
}
