package com.mygdx.game.gamestate.playstage2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.playstage.GameRenderer;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.helper.InputHandle;
import com.mygdx.game.helper.InputHandle2;

public class GamePlayStage2 implements Screen {
    private GameWorld2 world;
    private GameRenderer2 renderer;
    MyGdxGame game;

    public GamePlayStage2(MyGdxGame game) {
        this.game = game;
        world = new GameWorld2();
        renderer = new GameRenderer2(world);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputHandle2(world, game));
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

    }
}
