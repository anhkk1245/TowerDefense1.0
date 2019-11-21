package com.mygdx.game.entities.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SmallerEnemy extends Enemy {
    public SmallerEnemy(float x, float y) {
        super(x, y);
        super.setSpeed(4);
        super.setId(3);
        super.setHp(150);
        super.setMoney(10);
        super.setArmour(10);
        super.setHpProgress(this.hp + this.armour);
    }
    public SmallerEnemy(int hp, int countWayPoint, float x, float y, String direction, int distanceTraveled){
        super(hp, countWayPoint, x, y, direction, distanceTraveled);
        super.setId(3);
        super.setMoney(10);
        super.setArmour(10);
        super.setSpeed(7);
        super.setHpProgress(this.hp + this.armour);
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
