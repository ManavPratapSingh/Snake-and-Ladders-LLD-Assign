package org.manav.snake_and_ladders.modules.game;

import org.manav.snake_and_ladders.modules.board.IGameBoard;
import org.manav.snake_and_ladders.modules.die.IDie;
import org.manav.snake_and_ladders.modules.player.IPlayer;

public interface IGameStrategy {
    int executeTurn(IPlayer player, IGameBoard board, IDie die, int totalCells);
}
