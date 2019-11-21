package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.gameoverstage.GameOverStage;
import com.mygdx.game.gamestate.playstage.GamePlayStage;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.gamestate.playstage2.GamePlayStage2;
import com.mygdx.game.gamestate.playstage2.GameWorld2;


public class InputHandle implements InputProcessor {
    MyGdxGame game;
    private GameWorld world;
    private int[][] MAP_SPRITE;
    private Box box;
    private int indexToDelete;

    private boolean justDragged = false;
    private boolean justChoose = false;
    private boolean devButton = false;

    public InputHandle(GameWorld gameWorld, MyGdxGame game) {
        this.game = game;
        this.world = gameWorld;
        this.MAP_SPRITE = gameWorld.MAP_SPRITES_1;
        this.box = gameWorld.box;
    }

    @Override
    public boolean keyDown(int keycode) {
        // switch instantly to next stage
        if (keycode == Input.Keys.SPACE) {
            devButton = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(devButton) {
            AssetLoader.inGame.stop();
            game.setScreen(new GamePlayStage2(game));
            GameWorld2.isActive = true;
            GameWorld.resetWorld();
            GameWorld.isActive = false;
        }
        return true;
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

        if (this.box.isContain(screenX, renderY, 96, 40)) {
            AssetLoader.click.play();
            GameWorld.playerMoney += (GameWorld.tower.get(indexToDelete).getPrice() / 3);
            AssetLoader.bonus.play();
            GameWorld.tower.remove(indexToDelete);
            this.box.setActive(false);
        }

        for (int i=0;i<GameWorld.iconBox.size();i++) {
            if(GameWorld.iconBox.get(i).isContain(screenX,renderY,64,64)) {
                AssetLoader.click.play();
                if(GameWorld.iconBox.get(i).getId() == 1) {
                    if(GameWorld.playerMoney - 100 >= 0) {
                        AssetLoader.flash.play();
                        GameWorld.plane.setActive(true);
                        GameWorld.playerMoney -= 100;
                    }
                }
                else if(GameWorld.iconBox.get(i).getId() == 2) {GameWorld.save();}
                else {GameWorld.load();}
            }
        }

        for(int i=0;i<GameWorld.icon.size();i++){
            if(GameWorld.icon.get(i).isContain(screenX, renderY)) {
                this.world.createTower(GameWorld.icon.get(i).getId(), GameWorld.icon.get(i).getX(),GameWorld.icon.get(i).getY());
                justChoose = true;
                if(GameWorld.tower.get(GameWorld.tower.size()-1).isReadyToDrag())
                    GameWorld.tower.get(GameWorld.tower.size()-1).update(screenX,renderY);
            }
        }

        justDragged = false;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(justChoose) {
            if(!justDragged) {
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
                    AssetLoader.click.play();
                } else {
                    GameWorld.tower.remove(GameWorld.tower.size() - 1);
                }
            } else {
                GameWorld.tower.remove(GameWorld.tower.size() - 1);
            }
        }

        justDragged = false;
        justChoose = false;

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if( GameWorld.tower.get(GameWorld.tower.size()-1).isReadyToDrag()) {
            GameWorld.tower.get(GameWorld.tower.size()-1).update(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            justDragged = true;
        }
        justChoose = false;
        return false;
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
