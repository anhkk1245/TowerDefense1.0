package com.mygdx.game.entities.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TankerEnemy extends Enemy {
    public TankerEnemy(float x, float y) {
        super(x, y);
        super.setSpeed(2);
        super.setId(2);
        super.setHp(150);
        super.setMoney(10);
        super.setArmour(20);
        super.setHpProgress(this.hp + this.armour);
    }
    public TankerEnemy(int hp, int countWayPoint, float x, float y, String direction, int distanceTraveled){
        super(hp, countWayPoint, x, y, direction, distanceTraveled);
        super.setId(2);
        super.setMoney(10);
        super.setArmour(20);
        super.setSpeed(3);
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
