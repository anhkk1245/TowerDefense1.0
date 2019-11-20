package com.mygdx.game.helper;

import com.badlogic.gdx.Screen;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.gamestate.playstage2.GameWorld2;

public class Wave {
    GameWorld world;
    GameWorld2 world2;
    private String[] getWaveInfo;

    public static int waveNumber = 1;
    private static int enemiesThisRound = 0;
    private static int enemyIndex = 1;

    public boolean waveSpawning;

    public Wave(GameWorld world) {
        this.world = world;
        getWaveInfo = world.getWaveInfo();
    }

    public Wave(GameWorld2 world) {
        this.world2 = world;
        getWaveInfo = world.getWaveInfo();
    }

    public void nextWave() {
        this.enemiesThisRound = 0;
        this.waveSpawning = true;
        this.waveNumber++;
        if(GameWorld.isActive) {
            this.world.resetEnemy();
        }
        else if(GameWorld2.isActive) {
            this.world2.resetEnemy();
        }

    }

    private static int currentDelay = 0;
    private int spawnRate = 15;

    public void spawnEnemies() {
            String[] getRoundInfo = this.getWaveInfo[this.waveNumber - 1].split(" ");
            if (this.enemiesThisRound < Integer.parseInt(getRoundInfo[0])) {
                if (currentDelay < spawnRate) {
                    currentDelay++;
                } else {
                    currentDelay = 0;
                    this.enemiesThisRound++;
                    if(GameWorld.isActive) {
                        this.world.createEnemy(Integer.parseInt(getRoundInfo[enemyIndex]));
                    }
                    else if(GameWorld2.isActive) {
                        this.world2.createEnemy(Integer.parseInt(getRoundInfo[enemyIndex]));
                    }
                    enemyIndex++;
                }
            } else {
                this.waveSpawning = false;
                enemyIndex = 1;
            }
    }

    public static void resetWave() {
        waveNumber = 1;
        enemiesThisRound = 0;
        enemyIndex = 1;
        currentDelay = 0;
    }

}
