package com.mygdx.game.entities.tile;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Tower extends TileEntities {
    public Tower(float x, float y) {
        super(x,y);
    }

    public void draw(SpriteBatch batch, Texture texture) {
        batch.draw(texture, this.position.x, this.position.y, 64,64);
    }
    public void update() {
        update(this.getX(), this.getY());
    }

    public void update(float x, float y) {
        this.position = new Vector2(x,y);
    }

    public boolean isActive() {
        return active;
    }

    public void deActive() {
        active = false;
    }

    public float getX() {
        return this.position.x;
    }

    public float getY() {
        return this.position.y;
    }

}
