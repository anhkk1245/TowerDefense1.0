package com.mygdx.game.helper;

import com.badlogic.gdx.Screen;
import com.mygdx.game.gamestate.playstage.GameWorld;

public class Wave {
    GameWorld world;
    private String[] getWaveInfo;

    public static int waveNumber = 1;
    private int enemiesThisRound = 0;
    private int enemyIndex = 1;

    public boolean waveSpawning;

    public Wave(GameWorld world) {
        this.world = world;
        getWaveInfo = world.getWaveInfo();
    }
    public void nextWave() {
        this.enemiesThisRound = 0;
        this.waveSpawning = true;
        this.waveNumber++;
        this.world.resetEnemy();

    }

    private int currentDelay = 0;
    private int spawnRate = 10;

    public void spawnEnemies() {
            String[] getRoundInfo = this.getWaveInfo[this.waveNumber - 1].split(" ");
            if (this.enemiesThisRound < Integer.parseInt(getRoundInfo[0])) {
                if (currentDelay < spawnRate) {
                    currentDelay++;
                } else {
                    currentDelay = 0;
                    this.enemiesThisRound++;
                    this.world.createEnemy(Integer.parseInt(getRoundInfo[enemyIndex]));
                    enemyIndex++;
                }
            } else {
                this.waveSpawning = false;
                enemyIndex = 1;
            }
    }

}
