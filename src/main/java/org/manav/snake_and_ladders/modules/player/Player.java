package org.manav.snake_and_ladders.modules.player;

public class Player {
    private final String symbol;
    private int position;

    public Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
