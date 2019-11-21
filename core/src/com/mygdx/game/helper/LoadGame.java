package com.mygdx.game.helper;

import com.mygdx.game.gamestate.playstage.GameRenderer;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.gamestate.playstage2.GameRenderer2;
import com.mygdx.game.gamestate.playstage2.GameWorld2;
import com.mygdx.game.gamestate.startstage.StartStage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadGame {
    public LoadGame(){}

    public static void load() {
        try {
                BufferedReader bf = new BufferedReader(new FileReader("save.txt"));
                StartStage.id = Integer.parseInt(bf.readLine());
                if(StartStage.id == 1) {
                    GameRenderer.timer = Float.parseFloat(bf.readLine());
                    GameWorld.escapedEnemy = Integer.parseInt(bf.readLine());
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
                        GameWorld.createEnemy(id, hp, countWayPoint, x, y, s[5], distanceTraveled);
                    }
                    int towerSize = Integer.parseInt(bf.readLine());
                    for (int i = 0; i < towerSize; i++) {
                        String[] s = bf.readLine().split(" ");
                        int id = Integer.parseInt(s[0]);
                        float x = Float.parseFloat(s[1]);
                        float y = Float.parseFloat(s[2]);
                        GameWorld.createTower(id, x, y);
                        GameWorld.tower.get(i).isDragged();
                    }
                    GameWorld.playerMoney = Integer.parseInt(bf.readLine());
                }
                else if(StartStage.id == 2) {
                    GameRenderer.timer = Float.parseFloat(bf.readLine());
                    GameWorld2.escapedEnemy = Integer.parseInt(bf.readLine());
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
                        GameWorld2.createEnemy(id, hp, countWayPoint, x, y, s[5], distanceTraveled);
                    }
                    int towerSize = Integer.parseInt(bf.readLine());
                    for (int i = 0; i < towerSize; i++) {
                        String[] s = bf.readLine().split(" ");
                        int id = Integer.parseInt(s[0]);
                        float x = Float.parseFloat(s[1]);
                        float y = Float.parseFloat(s[2]);
                        GameWorld2.createTower(id, x, y);
                        GameWorld2.tower.get(i).isDragged();
                    }
                    GameWorld2.playerMoney = Integer.parseInt(bf.readLine());
                }
                bf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setInfo() {
        try {
            BufferedReader bf = new BufferedReader(new FileReader("save.txt"));
            StartStage.id = Integer.parseInt(bf.readLine());
            StartStage.timer = Float.parseFloat(bf.readLine());
            bf.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


}
