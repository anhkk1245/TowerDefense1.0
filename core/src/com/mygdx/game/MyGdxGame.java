package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gamestate.playstage.GamePlayStage;
import com.mygdx.game.gamestate.playstage2.GamePlayStage2;
import com.mygdx.game.gamestate.startstage.StartStage;
import com.mygdx.game.helper.AssetLoader;
import jdk.nashorn.internal.codegen.ClassEmitter;
import sun.font.GraphicComponent;

public class MyGdxGame extends Game {

	@Override
	public void create () {
		AssetLoader.load();
		setScreen(new StartStage(this));
	}

	@Override
	public void dispose () {
		AssetLoader.dispose();
	}
}
