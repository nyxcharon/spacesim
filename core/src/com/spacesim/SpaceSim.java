package com.spacesim;


import com.badlogic.gdx.Game;
import com.spacesim.screens.TestScreen;

public class SpaceSim extends Game {

    @Override
    public void create() {
        this.setScreen(new TestScreen());
    }
}
