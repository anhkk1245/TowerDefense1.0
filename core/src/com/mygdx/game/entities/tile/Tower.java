package com.mygdx.game.entities.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.movable.Bullet;
import com.mygdx.game.entities.movable.Enemy;
import com.mygdx.game.gamestate.playstage.GameWorld;

public abstract class Tower extends TileEntities {
    protected boolean readyToDrag = true;
    protected int id;
    protected int damage;
    protected int range;
    protected float angle;
    protected Enemy target = null;
    protected double time;
    protected double bulletPerSecond;

    protected boolean isSetting = true;

    public Tower(float x, float y) {
        super(x,y);
        this.angle = 0;
    }

    public Enemy getTarget() {
        double farestEnemy = 0;
        for (Enemy enemy : GameWorld.EnemyList) {
            double distance = Vector2.dst(this.getX(), this.getY(), enemy.getX(), enemy.getY());
            if (enemy.isActive() && distance < range && enemy.getDistanceTraveled() > farestEnemy) // cai getA la lấy ra quãng đuoèng mà ênmy đã đi được, mỗi lần ô rendẻ ênmy thì a += speed
            {
                this.target = enemy;
                farestEnemy = enemy.getDistanceTraveled();
            }
        }
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
                float ch = Vector2.dst(this.getX(), this.getY(), target.getX(), target.getY());
                float cgv = Math.abs(this.getY() - target.getY());
                float cos = cgv/ch;
                angle = Math.abs(Math.acos(cos));
                angle = (double)Math.round(angle*1000)/1000;
                angle = angle * 180/Math.PI;
                angle = (double)Math.round(angle*1000)/1000;
                if (target.getY() > this.getY())
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
                if (target.getY() > this.getY())
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

    public void drawGun(SpriteBatch batch, Sprite sprite) {
        float angleX = this.getAngle(this.getTarget()) - this.angle;

        sprite.setSize(64,64);
        sprite.setPosition(this.getX(), this.getY());
        sprite.rotate(angleX);
        this.angle = this.getAngle(getTarget());
        sprite.draw(batch);
    }

    public void update() {
        update(this.getX(), this.getY());
    }

    public void update(float x, float y) {
        this.position = new Vector2(x,y);
    }

    public void shot() {
        if (this.isActive()) {
            if (this.getTarget() != null) {
                //System.out.println(time);
                if (time >= (1/(bulletPerSecond))) { // BUG khi de bulletPerSecond la int thi ep kieu(double)(1/bulletPerSecond)van se ra 0
                    time = 0;
                    GameWorld.bulletList.add(new Bullet(this));
                }
                time += Gdx.graphics.getDeltaTime();// de bien timer o dang sau de khi co target se ban ngay luon chu k phai cho bai s sau ms ban d'

            }
            System.out.println(GameWorld.bulletList.size());
            for (Bullet bullet : GameWorld.bulletList) {
                if (bullet.isActive())
                {
                    bullet.getTargetPos();
                    bullet.update();//bug
                }
                //else { this.listBullet.remove(bullet); } // sao remove khoi list bi loi ?
                // vi k xoa bullet khoi list nen neu de lau se bi lag
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

    public void isDragged() {
        this.readyToDrag = false;
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
}
