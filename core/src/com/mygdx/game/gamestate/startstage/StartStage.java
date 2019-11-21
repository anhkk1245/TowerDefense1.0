package com.mygdx.game.gamestate.startstage;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.gamestate.playstage.GamePlayStage;
import com.mygdx.game.gamestate.playstage.GameRenderer;
import com.mygdx.game.gamestate.playstage.GameWorld;
import com.mygdx.game.gamestate.playstage2.GamePlayStage2;
import com.mygdx.game.gamestate.playstage2.GameWorld2;
import com.mygdx.game.helper.AssetLoader;
import com.mygdx.game.helper.LoadGame;
import com.mygdx.game.helper.Wave;

public class StartStage implements Screen {
    SpriteBatch batch;
    BitmapFont font;
    MyGdxGame game;
    public static int id;
    public static float timer = 0 ;
    public static boolean justLoad = false;

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
                    timer = 0;
                    justLoad = false;
                    AssetLoader.startGame.stop();
                    game.setScreen(new GamePlayStage(game));
                    GameWorld.isActive = true;
                }
                else if (keyCode == Input.Keys.ENTER) {
                    justLoad = true;
                    AssetLoader.startGame.stop();
                    LoadGame.setInfo();
                    if(id == 1) {
                        game.setScreen(new GamePlayStage(game));
                        GameWorld.isActive = true;
                        GameWorld.tower.clear();
                        GameWorld.EnemyList.clear();
                        LoadGame.load();
                    }
                    else if(id == 2) {
                        game.setScreen(new GamePlayStage2(game));
                        GameWorld2.isActive = true;
                        GameWorld2.tower.clear();
                        GameWorld2.EnemyList.clear();
                        LoadGame.load();
                    }
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
