package com.mygdx.game.entities.movable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class MovableEntities {
    protected Vector2 position;
    protected float speed;
    protected boolean active;

    public MovableEntities() {}

    public MovableEntities(float x, float y) {
        this.position = new Vector2(x,y);
        this.active = true;
    }

    public abstract void draw(SpriteBatch batch, Texture texture);

    public void deActive() {
        active = false;
    }

    public boolean isActive() {
        return this.active;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
