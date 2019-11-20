package com.mygdx.game.entities.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.movable.*;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.gamestate.playstage2.GameWorld2;
import com.mygdx.game.helper.AssetLoader;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower extends TileEntities {
    protected boolean readyToDrag = true;
    protected boolean planted;
    protected int id;
    protected int damage;
    protected int range;
    protected float angle;
    protected Enemy target = null;
    protected double time;
    protected double bulletPerSecond;
    protected int price;
    protected Sprite sprite;
    protected List<Bullet> listBullet;
    //ShapeRenderer shapeRenderer;

    protected boolean isSetting = true;

    public Tower(float x, float y) {
        super(x,y);
        this.angle = 0;
        planted = false;
        listBullet = new ArrayList<>();
    }
    public Enemy TakeTarget()
    {
        return this.target;
    }
    public float TakeAngle(){return this.angle;}

    public Enemy getTarget() {
        Enemy Target = null;
        double farestEnemy = 0;
        if(GameWorld.isActive) {
            for (Enemy enemy : GameWorld.EnemyList) {
                double distance = Vector2.dst(this.getX() + 32, this.getY() + 32, enemy.getX() + 32, enemy.getY() + 32);
                if (enemy.isActive() && distance < range && enemy.getDistanceTraveled() > farestEnemy) {
                    Target = enemy;
                    farestEnemy = enemy.getDistanceTraveled();
                }
            }
        }
        else if(GameWorld2.isActive) {
            for (Enemy enemy : GameWorld2.EnemyList) {
                double distance = Vector2.dst(this.getX() + 32, this.getY() + 32, enemy.getX() + 32, enemy.getY() + 32);
                if (enemy.isActive() && distance < range && enemy.getDistanceTraveled() > farestEnemy) {
                    Target = enemy;
                    farestEnemy = enemy.getDistanceTraveled();
                }
            }
        }
        this.target = Target;
        return this.target;
    }

    public float getAngle(Enemy target)
    {
        double angle = this.angle;
        if(target == null)
        {
            return (float)angle;
        }
        else{
            if(this.getX() > target.getX())
            {
                float ch = Vector2.dst(this.getX()+32, this.getY()+32, target.getX()+32, target.getY()+32);
                float cgv = Math.abs(this.getY() - target.getY());
                float cos = cgv/ch;
                angle = Math.abs(Math.acos(cos));
                angle = (double)Math.round(angle*1000)/1000;
                angle = angle * 180/Math.PI;
                angle = (double)Math.round(angle*1000)/1000;
                if (target.getY()+32 > this.getY()+32)
                {
                    return (float)(angle);
                }
                else return (float)(180-angle);
            }
            else{
                float ch = Vector2.dst(this.getX(), this.getY(), target.getX(), target.getY());
                float cgv = Math.abs(this.getY() - target.getY());
                float cos = cgv/ch;
                angle = Math.abs(Math.acos(cos));
                angle = (double)Math.round(angle*1000)/1000;
                angle = angle * 180/Math.PI;
                angle = (double)Math.round(angle*1000)/1000;
                if (target.getY()+32 > this.getY()+32)
                {
                    return (float)(-angle);
                }
                else return (float)(angle -180);
            }
        }
    }

    public void draw(SpriteBatch batch, Texture texture) {
        batch.draw(texture, this.position.x, this.position.y, 64,64);
    }

    public void drawGun(SpriteBatch batch) {
        if (this.isPlanted())
        {
            float angleX = this.getAngle(this.getTarget()) - this.angle;
            this.sprite.rotate(angleX);
            this.angle = this.getAngle(getTarget());
        }
        this.sprite.setPosition(this.getX(), this.getY());
        this.sprite.draw(batch);
    }

    public void update() {
        update(this.getX(), this.getY());
    }

    public void update(float x, float y) {
        this.position = new Vector2(x,y);
    }

    public void shot(SpriteBatch batch) {
        if (this.isActive()) {
            if (this.getTarget() != null) {
                if (time >= (1/(bulletPerSecond))) {
                    time = 0;
                    if(this.getId() ==1)
                    {
                        listBullet.add(new Bullet(this, AssetLoader.normalBullet));
                    }
                    else if(this.getId() ==2)
                    {
                        listBullet.add(new Bullet(this, AssetLoader.sniperBullet));
                    }
                    else
                    {
                        listBullet.add(new Bullet(this, AssetLoader.machineBullet));
                    }
                }
                time += Gdx.graphics.getDeltaTime();
                /*
                shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
                shapeRenderer.setColor(Color.RED);
                shapeRenderer.line(this.getX(), this.getY(), target.getX(), target.getY());
                shapeRenderer.end();*/

            }
            for (Bullet bullet : this.listBullet) {
                if (bullet.isActive())
                {
                    bullet.getTargetPos();
                    bullet.update();
                    bullet.draw(batch);
                    AssetLoader.shoot.play();

                }
            }
            for (int i=0; i< this.listBullet.size()-1; i++)
            {
                if (!this.listBullet.get(i).isActive()) {
                    this.listBullet.remove(i);
                    i--;
                }
            }
        }
    }

    public boolean isActive() {
        return active;
    }

    public void deActive() {
        active = false;
    }

    // method make use in InputHandle
    public boolean isReadyToDrag() {
        return readyToDrag;
    }

    public boolean isPlanted() {
        return this.planted;
    }

    public void isDragged() {
        if(GameWorld.isActive) {
            if (GameWorld.playerMoney - this.price >= 0) {
                this.planted = true;
                this.readyToDrag = false;
                GameWorld.playerMoney -= this.price;
            } else {
                AssetLoader.notEnoughMoney.play();
                GameWorld.tower.remove(GameWorld.tower.size() - 1);
            }
        }
        else if(GameWorld2.isActive) {
            if (GameWorld2.playerMoney - this.price >= 0) {
                this.planted = true;
                this.readyToDrag = false;
                GameWorld2.playerMoney -= this.price;
            } else GameWorld2.tower.remove(GameWorld2.tower.size() - 1);
        }
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public int getMapRowIndex(int screenY) {
        return screenY/64;
    }

    public int getMapCollumnIndex(int screenX) {
        return screenX/64;
    }

    public boolean isContain(float x, float y) {
        return (x >= this.position.x)&&(x <= this.position.x + 64)&&(y >= this.position.y)&&(y <= this.position.y +64);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setTarget(Enemy target) {
        this.target = target;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
}
