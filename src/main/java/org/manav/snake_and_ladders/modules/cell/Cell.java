package org.manav.snake_and_ladders.modules.cell;

import org.manav.snake_and_ladders.modules.marker.Marker;

public class Cell {
    private final int number;
    private String symbol;
    private final Marker marker;

    public Cell(int size, int sum_indices, Marker marker) {
        this.number = (int) Math.pow(size, 2) - (sum_indices + 2) + 1;
        this.marker = marker;
        this.symbol = ".";
    }

    public int getNumber() {
        return number;
    }

    public boolean hasMarker() {
        return marker != null;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
