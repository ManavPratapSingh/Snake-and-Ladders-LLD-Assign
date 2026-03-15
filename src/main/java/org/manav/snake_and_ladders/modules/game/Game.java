package org.manav.snake_and_ladders.modules.game;

import org.manav.snake_and_ladders.modules.board.Board;
import org.manav.snake_and_ladders.modules.marker.Ladder;
import org.manav.snake_and_ladders.modules.marker.Snake;
import org.manav.snake_and_ladders.modules.player.Player;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Game {
    private final int totalCells;
    private final Board board;
    private volatile boolean gameOver = false;
    private volatile int initialPosition = 1;
    private final Player[] players;
    private final Queue<Player> playerQueue = new LinkedList<>();

    public Game(int boardSize, int numPlayers) {
        this.totalCells = boardSize * boardSize;
        this.board = new Board(boardSize);
        this.players = new Player[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            this.players[i] = new Player("P" + (i + 1));
            this.players[i].setPosition(1);
        }
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
        for (Player p : players) {
            board.placePlayer(p, initialPosition);
            playerQueue.add(p);
        }
        gameOver = false;
    }

    public void makeMove() {
        if (gameOver)
            return;

        Player currentPlayer = playerQueue.poll();
        int turnStartingPosition = currentPlayer.getPosition();
        int sixCount = 0;
        int turnaroundCount = 0; // sixCount is already used locally
        boolean firstRollInTurn = true;
        boolean turnCancelled = false;

        while (firstRollInTurn || (sixCount > 0 && sixCount < 3)) {
            firstRollInTurn = false;
            int move = (int) (Math.random() * 6) + 1;
            System.out.println("\n--- Player " + currentPlayer.getSymbol() + " rolled: " + move + " ---");

            if (move == 6) {
                sixCount++;
                if (sixCount == 3) {
                    System.out.println(
                            "!!! Three 6s in a row! Turn cancelled. Reverting to " + turnStartingPosition + " !!!");
                    board.removePlayer(currentPlayer, currentPlayer.getPosition());
                    currentPlayer.setPosition(turnStartingPosition);
                    board.placePlayer(currentPlayer, turnStartingPosition);
                    turnCancelled = true;
                    break;
                }
            } else {
                sixCount = 0; // Break the '6' chain
            }

            // Execute move
            if (currentPlayer.getPosition() + move > totalCells) {
                System.out.println("Roll too high! Cannot move from " + currentPlayer.getPosition());
            } else {
                board.removePlayer(currentPlayer, currentPlayer.getPosition());
                currentPlayer.setPosition(currentPlayer.getPosition() + move);
                board.placePlayer(currentPlayer, currentPlayer.getPosition());

                // Check market consequence
                if (board.getCell(currentPlayer.getPosition()).hasMarker()) {
                    System.out.println("\n>>> "
                            + board.getCell(currentPlayer.getPosition()).getMarker()
                                    .getMessage(currentPlayer.getSymbol())
                            + " <<<");
                    board.removePlayer(currentPlayer, currentPlayer.getPosition());
                    currentPlayer.setPosition(board.getCell(currentPlayer.getPosition()).getMarker().getEnd());
                    board.placePlayer(currentPlayer, currentPlayer.getPosition());
                }

                if (currentPlayer.getPosition() == totalCells) {
                    gameOver = true;
                    System.out.println("\n********************");
                    System.out.println("*   PLAYER " + currentPlayer.getSymbol() + " WINS!   *");
                    System.out.println("********************");
                    break;
                }
            }

            if (sixCount == 0)
                break; // Finished turn (didn't roll a 6)

            System.out.println("You rolled a 6! You get another roll.");
        }

        if (!gameOver) {
            playerQueue.add(currentPlayer);
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
