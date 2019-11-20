package com.mygdx.game.gamestate.startstage;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.playstage.GamePlayStage;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.helper.AssetLoader;

public class StartStage implements Screen {
    SpriteBatch batch;
    BitmapFont font;
    MyGdxGame game;

    public StartStage(MyGdxGame game) {
        this.game = game;
        batch =new SpriteBatch();
        font = new BitmapFont();
    }

    @Override
    public void show(){
        // choose SPACE to move to next stage
        AssetLoader.startGame.setLooping(true);
        AssetLoader.startGame.play();
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.SPACE) {
                    AssetLoader.startGame.stop();
                    game.setScreen(new GamePlayStage(game));
                    GameWorld.isActive = true;
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
        batch.begin();
        batch.draw(AssetLoader.start,0,0);
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
    public void hide(){
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
