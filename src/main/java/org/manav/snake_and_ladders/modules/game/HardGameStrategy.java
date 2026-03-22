package org.manav.snake_and_ladders.modules.game;

import org.manav.snake_and_ladders.modules.board.IGameBoard;
import org.manav.snake_and_ladders.modules.die.IDie;
import org.manav.snake_and_ladders.modules.player.IPlayer;

public class HardGameStrategy implements IGameStrategy {
    @Override
    public int executeTurn(IPlayer player, IGameBoard board, IDie die, int totalCells) {
        int turnStartingPosition = player.getPosition();
        int currentPos = turnStartingPosition;
        int sixCount = 0;
        boolean firstRollInTurn = true;

        while (firstRollInTurn || (sixCount > 0 && sixCount < 3)) {
            firstRollInTurn = false;
            int move = die.roll();
            System.out.println("\n--- Player " + player.getSymbol() + " rolled: " + move + " ---");

            if (move == 6) {
                sixCount++;
                if (sixCount == 3) {
                    System.out.println(
                            "!!! Three 6s in a row! Turn cancelled. Reverting to " + turnStartingPosition + " !!!");
                    return turnStartingPosition;
                }
            } else {
                sixCount = 0;
            }

            if (currentPos + move <= totalCells) {
                currentPos += move;
                currentPos = handleMarkers(player, currentPos, board);
                if (currentPos == totalCells) {
                    break;
                }
            } else {
                System.out.println("Roll too high! Cannot move from " + currentPos);
            }

            if (sixCount == 0) {
                break;
            }

            System.out.println("You rolled a 6! You get another roll.");
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
