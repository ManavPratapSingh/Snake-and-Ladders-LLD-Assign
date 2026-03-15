package org.manav.snake_and_ladders.modules.board;

import org.manav.snake_and_ladders.modules.cell.ICell;
import org.manav.snake_and_ladders.modules.marker.IMarker;
import org.manav.snake_and_ladders.modules.player.IPlayer;

public interface IGameBoard {
    int getSize();

    void addMarker(IMarker marker);

    void placePlayer(IPlayer p, int position);

    void removePlayer(IPlayer player, int position);

    ICell getCell(int position);

    void printBoard();
}
