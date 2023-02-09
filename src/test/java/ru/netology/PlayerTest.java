package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    private Map<Game, Integer> playedTime = new HashMap<>();
    GameStore store = new GameStore();
    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game2 = store.publishGame("Mario", "Экшен");
    Game game3 = store.publishGame("Tetris", "Логические");
    Player player = new Player("Petya");

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTimeIsZero() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 0);

        int expected = 0;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSeveralGames() {

        player.play(game2, 3);
        player.play(game3,5);
        player.play(game2, 4);

        int expected = 7;
        int actual = player.sumGenre(game2.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfOneGameSeveralTimes() {

        player.play(game3, 3);
        player.play(game3,5);
        player.play(game3, 4);

        int expected = 12;
        int actual = player.sumGenre(game3.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindGameByGenreMostPlayedIfSeveral() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);

        player.play(game1, 3);
        player.play(game2, 5);
        player.play(game3, 2);
        player.play(game3, 6);

        Game expected = game3;
        Game actual = player.mostPlayerByGenre(game3.getGenre());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindGameByGenreMostPlayerIfOne() {

        player.installGame(game1);

        player.play(game1, 3);

        Game expected = game1;
        Game actual = player.mostPlayerByGenre(game1.getGenre());

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindGameByGenreMostPlayerIfNull() {
        player.installGame(game3);

        player.play(game3, 0);

        Game expected = null;
        Game actual = player.mostPlayerByGenre(game3.getGenre());

        Assertions.assertEquals(expected, actual);
    }


// другие ваши тесты
}
