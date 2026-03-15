package org.manav.snake_and_ladders.modules.marker;

public class Snake implements IMarker {
    private final int head;
    private final int tail;
    private final String symbol = "S";

    public Snake(int head, int tail) {
        if (head <= tail) {
            throw new IllegalArgumentException("Head of the snake must be greater than the tail.");
        }
        this.head = head;
        this.tail = tail;
    }

    @Override
    public int getStart() {
        return this.head;
    }

    @Override
    public int getEnd() {
        return this.tail;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public String getMessage(String symbol) {
        return "Player " + symbol + " got bit by a snake! [" + this.head + " -> " + this.tail + "]";
    }
}
