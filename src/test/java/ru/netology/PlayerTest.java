package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PlayerTest {
    private Map<Game, Integer> playedTime = new HashMap<>();
    GameStore store = new GameStore();
    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game2 = store.publishGame("Mario", "Экшен");
    Game game3 = store.publishGame("Tetris", "Логические");

    Game game4 = store.publishGame("Racing", "Экшен");
    Player player = new Player("Petya");

    @Test
    public void shouldSumGenreIfOneGame() {
        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 3);
        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTimeIsZero() {
        player.installGame(game2);
        player.play(game2, 0);
        int expected = 0;
        int actual = player.sumGenre("Экшен");
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfGameUninstall() {
        //player.installGame(game2);
        player.installGame(game3);
        player.play(game3, 5);
        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game2, 0);
        });
    }

    @Test //баг-репорт
    public void shouldSumGenreIfSeveralGames() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 4); // не возвращает время, проигранное в game2
        player.play(game3, 5);
        int expected = 4;
        int actual = player.sumGenre("Экшен");
        Assertions.assertEquals(expected, actual);
    }

    @Test //баг-репорт
    public void shouldSumGenreIfSeveralSameGames() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 5);
        player.play(game3, 2);
        player.play(game3, 6);
        int expected = 8;                               //суммарное кол-во часов,
        int actual = player.sumGenre("Логические");     //проигранных в третью игру не считает
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfOneGameSeveralTimes() {
        player.installGame(game1);
        player.play(game1, 3);
        player.play(game1, 5);
        player.play(game1, 4);
        int expected = 12;
        int actual = player.sumGenre(game1.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldFindGameByGenreMostPlayedIfSeveralGames() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 5);
        player.play(game3, 2);
        player.play(game3, 6);
        Game[] expected = {game3};
        Game[] actual = player.mostPlayerByGenre("Логические");
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindGameByGenreMostPlayedIfOneGame() {
        player.installGame(game1);
        player.play(game1, 3);
        Game[] expected = {game1};
        Game[] actual = player.mostPlayerByGenre(game1.getGenre());
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindGameByGenreMostPlayedIfNull() {
        player.installGame(game3);
        player.play(game3, 0);
        Game[] expected = null;
        Game[] actual = player.mostPlayerByGenre(game3.getGenre());
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindGameByGenreMostPlayedIfSeveral() {

        //Game game4 = store.publishGame("Racing", "Экшен");

        player.installGame(game2);
        player.installGame(game4);

        player.play(game2, 3);
        player.play(game4, 3);

        Game[] expected = {game2, game4};
        Game[] actual = player.mostPlayerByGenre("Экшен");

        Assertions.assertArrayEquals(expected, actual);

    }
    // другие ваши тесты
}
