package org.manav.snake_and_ladders.modules.game;

import org.manav.snake_and_ladders.modules.board.IGameBoard;
import org.manav.snake_and_ladders.modules.die.IDie;
import org.manav.snake_and_ladders.modules.die.SixFaceDie;
import org.manav.snake_and_ladders.modules.marker.Ladder;
import org.manav.snake_and_ladders.modules.marker.Snake;
import org.manav.snake_and_ladders.modules.player.IPlayer;
import org.manav.snake_and_ladders.modules.player.Player;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

public class Game {
    private final int totalCells;
    private final IGameBoard board;
    private volatile boolean gameOver = false;
    private volatile int initialPosition = 1;
    private final IPlayer[] players;
    private final Queue<IPlayer> playerQueue = new LinkedList<>();
    private final IDie die;

    private final IGameStrategy strategy;

    public Game(IGameBoard board, int numPlayers, Difficulty difficulty) {
        this.totalCells = board.getSize() * board.getSize();
        this.board = board;
        this.players = new IPlayer[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            this.players[i] = new Player("P" + (i + 1));
            this.players[i].setPosition(1);
        }
        this.die = new SixFaceDie();
        this.strategy = (difficulty == Difficulty.HARD) ? new HardGameStrategy() : new EasyGameStrategy();
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
        for (IPlayer p : players) {
            board.placePlayer(p, initialPosition);
            playerQueue.add(p);
        }
        gameOver = false;
    }

    public void makeMove() {
        if (gameOver)
            return;

        IPlayer currentPlayer = playerQueue.poll();
        int turnStartingPosition = currentPlayer.getPosition();
        int finalPosition = strategy.executeTurn(currentPlayer, board, die, totalCells);

        if (finalPosition != turnStartingPosition) {
            board.removePlayer(currentPlayer, turnStartingPosition);
            currentPlayer.setPosition(finalPosition);
            board.placePlayer(currentPlayer, finalPosition);

            if (finalPosition == totalCells) {
                gameOver = true;
                System.out.println("\n********************");
                System.out.println("*   PLAYER " + currentPlayer.getSymbol() + " WINS!   *");
                System.out.println("********************");
            }
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
