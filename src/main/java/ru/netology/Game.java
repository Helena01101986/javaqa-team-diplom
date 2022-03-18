package ru.netology;

import java.util.Objects;

public class Game {
    private final String title;
    private final String genre;
    private final GameStore store;

    public Game(String title, String genre, GameStore store) {
        this.title = title;
        this.genre = genre;
        this.store = store;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public GameStore getStore() {
        return store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(title, game.title) &&
                Objects.equals(genre, game.genre) &&
                Objects.equals(store, game.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, store);
    }
}
