package com.mygdx.game.entities.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BossEnemy extends Enemy {
    public BossEnemy(float x, float y) {
        super(x, y);
        super.setSpeed(2);
        super.setId(4);
        super.setHp(90);
        super.setMoney(20);
        super.setArmour(30);
    }

    public BossEnemy(int hp, int countWayPoint, float x, float y, String direction, int distanceTraveled) {
        super(hp, countWayPoint, x, y, direction, distanceTraveled);
        super.setId(4);
        super.setMoney(20);
        super.setArmour(30);
        super.setSpeed(4);
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
