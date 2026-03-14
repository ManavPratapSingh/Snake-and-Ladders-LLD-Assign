package org.manav.snake_and_ladders.modules.game;

import org.manav.snake_and_ladders.modules.board.Board;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private final int boardSize;
    private final int numPlayers;
    private final Board board;

    public Game(int boardSize, int numPlayers) {
        this.boardSize = boardSize;
        this.numPlayers = numPlayers;
        this.board = new Board(boardSize);
    }

    public void setMarkers () {
        Random rand = new Random();
        Set<Integer> occupiedPositions = new HashSet<>();
        int markers=0;
        int numLadders = rand.nextInt(5) + 6; // Random 6-10
        int numSnakes = rand.nextInt(5) + 6;  // Random 6-10

        // Ensure the start (1) and end (100) are always clear
        occupiedPositions.add(1);
        occupiedPositions.add(boardSize);

        // Generate Ladders
        while (markers < numLadders) {
            int bottom = rand.nextInt(boardSize - 2) + 2; // Range 2-98
            int top = rand.nextInt(boardSize - bottom) + bottom + 1;

            if (!occupiedPositions.contains(bottom) && !occupiedPositions.contains(top)) {
                occupiedPositions.add(bottom);
                occupiedPositions.add(top);
                markers++;
            }
        }

        // Generate Snakes
        int totalTarget = numLadders + numSnakes;
        while (markers < totalTarget) {
            int head = rand.nextInt(boardSize - 2) + 2; // Range 2-98
            int tail = rand.nextInt(head - 1) + 1;

            if (!occupiedPositions.contains(head) && !occupiedPositions.contains(tail)) {
                markers++;
                occupiedPositions.add(head);
                occupiedPositions.add(tail);
            }
        }
    }

    public void makeMove() {
        int dice = (int) (Math.random() * 6) + 1; // Simulate a dice roll (1-6)
        System.out.println("Player rolled: " + dice);
    }

    public void startGame() {
        setMarkers();

    }
}
