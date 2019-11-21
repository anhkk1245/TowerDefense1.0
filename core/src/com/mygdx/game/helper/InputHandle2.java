package com.mygdx.game.helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.gameoverstage.GameOverStage;
import com.mygdx.game.gamestate.playstage.GamePlayStage;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.gamestate.playstage2.GameWorld2;


public class InputHandle2 implements InputProcessor {
    MyGdxGame game;
    private GameWorld2 world;
    private int[][] MAP_SPRITE;
    private Box box;
    private int indexToDelete;

    private boolean justDragged = false;
    private boolean justChoose = false;
    private boolean devButton = false;

    public InputHandle2(GameWorld2 gameWorld, MyGdxGame game) {
        this.game = game;
        this.world = gameWorld;
        this.MAP_SPRITE = gameWorld.MAP_SPRITES_2;
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
            game.setScreen(new GameOverStage(game));
            GameWorld2.isActive = false;
            GameWorld2.resetWorld();
//            GameWorld.isActive = true;
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

        for (int i = GameWorld2.tower.size() - 1;i>=0;i--) {
            if(GameWorld2.tower.get(i).isContain(screenX, renderY)) {
                indexToDelete=i;
                this.box.setActive(true);
                this.box.setPosition(GameWorld2.tower.get(i).getX(), GameWorld2.tower.get(i).getY() );
            }
        }

        if (this.box.isContain(screenX, renderY, 96, 40)) {
            AssetLoader.click.play();
            GameWorld2.playerMoney += (GameWorld2.tower.get(indexToDelete).getPrice() / 3);
            AssetLoader.bonus.play();
            GameWorld2.tower.remove(indexToDelete);
            this.box.setActive(false);
        }

        for (int i=0;i<GameWorld2.iconBox.size();i++) {
            if(GameWorld2.iconBox.get(i).isContain(screenX,renderY,64,64)) {
                AssetLoader.click.play();
                if(GameWorld2.iconBox.get(i).getId() == 1) {
                    if(GameWorld2.playerMoney - 100 >= 0) {
                        AssetLoader.flash.play();
                        GameWorld2.plane.setActive(true);
                        GameWorld2.playerMoney -= 100;
                    }
                }
                else if(GameWorld2.iconBox.get(i).getId() == 2) {GameWorld2.save();}
                else {GameWorld2.load();}
            }
        }

        for(int i=0;i<GameWorld2.icon.size();i++){
            if(GameWorld2.icon.get(i).isContain(screenX, renderY)) {
                this.world.createTower(GameWorld2.icon.get(i).getId(), GameWorld2.icon.get(i).getX(),GameWorld2.icon.get(i).getY());
                justChoose = true;
                if(GameWorld2.tower.get(GameWorld2.tower.size()-1).isReadyToDrag())
                    GameWorld2.tower.get(GameWorld2.tower.size()-1).update(screenX,renderY);
            }
        }

        justDragged = false;
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if(justChoose) {
            if(!justDragged) {
                GameWorld2.tower.remove(GameWorld2.tower.size() - 1);
            }
        }

        if(justDragged) {
            if (screenX >= 0 && screenX <= 13 * 64 && screenY >= 0 && screenY <= 10 * 64) {
                if (MAP_SPRITE[GameWorld2.tower.get(GameWorld2.tower.size() - 1).getMapRowIndex(screenY)][GameWorld2.tower.get(GameWorld2.tower.size() - 1).getMapCollumnIndex(screenX)] == 0) {// them dieu kien no khong la duong
                    //dat thap vao trong o
                    int row = GameWorld2.tower.get(GameWorld2.tower.size() - 1).getMapRowIndex(Gdx.graphics.getHeight() - screenY);
                    int col = GameWorld2.tower.get(GameWorld2.tower.size() - 1).getMapRowIndex(screenX);
                    GameWorld2.tower.get(GameWorld2.tower.size() - 1).update(col * 64, row * 64);
                    GameWorld2.tower.get(GameWorld2.tower.size() - 1).isDragged();
                    AssetLoader.click.play();
                } else {
                    GameWorld2.tower.remove(GameWorld2.tower.size() - 1);
                }
            } else {
                GameWorld2.tower.remove(GameWorld2.tower.size() - 1);
            }
        }

        justDragged = false;
        justChoose = false;

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if( GameWorld2.tower.get(GameWorld2.tower.size()-1).isReadyToDrag()) {
            GameWorld2.tower.get(GameWorld2.tower.size()-1).update(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
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