package org.manav.snake_and_ladders.modules.board;

import org.manav.snake_and_ladders.modules.cell.Cell;
import org.manav.snake_and_ladders.modules.marker.Marker;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private final int size;
    private final Cell[] cells;

    public Board(int size) {
        this.size = size;
        this.cells = new Cell[size*size];
        for (int i = 0; i < size*size; i++) {
            int sum_indices = (i/size) + (i%size);
            cells[i] = new Cell(size, sum_indices, null);
        }
    }

    public void addAllMarkers(Set<Marker> markers) {
        for (Marker marker : markers) {
            addMarker(marker);
        }
    }

    public void addMarker(Marker marker) {
        int cell_number = marker.getStart();
        cells[cell_number-1] = new Cell(size, (cell_number-1)/size + (cell_number-1)%size, marker);
    }

    public void printBoard() {
        for (int i = size*size-1; i >= 0; i--) {
            System.out.print(cells[i] + " ");
            if (i % size == 0) {
                System.out.println();
            }
        }
    }
}
