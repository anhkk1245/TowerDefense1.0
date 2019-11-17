package com.mygdx.game.entities.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.tile.Tower;
import com.mygdx.game.gamestate.playstage.GameWorld;

public class Bullet extends MovableEntities {
    private Enemy target = null;
    private int damage;
    private float maxDistance;
    private float angle;
    private int numFrame = 12;
    private Vector2 targetPos;

    private boolean KT = true;

    public Bullet(Tower tower) {
        super();
        this.active();
        this.damage = tower.getDamage();
        target = tower.getTarget();
        this.position = new Vector2(tower.getX(), tower.getY());
    }

    @Override
    public void draw(SpriteBatch batch, Texture texture) {
        batch.draw(texture, this.position.x,this.position.y,21,21);
    }

    public void setPosition(float x, float y) {
        // set position at center of tower
        this.position = new Vector2(x + 32, y + 32);
    }

    public void getTargetPos() {
        if(KT) {
            targetPos = new Vector2(target.getX(), target.getY());
            this.maxDistance = Vector2.dst(this.getX(), this.getY(),this.target.getX(), this.target.getY());
            float ch = Vector2.dst(this.getX(), this.getY(), this.target.getX(), this.target.getY());
            float cgv = Math.abs(this.getY() - this.target.getY());
            float cos = cgv/ch;
            this.angle = (float)Math.abs(Math.acos(cos));
            KT = false;
        }
        else return;
    }

    private float distanceTraveled = 0;

    public void update() {
        //update vi tri
        double len = maxDistance/numFrame;
        distanceTraveled += maxDistance/numFrame;
        if(distanceTraveled > maxDistance) {
            this.deActive();
            return;
        }
        if (this.getX() > this.targetPos.x) // X là tọa độ X của ênmy tại thời điểm xác định target, lúc này enemy đã đi dc thêm 1 đoạn nhỏ nữa r
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

    }

    @Override
    public void deActive() {
        super.deActive();
        GameWorld.bulletList.remove(this);
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
