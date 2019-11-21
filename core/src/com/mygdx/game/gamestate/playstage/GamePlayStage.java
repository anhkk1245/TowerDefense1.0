package com.mygdx.game.gamestate.playstage;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.gameoverstage.GameOverStage;
import com.mygdx.game.gamestate.playstage2.GamePlayStage2;
import com.mygdx.game.gamestate.playstage2.GameWorld2;
import com.mygdx.game.gamestate.startstage.StartStage;
import com.mygdx.game.helper.AssetLoader;
import com.mygdx.game.helper.InputHandle;
import com.mygdx.game.helper.Wave;

public class GamePlayStage implements Screen {
    private GameWorld world;
    private GameRenderer renderer;
    MyGdxGame game;
    private float timer = 0;

    public GamePlayStage(MyGdxGame game) {
        this.game = game;
        world = new GameWorld();
        renderer = new GameRenderer(world);
    }


    @Override
    public void show() {
        AssetLoader.spotEnemy.play();
        Gdx.input.setInputProcessor(new InputHandle(world, game));
    }

    @Override
    public void render(float delta) {
        if(Wave.waveNumber == 5) {
            timer += Gdx.graphics.getDeltaTime();
            AssetLoader.win.play();
            if(timer > 3) {
                timer = 0;
                game.setScreen(new GamePlayStage2(game));
                GameWorld2.isActive = true;
                GameWorld.resetWorld();
                GameWorld.isActive = false;
            }
        }


        renderer.render();

        if(GameWorld.escapedEnemy == 10) {
            timer += Gdx.graphics.getDeltaTime();
            AssetLoader.lose.play();
            if(timer > 1.5) {
                timer = 0;
                GameWorld.resetWorld();
                GameWorld.isActive = false;
                game.setScreen(new GameOverStage(game));
            }
        }
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
    }
}
