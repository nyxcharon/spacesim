package com.spacesim.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.spacesim.SpaceSim;

/**
 * Created by Bobby on 1/3/2016.
 */
public class StartScreen implements Screen
{
	private SpaceSim game;
    private Texture background;
    private SpriteBatch batch;
    private Stage stage;
	private Skin skin;
    private BitmapFont headingFont;
    private BitmapFont buttonFont;
    private int headingX;
    private int headingY;
    private String heading;
    
    public StartScreen(SpaceSim game)
    {
    	this.game = game;
        background = new Texture(Gdx.files.internal("bg5.jpg"));
        batch = new SpriteBatch();
        stage = new Stage();
        heading = "Space Simulator";
        
        Gdx.input.setInputProcessor(stage);
        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Philosopher-Regular.ttf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        buttonFont = generator.generateFont(parameter);
        buttonFont.setColor(1, 1, 1, 1);
        parameter.size = 72;
        headingFont = generator.generateFont(parameter);
        headingFont.setColor(1, 1, 1, 1);
        generator.dispose();
        
        GlyphLayout layout = new GlyphLayout(headingFont, heading);
        headingX = (int) ((Gdx.graphics.getWidth() / 2) - (layout.width / 2));
        headingY = 550;
        
        this.skin = new Skin();
		this.skin.add("default", buttonFont);
		
		Pixmap pixmap = new Pixmap(200, 50, Pixmap.Format.RGB888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		this.skin.add("background", new Texture(pixmap));
		
		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.up = this.skin.newDrawable("background", Color.GRAY);
		textButtonStyle.over = this.skin.newDrawable("background", Color.LIGHT_GRAY);
		textButtonStyle.font = this.skin.getFont("default");
		this.skin.add("default", textButtonStyle);
		
		TextButton startButton = new TextButton("Start", this.skin);
		startButton.addListener(new ClickListener() {
			@Override
		    public void clicked(InputEvent event, float x, float y) {
		        StartScreen.this.game.setScreen(new GalaxyMapScreen(StartScreen.this.game));
		    };
		});
		startButton.setPosition((Gdx.graphics.getWidth()/2) - (startButton.getWidth()/2), 300);
		
		this.stage.addActor(startButton);
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
        headingFont.draw(batch, heading, headingX, headingY);
        batch.end();
        
        this.stage.act();
		this.stage.draw();
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

    }
}
