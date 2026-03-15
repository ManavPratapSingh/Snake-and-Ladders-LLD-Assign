package org.manav.snake_and_ladders.modules.cell;

import java.util.ArrayList;
import java.util.List;

import org.manav.snake_and_ladders.modules.marker.Marker;
import org.manav.snake_and_ladders.modules.player.Player;

public class Cell {
    private final int number;
    private String symbol;
    private final Marker marker;
    private final List<Player> players;

    public Cell(int size, int sum_indices, Marker marker) {
        this.number = (int) Math.pow(size, 2) - (sum_indices + 2) + 1;
        this.marker = marker;
        this.symbol = ".";
        this.players = new ArrayList<>();
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

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
