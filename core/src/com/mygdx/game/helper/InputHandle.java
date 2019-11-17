package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.gamestate.playstage.GameWorld;


public class InputHandle implements InputProcessor {
    private GameWorld world;
    private int[][] MAP_SPRITE;
    private Box box;
    private int indexToDelete;

    private boolean justDragged = false;
    private boolean justTouched = false;

    public InputHandle(GameWorld gameWorld) {
        this.world = gameWorld;
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

        for (int i = GameWorld.tower.size() - 1;i>=0;i--) {
            if(GameWorld.tower.get(i).isContain(screenX, renderY)) {
                indexToDelete=i;
                this.box.setActive(true);
                this.box.setPosition(GameWorld.tower.get(i).getX(), GameWorld.tower.get(i).getY() );
            }
        }

        if(this.box.isContain(screenX, renderY)) {
            GameWorld.playerMoney += GameWorld.tower.get(indexToDelete).getPrice();
            GameWorld.tower.remove(indexToDelete);
            this.box.setActive(false);
        }

        for(int i=0;i<GameWorld.icon.size();i++){
            if(GameWorld.icon.get(i).isContain(screenX, renderY)) {
                this.world.createTower(GameWorld.icon.get(i).getId(), GameWorld.icon.get(i).getX(),GameWorld.icon.get(i).getY());
                justTouched = true;
                if(GameWorld.tower.get(GameWorld.tower.size()-1).isReadyToDrag())
                    GameWorld.tower.get(GameWorld.tower.size()-1).update(screenX,renderY);
            }
        }
        justDragged = false;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(justTouched) {
            if(!justDragged) {
                //this.towerList.get(towerList.size() - 1).deActive();
                GameWorld.tower.remove(GameWorld.tower.size() - 1);
            }
        }

        if(justDragged) {
            if (screenX >= 0 && screenX <= 13 * 64 && screenY >= 0 && screenY <= 10 * 64) {
                if (MAP_SPRITE[GameWorld.tower.get(GameWorld.tower.size() - 1).getMapRowIndex(screenY)][GameWorld.tower.get(GameWorld.tower.size() - 1).getMapCollumnIndex(screenX)] == 0) {// them dieu kien no khong la duong
                    //dat thap vao trong o
                    int row = GameWorld.tower.get(GameWorld.tower.size() - 1).getMapRowIndex(Gdx.graphics.getHeight() - screenY);
                    int col = GameWorld.tower.get(GameWorld.tower.size() - 1).getMapRowIndex(screenX);
                    GameWorld.tower.get(GameWorld.tower.size() - 1).update(col * 64, row * 64);
                    GameWorld.tower.get(GameWorld.tower.size() - 1).isDragged();
                } else {
                    GameWorld.tower.remove(GameWorld.tower.size() - 1);
                }
            } else {
                GameWorld.tower.remove(GameWorld.tower.size() - 1);
            }
        }

        justDragged = false;
        justTouched = false;

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if( GameWorld.tower.get(GameWorld.tower.size()-1).isReadyToDrag()) {
            GameWorld.tower.get(GameWorld.tower.size()-1).update(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        }
        justDragged = true;
        justTouched = false;
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
