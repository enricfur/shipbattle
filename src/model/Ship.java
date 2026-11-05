package model;

public class Ship {
    private final int decks; // количество палуб
    private int hits;        // количество попаданий

    public Ship(int decks) {
        this.decks = decks;
        this.hits = 0;
    }

    public void hit() {
        if (hits < decks) {
            hits++;
        }
    }

    public boolean isSunk() {
        return hits >= decks;
    }

    public int getDecks() { return decks; }
}