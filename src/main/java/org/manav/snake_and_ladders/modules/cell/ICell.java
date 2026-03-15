package org.manav.snake_and_ladders.modules.cell;

import java.util.List;

import org.manav.snake_and_ladders.modules.marker.IMarker;
import org.manav.snake_and_ladders.modules.player.IPlayer;

public interface ICell {
    int getNumber();

    boolean hasMarker();

    IMarker getMarker();

    void setSymbol(String symbol);

    String getSymbol();

    void addPlayer(IPlayer player);

    void removePlayer(IPlayer player);

    List<IPlayer> getPlayers();
}
