package com.mygdx.game.helper;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Box {
    private boolean active = false;
    private Vector2 position;
    public Box() {
        this.position = new Vector2(0,0);
    }

    public boolean isContain(float x, float y) {
        return (x >= this.position.x)&&(x <= this.position.x + 96)&&(this.position.y + 40 >= y) && (y >= this.position.y);
    }

    public void draw(SpriteBatch batch, Texture texture) {
        batch.draw(texture, this.position.x, this.position.y, 96, 40);
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


}
