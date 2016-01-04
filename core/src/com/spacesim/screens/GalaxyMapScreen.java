package com.spacesim.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.spacesim.Planet;
import com.spacesim.SpaceSim;

public class GalaxyMapScreen implements Screen, InputProcessor {

	SpaceSim game;
	Planet chosenPlanet;
	private Texture background;
	private Texture star,starSelected;
	private SpriteBatch batch;
    private BitmapFont font;
	
	public GalaxyMapScreen(SpaceSim game) {
		this.game = game;
		Gdx.input.setInputProcessor(this);
		
		batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);
		background = new Texture(Gdx.files.internal("dueling_stars.png"));
		star = new Texture(Gdx.files.internal("star.png"));
		starSelected = new Texture(Gdx.files.internal("star_red.png"));
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
		batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        for(Planet p : this.game.galaxy.planets) {
        	if(chosenPlanet != null && p.name.equals(chosenPlanet.name) && p.position.equals(chosenPlanet.position)) {
        		Vector2 newp = new Vector2(p.position).scl(600);
				batch.draw(starSelected, newp.x,newp.y,15, 15);

        	} else {
        		Vector2 newp = new Vector2(p.position).scl(600);
				batch.draw(star,newp.x,newp.y,15, 15);
        	}
        }
		batch.end();

        if(chosenPlanet == null) {
        	batch.begin();
        	font.draw(batch, "Please select a planet to travel to.", 600, 600, 150, 10, true);
        	batch.end();
        } else {
        	int costToTravel = (int)Math.floor(chosenPlanet.position.dst(this.game.ship.position) * 10.0f);
        	batch.begin();
        	font.draw(batch, String.format("Click or press enter to travel to the planet.\nPlanet Name: %s\nTravel Costs.\nFood: %d\nWater: %d", chosenPlanet.name, costToTravel, costToTravel), 600, 600, 150, 10, true);
        	batch.end();
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

	}

	@Override
	public void dispose() {
		batch.dispose();
        font.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.ENTER:
			// TODO travel to planet
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		screenY = 600 - screenY;
		for(Planet p : this.game.galaxy.planets) {
        	Vector2 newp = new Vector2(p.position).scl(600);
        	if((screenX >= (newp.x - 10) && screenX <= (newp.x + 10)) && (screenY >= (newp.y - 10) && screenY <= (newp.y + 10))) {
        		chosenPlanet = p;
        	}
        }
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
