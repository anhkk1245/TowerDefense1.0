package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.gamestate.playstage.GameWorld;

public class Box {
    private boolean active = false;
    private Vector2 position;
    private float speed = 9;
    private int id;
    public Box() {
        this.position = new Vector2(0,0);
    }

    public Box(float x, float y) {
        this.position = new Vector2(x,y);
    }

    public Box(float x, float y, int id) {
        this.position = new Vector2(x,y);
        this.setId(id);
    }

    public boolean isContain(float x, float y, float width, float height) {
        return (x >= this.position.x)&&(x <= this.position.x + width)&&(this.position.y + height >= y) && (y >= this.position.y);
    }

    public void update() {
        if(this.position.x >=0) {
           this.position.add(-this.speed, 0);
        }
        else {
            this.setActive(false);
        }
    }

    public void draw(SpriteBatch batch, Texture texture, float width, float height) {
        batch.draw(texture, this.position.x, this.position.y, width, height);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean value) {
        active = value;
    }

    public void setPosition(float x, float y) {
        if(x == 64*12) this.position = new Vector2(x-64, y+64);
        else if(y == 64*11) this.position = new Vector2(x -70 , y-40);
        else this.position = new Vector2(x+64, y+64);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

    public void setPlanePosition(float x, float y) {
        this.position = new Vector2(x,y);
    }
}
