package org.manav.snake_and_ladders.modules.die;

import java.util.Random;

public class SixFaceDie implements IDie {
    private final int faces;
    private final Random random;

    public SixFaceDie() {
        this.faces = 6;
        this.random = new Random();
    }

    @Override
    public int roll() {
        return random.nextInt(faces) + 1;
    }

    @Override
    public int getFaces() {
        return faces;
    }
}
