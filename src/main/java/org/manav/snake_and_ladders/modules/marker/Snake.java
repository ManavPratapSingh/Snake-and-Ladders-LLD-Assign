package org.manav.snake_and_ladders.modules.marker;

public class Snake implements Marker{
    private final int head;
    private final int tail;

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
}
