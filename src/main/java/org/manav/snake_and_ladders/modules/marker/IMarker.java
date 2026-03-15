package org.manav.snake_and_ladders.modules.marker;

public interface IMarker {
    int getStart();

    int getEnd();

    String getSymbol();

    String getMessage(String symbol);
}
