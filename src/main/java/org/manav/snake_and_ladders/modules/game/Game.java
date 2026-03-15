package org.manav.snake_and_ladders.modules.game;

import org.manav.snake_and_ladders.modules.board.Board;
import org.manav.snake_and_ladders.modules.marker.Ladder;
import org.manav.snake_and_ladders.modules.marker.Snake;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Game {
    private final int totalCells;
    private final Board board;
    private volatile boolean gameOver = false;
    private volatile int currentPosition = 1;

    public Game(int boardSize, int numPlayers) {
        this.totalCells = boardSize * boardSize;
        this.board = new Board(boardSize);
    }

    public void setMarkers() {
        Random rand = new Random();
        Set<Integer> occupiedPositions = new HashSet<>();
        int markers = 0;
        int numLadders = rand.nextInt(5) + 6; // Random 6-10
        int numSnakes = rand.nextInt(5) + 6; // Random 6-10

        occupiedPositions.add(1);
        occupiedPositions.add(totalCells);

        while (markers < numLadders) {
            int bottom = rand.nextInt(totalCells - 2) + 2;
            int top = rand.nextInt(totalCells - bottom) + bottom + 1;
            if (!occupiedPositions.contains(bottom) && !occupiedPositions.contains(top)) {
                occupiedPositions.add(bottom);
                occupiedPositions.add(top);
                board.addMarker(new Ladder(bottom, top));
                markers++;
            }
        }

        int totalTarget = numLadders + numSnakes;
        while (markers < totalTarget) {
            int head = rand.nextInt(totalCells - 2) + 2;
            int tail = rand.nextInt(head - 1) + 1;
            if (!occupiedPositions.contains(head) && !occupiedPositions.contains(tail)) {
                markers++;
                occupiedPositions.add(head);
                occupiedPositions.add(tail);
                board.addMarker(new Snake(head, tail));
            }
        }
    }

    public void startGame() {
        setMarkers();
        board.placePlayer(currentPosition);
        gameOver = false;
    }

    public void makeMove() {
        if (gameOver) return;

        int move = (int) (Math.random() * 6) + 1;
        System.out.println("\n--- Player rolled: " + move + " ---");
        
        if (currentPosition + move > totalCells) {
            System.out.println("Roll too high! Staying at " + currentPosition);
            return;
        }

        board.removePlayer(currentPosition);
        currentPosition += move;

        if (board.getCell(currentPosition).hasMarker()) {
            System.out.println("\n>>> " + board.getCell(currentPosition).getMarker().getMessage() + " <<<");
            System.out.flush();
            currentPosition = board.getCell(currentPosition).getMarker().getEnd();
        }

        board.placePlayer(currentPosition);
        
        if (currentPosition == totalCells) {
            gameOver = true;
            System.out.println("\n********************");
            System.out.println("*   PLAYER WINS!   *");
            System.out.println("********************");
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void displayBoard() {
        board.printBoard();
        System.out.flush();
    }
}
