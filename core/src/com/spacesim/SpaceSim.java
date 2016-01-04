package com.spacesim;


import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.spacesim.screens.StartScreen;

public class SpaceSim extends Game {

	public Galaxy galaxy;
	public SpaceShip ship;
	
    @Override
    public void create() {
    	this.galaxy = new Galaxy(100, new Random(System.currentTimeMillis()));
    	this.ship = new SpaceShip(new Vector2(0.5f, 0.5f), 10, 10);
    	this.setScreen(new StartScreen(this));
    }
}
