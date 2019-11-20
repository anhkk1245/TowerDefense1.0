package com.mygdx.game.entities.tile;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.helper.AssetLoader;
import com.mygdx.game.helper.InforGame;

public class MachineGunTower extends Tower {
    public MachineGunTower(float x, float y) {
        super(x, y);
        setId(3);
        setPrice(35);
        this.range = InforGame.MACHINE_GUN_RANGE;
        this.damage = InforGame.MACHINE_GUN_DAMAGE;
        this.bulletPerSecond = InforGame.MACHINE_GUN_BULLET_PER_SECOND;
        this.time =1/bulletPerSecond;
        setSprite(new Sprite(AssetLoader.machineTowerGun));
    }
}
