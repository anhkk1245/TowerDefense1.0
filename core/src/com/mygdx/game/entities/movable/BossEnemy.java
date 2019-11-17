package com.mygdx.game.entities.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BossEnemy extends Enemy {
    public BossEnemy(float x, float y) {
        super(x, y);
        super.setSpeed(4);
        super.setId(4);
        super.setHp(90);
        super.setMoney(20);
        super.setArmour(30);
    }

    @Override
    public void draw(SpriteBatch batch, Texture texture) {
        batch.draw(texture, this.position.x -32 , this.position.y -32 , 64,64);
    }

    @Override
    public void update(Vector2[] wayPoints) {
        super.update(wayPoints);
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }
}
