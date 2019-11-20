package com.mygdx.game.entities.tile;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.game.helper.AssetLoader;
import com.mygdx.game.helper.InforGame;

public class NormalTower extends Tower {
    public NormalTower(float x, float y) {
        super(x, y);
        setId(1);
        setPrice(30);
        this.range = InforGame.NOR_RANGE;
        this.damage = InforGame.NOR_DAMAGE;
        this.bulletPerSecond = InforGame.NOR_BULLET_PER_SECOND;
        this.time = 1/bulletPerSecond;
        setSprite(new Sprite(AssetLoader.normalTowerGun));
    }
}
