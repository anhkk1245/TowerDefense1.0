package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.entities.tile.NormalTower;
import com.mygdx.game.entities.tile.Tower;
import com.mygdx.game.gamestate.playstage.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class InputHandle implements InputProcessor {
    private List<Tower> towerList;
    private int[][] MAP_SPRITE;
    private Box box;

    private boolean justDragged = false;
    private boolean justTouched = false;

    public InputHandle(GameWorld gameWorld) {
        this.towerList = gameWorld.tower;
        this.MAP_SPRITE = gameWorld.MAP_SPRITES_1;
        this.box = gameWorld.box;
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

        // luu index cua phan tu muon xoa
        int index = 0;

        if(this.box.isContain(screenX, renderY)) {
            this.towerList.get(index).deActive();
            this.box.setActive(false);
        }

        for (int i = GameWorld.tower.size() - 1;i>=0;i--) {
//            if(i >= GameWorld.tower.size()) {
//                i=GameWorld.tower.size() - 1;
//                if(i == -1) break;
//            }
            if(this.towerList.get(i).isContain(screenX, renderY)) {
                index = i;
                this.box.setActive(true);
                this.box.setPosition(this.towerList.get(i).getX(), this.towerList.get(i).getY() );
            }
        }

        if(screenX >= 64*3 && screenX <= 4*64 && renderY >= 64 && renderY <= 2*64) {
            Tower newTower = new NormalTower(64*3, 64);
            towerList.add(newTower);
            justTouched = true;
            if(this.towerList.get(towerList.size() - 1).isReadyToDrag())
                this.towerList.get(towerList.size() - 1).update(screenX,renderY);
        }
        justDragged = false;
        return true;
    }

    //TO DO: Tha thap vao cac o moi
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(justTouched) {
            if(!justDragged) {
                //this.towerList.get(towerList.size() - 1).deActive();
                this.towerList.remove(towerList.size() - 1);
            }
        }

        if(justDragged) {
            if (screenX >= 0 && screenX <= 13 * 64 && screenY >= 0 && screenY <= 10 * 64) {
                if (MAP_SPRITE[this.towerList.get(towerList.size() - 1).getMapRowIndex(screenY)][this.towerList.get(towerList.size() - 1).getMapCollumnIndex(screenX)] == 0) {// them dieu kien no khong la duong
                    //dat thap vao trong o
                    int row = this.towerList.get(towerList.size() - 1).getMapRowIndex(Gdx.graphics.getHeight() - screenY);
                    int col = this.towerList.get(towerList.size() - 1).getMapRowIndex(screenX);
                    this.towerList.get(towerList.size() - 1).update(col * 64, row * 64);
                    this.towerList.get(towerList.size() - 1).isDragged();
                } else {
                    this.towerList.remove(towerList.size() - 1);
                }
            } else {
                //this.towerList.get(towerList.size() - 1).deActive();
                this.towerList.remove(towerList.size() - 1);
            }
        }

        justDragged = false;
        justTouched = false;

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if( this.towerList.get(towerList.size()-1).isReadyToDrag()) {
            this.towerList.get(towerList.size()-1).update(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        }
        justDragged = true;
        justTouched = false;
//        //this.tower.deActive();
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
