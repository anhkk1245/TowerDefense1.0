package com.mygdx.game.gamestate.gameoverstage;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.startstage.StartStage;
import com.mygdx.game.helper.AssetLoader;

public class GameOverStage implements Screen {
    MyGdxGame game;
    SpriteBatch batch;
    Texture over;

    public GameOverStage(MyGdxGame game) {
        this.game = game;
        batch = new SpriteBatch();
        over = AssetLoader.over;
    }

    @Override
    public void show() {
        // choose SPACE to move to next stage
        AssetLoader.gameOver.setLooping(true);
        AssetLoader.gameOver.play();
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {

                if (keyCode == Input.Keys.SPACE) {
                    AssetLoader.gameOver.stop();
                    game.setScreen(new StartStage(game));
                }

                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        // display green screen
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(over,0,0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
