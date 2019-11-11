package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.entities.tile.Tower;
import com.mygdx.game.gamestate.playstage.GamePlayStage;
import org.graalvm.compiler.graph.Node;

public class InputHandle implements InputProcessor {
    private Tower tower;

    public InputHandle(Tower tower) {
        this.tower = tower;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int renderY = Gdx.graphics.getHeight() - screenY;
        if(Vector2.dst(64*3 + 32,64 + 32,screenX, renderY) < 60) {
            this.tower.update(screenX,renderY);
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        this.tower.deActive();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        int renderY = Gdx.graphics.getHeight() - screenY;
        if(this.tower.isActive()) {
            this.tower.update(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        }
        //this.tower.deActive();
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
