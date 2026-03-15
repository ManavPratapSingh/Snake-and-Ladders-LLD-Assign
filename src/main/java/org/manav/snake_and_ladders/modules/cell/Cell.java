package org.manav.snake_and_ladders.modules.cell;

import java.util.ArrayList;
import java.util.List;

import org.manav.snake_and_ladders.modules.marker.IMarker;
import org.manav.snake_and_ladders.modules.player.IPlayer;

public class Cell implements ICell {
    private final int number;
    private String symbol;
    private final IMarker marker;
    private final List<IPlayer> players;

    public Cell(int size, int sum_indices, IMarker marker) {
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

    public IMarker getMarker() {
        return marker;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void addPlayer(IPlayer player) {
        players.add(player);
    }

    public void removePlayer(IPlayer player) {
        players.remove(player);
    }

    public List<IPlayer> getPlayers() {
        return players;
    }
}
