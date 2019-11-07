package com.mygdx.game.gamestate.startstage;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.playstage.GamePlayStage;

public class StartStage extends ScreenAdapter {
    MyGdxGame game;

    public StartStage(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show(){
        // choose SPACE to move to next stage
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GamePlayStage(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        //display green screen
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }
}
