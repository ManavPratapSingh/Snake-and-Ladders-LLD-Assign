package org.manav.snake_and_ladders.modules.game;

import org.manav.snake_and_ladders.modules.board.IGameBoard;
import org.manav.snake_and_ladders.modules.die.IDie;
import org.manav.snake_and_ladders.modules.player.IPlayer;

public class EasyGameStrategy implements IGameStrategy {
    @Override
    public int executeTurn(IPlayer player, IGameBoard board, IDie die, int totalCells) {
        int currentPos = player.getPosition();
        boolean rollAgain = true;

        while (rollAgain) {
            int move = die.roll();
            System.out.println("\n--- Player " + player.getSymbol() + " rolled: " + move + " ---");

            if (currentPos + move <= totalCells) {
                currentPos += move;
                currentPos = handleMarkers(player, currentPos, board);
                if (currentPos == totalCells) {
                    break;
                }
            } else {
                System.out.println("Roll too high! Cannot move from " + currentPos);
            }

            if (move == 6) {
                System.out.println("You rolled a 6! You get another roll.");
                rollAgain = true;
            } else {
                rollAgain = false;
            }
        }
        return currentPos;
    }

    private int handleMarkers(IPlayer player, int pos, IGameBoard board) {
        if (board.getCell(pos).hasMarker()) {
            System.out.println("\n>>> "
                    + board.getCell(pos).getMarker()
                            .getMessage(player.getSymbol())
                    + " <<<");
            return board.getCell(pos).getMarker().getEnd();
        }
        return pos;
    }
}
