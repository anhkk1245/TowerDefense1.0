package com.mygdx.game.entities.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SmallerEnemy extends Enemy {
    public SmallerEnemy(float x, float y) {
        super(x, y);
        super.setSpeed(7);
        super.setId(3);
        super.setHp(30);
        super.setMoney(10);
        super.setArmour(10);
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
