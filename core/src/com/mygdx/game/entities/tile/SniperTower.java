package com.mygdx.game.entities.tile;

import com.mygdx.game.helper.InforGame;

public class SniperTower extends Tower{
    public SniperTower(float x, float y) {
        super(x, y);
        setId(2);
        this.range = InforGame.SNIPER_RANGE;
        this.damage = InforGame.SNIPER_DAMAGE;
        this.bulletPerSecond = InforGame.SNIPER_BULLET_PER_SECOND;
        this.time =1/bulletPerSecond;
    }
}
