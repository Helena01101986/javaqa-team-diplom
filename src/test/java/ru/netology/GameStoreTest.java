package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStoreTest {

    GameStore store = new GameStore();


    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");


        assertTrue(store.containsGame(game));
    }

}


// другие ваши тесты

