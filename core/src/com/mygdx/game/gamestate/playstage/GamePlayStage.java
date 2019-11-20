package com.mygdx.game.gamestate.playstage;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.gameoverstage.GameOverStage;
import com.mygdx.game.gamestate.startstage.StartStage;
import com.mygdx.game.helper.InputHandle;
import com.mygdx.game.helper.Wave;

public class GamePlayStage implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    MyGdxGame game;

    public GamePlayStage(MyGdxGame game) {
        this.game = game;
        world = new GameWorld();
        renderer = new GameRenderer(world);

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputHandle(world, game));
    }

    @Override
    public void render(float delta) {
        renderer.render();
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
        //renderer.dispose();
    }
}
