package com.mygdx.game.entities.tile;


import com.badlogic.gdx.math.Vector2;

public abstract class TileEntities  {
    protected Vector2 position;
    protected boolean active = true;

    public TileEntities(float x, float y) {
        this.position = new Vector2(x,y);
    }

}
