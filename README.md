# Snake and Ladders LLD

A robust Low-Level Design (LLD) implementation of the classic Snake and Ladders game in Java. This project demonstrates clean code principles, modular architecture, and the use of design patterns like the Strategy Pattern for game difficulty.

## 🚀 Key Features

-   **Strategy Pattern**: Supports multiple gameplay dynamics using `EasyGameStrategy` and `HardGameStrategy`.
-   **Modular Architecture**: Clearly defined modules for `Board`, `Cell`, `Die`, `Marker` (Snakes and Ladders), and `Player`.
-   **Dynamic Board Generation**: Randomly generates snakes and ladders for a unique experience every time.
-   **Interactive Console UI**: Step-by-step terminal-based gameplay with visual board representation.
-   **Extensible**: Easily add new types of markers, different dice, or custom game rules.

## 🏗️ Core Components

-   **Board**: Manages the grid, player positions, and markers.
-   **Game**: The central orchestrator that handles turn management, win conditions, and game flow.
-   **Markers**:
    -   **Snake**: Moves a player from a higher-numbered cell (head) to a lower-numbered cell (tail).
    -   **Ladder**: Moves a player from a lower-numbered cell (bottom) to a higher-numbered cell (top).
-   **Die**: A `SixFaceDie` implementation for standard random movement (1-6).
-   **Difficulty**: Enum-based difficulty settings (`EASY`, `HARD`) that influence movement strategies.

## 🛠️ Tech Stack

-   **Java 21+**
-   **Maven** (Project management and build tool)

## 🏃 How to Run

1.  **Clone the Repository**:
    ```bash
    git clone <repository-url>
    cd Snake-and-Ladders-LLD
    ```

2.  **Build the Project**:
    Ensure you have Maven installed.
    ```bash
    mvn clean install
    ```

3.  **Run the Game**:
    Execute the `Main` class using Maven:
    ```bash
    mvn exec:java -Dexec.mainClass="org.manav.snake_and_ladders.Main"
    ```

4.  **Gameplay**:
    -   Follow the on-screen instructions.
    -   Press **ENTER** to roll the dice and proceed through turns.
    -   Watch the board update in real-time as players move!

## 📂 Project Structure

```text
src/main/java/org/manav/snake_and_ladders/
├── modules/
│   ├── board/      # Board management and IGameBoard interface
│   ├── cell/       # Cell representation and ICell interface
│   ├── die/        # Dice implementations (e.g., SixFaceDie)
│   ├── game/       # Core game logic and strategy patterns
│   ├── marker/     # Snake and Ladder implementations
│   └── player/     # Player entity and IPlayer interface
└── Main.java       # Project entry point
```
