package com.spacesim;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Galaxy {

	public List<Planet> planets;
	
	public Galaxy(int numPlanets, Random rnd) {
		List<String> planetNames = this.loadPlanetNames(); 
		Iterator<Vector2> positions = this.createPositions(rnd, numPlanets).iterator();
		planets = new ArrayList<Planet>();
		for(int i = 0;i < numPlanets;i++) {
			String planetName = planetNames.get(rnd.nextInt(planetNames.size()));
			planets.add(new Planet(planetName, positions.next()));
		}
	}
	
	private Set<Vector2> createPositions(Random rnd, int numPositions) {
		Set<Vector2> positions = new HashSet<Vector2>();
		while(positions.size() < numPositions) {
			positions.add(new Vector2(rnd.nextFloat(), rnd.nextFloat()));
		}
		return positions;
	}
	
	private List<String> loadPlanetNames() {
		List<String> planetNames = new ArrayList<String>();
		try {
			File planetNamesFile = Gdx.files.internal("altplanetnames.txt").file();
			BufferedReader br = new BufferedReader(new FileReader(planetNamesFile));
			String line;
			while((line = br.readLine()) != null) {
				planetNames.add(line.trim());
			}
			br.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return planetNames;
	}
}
