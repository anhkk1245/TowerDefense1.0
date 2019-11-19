package com.mygdx.game.entities.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.tile.Tower;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.helper.InforGame;

public class Bullet extends MovableEntities {
    private Enemy target = null;
    private int damage;
    private float maxDistance;
    private float angle;
    private int numFrame;
    private Vector2 targetPos;
    private Sprite img_bullet;
    private float distanceTraveled = 0;
    private Texture texture;


    private boolean KT = true;

    public Bullet(Tower tower, Texture texture) {
        super();
        this.texture = texture;
        this.active();
        this.damage = tower.getDamage();
        this.target = tower.TakeTarget();
        angle = tower.getAngle(target);
        this.position = new Vector2(tower.getX()+32, tower.getY()+32);
        numFrame =12;
        img_bullet = new Sprite(this.texture);
        img_bullet.rotate(angle);
    }

    @Override
    public void draw(SpriteBatch batch, Texture texture) {
    }
    public void draw(SpriteBatch batch)
    {
        img_bullet.setPosition(this.getX()-32, this.getY()-32);
        img_bullet.draw(batch);
    }

    public void setPosition(float x, float y) {

        this.position = new Vector2(x , y);
    }

    public void getTargetPos() {
        if(KT) {
            targetPos = new Vector2(target.getX()+32, target.getY()+32);
            this.maxDistance = Vector2.dst(this.getX(), this.getY(),this.target.getX()+32, this.target.getY()+32);
            float ch = Vector2.dst(this.getX(), this.getY(), this.target.getX()+32, this.target.getY()+32);
            float cgv = Math.abs(this.getY() - this.target.getY()-32);
            float cos = cgv/ch;
            this.angle = (float)Math.abs(Math.acos(cos));
            KT = false;
        }
        else return;
    }

    public void update() {

        if (this.getX() > InforGame.MAP_WIDTH || this.getX() < 0 || this.getY() > InforGame.MAP_HEIGHT || this.getY() <0)
        {
            this.deActive();
            return;
        }

        double len = maxDistance/numFrame;
        distanceTraveled += maxDistance/numFrame;

        if (this.getX() > this.targetPos.x)
        {
            if (this.getY()> this.targetPos.y)
            {
                this.setPosition((float)(this.getX()-len*Math.sin(angle)),(float)( this.getY() - len*Math.cos(angle)) );
            }
            else {
                this.setPosition((float)(this.getX()-len*Math.sin(angle)), (float)(this.getY() + len*Math.cos(angle)) );
            }
        }
        else{
            if (this.getY()> this.targetPos.y)
            {
                this.setPosition((float)(this.getX() + len*Math.sin(angle)), (float)(this.getY() - len*Math.cos(angle)));
            }
            else {
                this.setPosition((float)(this.getX() + len*Math.sin(angle)), (float)(this.getY() + len*Math.cos(angle)));
            }
        }
        if (distanceTraveled > maxDistance)
        {
            this.setPosition(target.getX()+32, target.getY()+32);
            this.deActive();
            return;
        }
    }

    @Override
    public void deActive() {
        super.deActive();
    }

    public void active() {
        this.active = true;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }
//    public Bullet reuse() {
//        Bullet reuse = new Bullet();
//        return reuse;
//    }

}
