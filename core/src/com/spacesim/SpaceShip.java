package com.spacesim;

import com.badlogic.gdx.math.Vector2;

public class SpaceShip {

	public Vector2 position;
	public int food;
	public int water;
	
	public SpaceShip(Vector2 position, int food, int water) {
		this.position = position;
		this.food = food;
		this.water = water;
	}
}
