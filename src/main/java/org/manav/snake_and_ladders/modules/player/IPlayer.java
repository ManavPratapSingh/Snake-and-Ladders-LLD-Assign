package org.manav.snake_and_ladders.modules.player;

public interface IPlayer {
    String getName();

    String getSymbol();

    int getPosition();

    void setPosition(int position);
}
