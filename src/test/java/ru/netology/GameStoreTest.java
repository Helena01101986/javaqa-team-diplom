package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameStoreTest {

    GameStore store = new GameStore();


    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");


        assertTrue(store.containsGame(game));
    }

    @Test
    public void shouldAddTwoGame() { // проверка добавления двух игр

        ru.netology.Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        ru.netology.Game game1 = store.publishGame("Мир танков", "Онлайн-игра");

        /*assertTrue(store.containsGame(game));*/
        assertTrue(store.containsGame(game1));
    }

    @Test
    public void shouldTestGameIfNotAdd() { // проверка добавления игры, не через метод publishGame

        ru.netology.Game game = new ru.netology.Game("Ведьмак", "РПГ", store);
        ru.netology.Game game1 = store.publishGame("Мир танков", "Онлайн-игра");

        assertFalse(store.containsGame(game));

    }

    @Test
    public void shouldFalseIfNoGame() {

        assertFalse(store.containsGame(null));
    }

    @Test
    public void shouldFalseIfNoGame2() {

        assertFalse(store.containsGame(new Game("Фифа", "Спорт", store)));
    }

    @Test
    public void shouldAddPlayTimeTestMax() { // проверка на отображение имени игрока, который играл в игры этого каталога больше всего времени

        store.addPlayTime("Oleg", 3);
        store.addPlayTime("Dima", 5);
        store.addPlayTime("Anton", 2);

        String expected = "Dima";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);

    }

    @Test
    public void shouldAddSumPlayTimeOnePlayer() { // проверка на наибольшее кол-во сыгранных часов, если игрок повторяется метод addPlayTime

        store.addPlayTime("Anton", 3);
        store.addPlayTime("Dima", 5);
        store.addPlayTime("Anton", 3);

        String expected = "Anton";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddPlayTimeNull() { // проверка метода getMostPlayer на возвращение null, если игровое время равно 0

        store.addPlayTime("Anton", 0);
        store.addPlayTime("Dima", 0);
        store.addPlayTime("Anton", 0);

        String actual = store.getMostPlayer();

        assertEquals(null, actual);
    }

    @Test
    public void shouldAddNegativePlayTime() { // проверка метода getMostPlayer на возвращение null, если игровое время отрицательное

        store.addPlayTime("Katya", -1);

        String actual = store.getMostPlayer();

        assertEquals(null, actual);
    }

    @Test
    public void shouldGetMostPlayerIfDraw() { // проверка метода getMostPlayer на возвращение имени, если есть игроки с одинаковым игровым временем

        store.addPlayTime("Oleg", 3);
        store.addPlayTime("Dima", 7);
        store.addPlayTime("Anton", 2);
        store.addPlayTime("Andrei", 7);
        store.addPlayTime("Anton", 3);

        String expected = "Andrei";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);

    }

    @Test
    public void shouldGetMostPlayerIfOnePlayer() {// проверка метода getMostPlayer на возвращение имени для одного игрока

        store.addPlayTime("Tanya", 2);

        String actual = store.getMostPlayer();

        assertEquals("Tanya", actual);

    }

    @Test
    public void testGetMostPlayerIfNoPlayer() {// проверка метода getMostPlayer на возвращение null, если нет игрока

        String actual = store.getMostPlayer();

        assertEquals(null, actual);
    }

    @Test
    public void testGetSumPlayedTimeIfSeveralPlayer() {// проверка метода getSumPlayedTime на суммирование игрового времени для нескольких игроков

        store.addPlayTime("Dima", 6);
        store.addPlayTime("Oleg", 3);
        store.addPlayTime("Anton", 4);
        store.addPlayTime("Andrei", 3);
        store.addPlayTime("Katya", 4);

        int expected = 20;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetSumPlayedTimeIfTwoPlayer() {// проверка метода getSumPlayedTime на суммирование игрового времени для двух игроков

        store.addPlayTime("Dima", 6);
        store.addPlayTime("Oleg", 3);


        int expected = 9;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetSumPlayedTimeIfOnePlayer() {// проверка метода getSumPlayedTime на суммирование игрового времени для одного игрока

        store.addPlayTime("Dima", 6);

        int expected = 6;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetSumPlayedNullTime () {// проверка метода getSumPlayedTime на суммирование нулевого игрового времени

        store.addPlayTime("Dima", 0);
        store.addPlayTime("Anton", 0);
        store.addPlayTime("Dima", 0);

        int expected = 0;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetSumPlayedOneHours () {// проверка метода getSumPlayedTime на суммирование одного игрового часа

        store.addPlayTime("Dima", 1);
        store.addPlayTime("Anton", 0);
        store.addPlayTime("Dima", 0);

        int expected = 1;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);
    }

}


// другие ваши тесты

