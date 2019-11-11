package com.mygdx.game.entities.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemy extends MovableEntities {
    protected String direction;
    protected int countWayPoint;
    protected int hp;
    protected int money;
    protected int armour; // ao giap

    public Enemy(float x, float y, float speed) {
        super(x, y, speed);
        this.direction = "";
        this.countWayPoint = 0;
        this.hp = 0;
        this.money = 0;
        this.armour = 0;
    }


    public void update(Vector2[] wayPoints) {
        if(this.position.x >= wayPoints[wayPoints.length -1].x) {
            this.deActive();
            this.countWayPoint = 0;
        }
        Vector2 currentWP = wayPoints[this.countWayPoint];
        if (Vector2.dst(this.position.x, this.position.y, currentWP.x, currentWP.y) <= this.speed) {
            this.position = new Vector2(currentWP.x, currentWP.y);
            Vector2 nextWayPoint;
            if(this.countWayPoint < wayPoints.length - 1) {
                nextWayPoint = wayPoints[++this.countWayPoint];
            }
            else return;
            double deltaX = nextWayPoint.x - this.position.x;
            double deltaY = nextWayPoint.y - this.position.y;
            if (deltaX > this.speed) this.direction = "RIGHT";
            else if (deltaX < -this.speed) this.direction = "LEFT";
            else if (deltaY > this.speed) this.direction = "DOWN";
            else if (deltaY <= -this.speed) this.direction = "UP";
        }

        if(direction == "RIGHT") this.position.add(this.speed,0);
        else if(direction == "UP" ) this.position.add(0, -this.speed);
        else if(direction == "DOWN") this.position.add(0, this.speed);
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public void deActive() {
        super.deActive();
    }
}
