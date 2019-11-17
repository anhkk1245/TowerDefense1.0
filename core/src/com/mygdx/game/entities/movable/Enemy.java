package com.mygdx.game.entities.movable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.gamestate.playstage.GameWorld;

public abstract class Enemy extends MovableEntities {
    protected String direction;
    protected int countWayPoint;
    protected int hp;
    protected int money;
    protected int armour;// ao giap
    protected int id;
    protected int distanceTraveled;

    public Enemy(float x, float y) {
        super(x, y);
        this.direction = "";
        this.countWayPoint = 0;
    }

    public void update(Vector2[] wayPoints) {
        if(this.position.x >= GameWorld.wayPoints[wayPoints.length-1].x) {
            this.deActive();
            this.countWayPoint = 0;
            return;
        }
        Vector2 currentWP = wayPoints[this.countWayPoint];
        if (Vector2.dst(this.position.x, this.position.y, currentWP.x, currentWP.y) <= this.speed) {
            this.position = new Vector2(currentWP.x, currentWP.y);
            Vector2 nextWayPoint = new Vector2();
            if(this.countWayPoint < GameWorld.wayPoints.length - 1) { 
                nextWayPoint = wayPoints[++this.countWayPoint];
            }
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
//        GameWorld.EnemyList.remove(this);
    }

    protected int hpProgress;
    public void createHealthBar(SpriteBatch batch, Texture healthBarBackground, Texture healthBar) {
        batch.draw(healthBarBackground, this.position.x - 10, this.position.y+35, 40, 5 );
        batch.draw(healthBar, this.position.x - 9, this.position.y + 36, 39, 5);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHpProgress() {
        return hpProgress;
    }

    public void setHpProgress(int hpProgress) {
        this.hpProgress = hpProgress;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public int getDistanceTraveled() {
        return this.distanceTraveled;
    }
}
