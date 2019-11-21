package com.mygdx.game.gamestate.playstage2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.gameoverstage.GameOverStage;
import com.mygdx.game.gamestate.playstage.GameRenderer;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.helper.AssetLoader;
import com.mygdx.game.helper.InputHandle;
import com.mygdx.game.helper.InputHandle2;
import com.mygdx.game.helper.Wave;

public class GamePlayStage2 implements Screen {
    private GameWorld2 world;
    private GameRenderer2 renderer;
    MyGdxGame game;
    private float timer = 0;

    public GamePlayStage2(MyGdxGame game) {
        this.game = game;
        world = new GameWorld2();
        renderer = new GameRenderer2(world);
    }

    @Override
    public void show() {
        AssetLoader.inGame.setLooping(true);
        AssetLoader.spotEnemy.play();
        AssetLoader.inGame.play();
        Gdx.input.setInputProcessor(new InputHandle2(world, game));
    }

    @Override
    public void render(float delta) {
        if(Wave.waveNumber == 5) {
            timer += Gdx.graphics.getDeltaTime();
            AssetLoader.win.play();
            if(timer > 3) {
                AssetLoader.inGame.stop();
                timer = 0;
                game.setScreen(new GameOverStage(game));
                GameWorld2.isActive = false;
                GameWorld2.resetWorld();
            }
        }

        renderer.render();

        if(GameWorld2.escapedEnemy == 10) {
            timer += Gdx.graphics.getDeltaTime();
            AssetLoader.lose.play();
            if(timer > 1.5) {
                AssetLoader.inGame.stop();
                timer = 0;
                GameWorld2.resetWorld();
                GameWorld2.isActive = false;
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
