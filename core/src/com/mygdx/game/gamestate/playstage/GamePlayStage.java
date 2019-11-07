package com.mygdx.game.gamestate.playstage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.gameoverstage.GameOverStage;
import com.mygdx.game.gamestate.startstage.StartStage;

public class GamePlayStage extends ScreenAdapter {
    MyGdxGame game;

    public GamePlayStage(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        // choose SPACE to move to next stage
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {

                if (keyCode == Input.Keys.SPACE) {
                    game.setScreen(new GameOverStage(game));
                }

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        // display blue screen
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
