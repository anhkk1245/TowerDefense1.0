package com.mygdx.game.entities.movable;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.gamestate.playstage2.GameWorld2;
import com.mygdx.game.helper.AssetLoader;

public abstract class Enemy extends MovableEntities {
    protected String direction = "";
    public int countWayPoint;
    protected int hp;
    protected int money;
    protected int armour;// ao giap
    protected int id;
    protected int distanceTraveled;
    protected Vector2 virtualPoint;

    protected boolean justLoad = false;

    public Enemy(float x, float y) {
        super(x, y);
        this.countWayPoint = 0;
    }

    // use for save function
    public Enemy(int hp, int countWayPoint, float x, float y, String direction, int distanceTraveled) {
        super(x,y);
        this.countWayPoint = countWayPoint;
        this.direction = "";
        this.distanceTraveled = distanceTraveled;
        this.justLoad = true;
        this.virtualPoint = new Vector2(x,y);
        setHp(hp);
    }

    public void update(Vector2[] wayPoints) {
        if(this.position.x + 36 >= GameWorld.wayPoints[wayPoints.length-1].x) {
            this.deActive();
            this.countWayPoint = 0;
            GameWorld.escapedEnemy ++;
            return;
        }
        if(!this.justLoad) {
            Vector2 currentWP = wayPoints[this.countWayPoint];
            if (Vector2.dst(this.position.x, this.position.y, currentWP.x, currentWP.y) <= this.speed) {
                this.position = new Vector2(currentWP.x, currentWP.y);
                Vector2 nextWayPoint = new Vector2();
                if (this.countWayPoint < GameWorld.wayPoints.length - 1) {
                    nextWayPoint = wayPoints[++this.countWayPoint];
                }
                double deltaX = nextWayPoint.x - this.position.x;
                double deltaY = nextWayPoint.y - this.position.y;
                if (deltaX > this.speed) this.direction = "RIGHT";
                else if (deltaX < -this.speed) this.direction = "LEFT";
                else if (deltaY > this.speed) this.direction = "UP";
                else if (deltaY <= -this.speed) this.direction = "DOWN";
            }
        }
        else {
            if (Vector2.dst(this.position.x, this.position.y, this.virtualPoint.x, this.virtualPoint.y) <= 1) {
                this.position = new Vector2(this.virtualPoint.x, this.virtualPoint.y);
                Vector2 nextWayPoint = new Vector2();
                if (this.countWayPoint < GameWorld.wayPoints.length - 1) {
                    nextWayPoint = wayPoints[++this.countWayPoint];
                }
                double deltaX = nextWayPoint.x - this.position.x;
                double deltaY = nextWayPoint.y - this.position.y;
                if (deltaX > this.speed) this.direction = "RIGHT";
                else if (deltaX < -this.speed) this.direction = "LEFT";
                else if (deltaY > this.speed) this.direction = "UP";
                else if (deltaY <= -this.speed) this.direction = "DOWN";
                this.justLoad = false;
            }
        }
    }

    public void findPath(Vector2[] wayPoints) {
        this.update(wayPoints);
        if(direction == "RIGHT") this.position.add(this.speed,0);
        else if(direction == "DOWN" ) this.position.add(0, -this.speed);
        else if(direction == "UP") this.position.add(0, this.speed);
    }

    @Override
    public boolean isActive() {
        return super.isActive();
    }

    @Override
    public void deActive() {
        super.deActive();
    }

    protected int hpProgress;

    public void createHealthBar(SpriteBatch batch, Texture healthBarBackground, Texture healthBar) {
        batch.draw(healthBarBackground, this.position.x + 20, this.position.y+60, 40, 5 );
        batch.draw(healthBar, this.position.x + 20, this.position.y + 60, getHpProgress(), 5);
    }

    public void takeDamage(int damage) {
        hpProgress -=  damage;
        if(hpProgress <= 0) {
            if(GameWorld.isActive) {
                GameWorld.playerMoney += this.money;
            }
            else if(GameWorld2.isActive) {
                GameWorld2.playerMoney += this.money;
            }
            AssetLoader.bonus.play();
            this.deActive();
        }
    }

    @Override
    public void draw(SpriteBatch batch, Texture texture) {
        batch.draw(texture, this.position.x  , this.position.y  , 64,64);
        distanceTraveled += this.speed;
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

    public float getHpProgress() {
        float progress = (float) (40 *( (1.0 * this.hpProgress) / (1.0 * (this.hp + this.armour) ) ));
        return progress;
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

    public String getDirection() {
        return direction;
    }
}
