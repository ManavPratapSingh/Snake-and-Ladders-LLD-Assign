package org.manav.snake_and_ladders.modules.marker;

public class Ladder implements Marker{
    private final int bottom;
    private final int top;

    public Ladder(int bottom, int top) {
        if (bottom >= top) {
            throw new IllegalArgumentException("Bottom of the ladder must be less than the top.");
        }
        this.bottom = bottom;
        this.top = top;
    }

    @Override
    public int getStart() {
        return this.bottom;
    }

    @Override
    public int getEnd() {
        return this.top;
    }
}
