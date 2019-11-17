package com.mygdx.game.entities.tile;

import com.mygdx.game.helper.InforGame;

public class MachineGunTower extends Tower {
    public MachineGunTower(float x, float y) {
        super(x, y);
        setId(3);
        setPrice(40);
        this.range = InforGame.MACHINE_GUN_RANGE;
        this.damage = InforGame.MACHINE_GUN_DAMAGE;
        this.bulletPerSecond = InforGame.MACHINE_GUN_BULLET_PER_SECOND;
        this.time =1/bulletPerSecond;
    }
}
