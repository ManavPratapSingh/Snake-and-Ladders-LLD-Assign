package org.manav.snake_and_ladders;

import org.manav.snake_and_ladders.modules.game.Game;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        Game game = new Game(10, 2);
        game.startGame();

        System.out.println("Welcome to Snake and Ladders!");
        System.out.println("Press ENTER to roll the dice...");

        while (!game.isGameOver()) {
            scanner.nextLine(); // Wait for Enter
            game.makeMove();
            game.displayBoard();
            if (!game.isGameOver()) {
                System.out.print("\nPress ENTER for the next turn...");
                System.out.flush();
            }
        }
        
        System.out.println("\nThanks for playing!");
        scanner.close();
    }
}
