package ru.netology.manager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.task.Epic;
import ru.netology.task.Meeting;
import ru.netology.task.SimpleTask;
import ru.netology.task.Task;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodosTest {

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        Todos todos = new Todos();

        todos.add(simpleTask);
        String query = "Позвонить родителям";

        Task[] expected = {simpleTask};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Todos todos = new Todos();

        todos.add(epic);
        String query = "Молоко";

        Task[] expected = {epic};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchMeetings() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Todos todos = new Todos();

        todos.add(meeting);
        String query = "Выкатка 3й версии приложения";

        Task[] expected = {meeting};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfSeveralSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        SimpleTask simpleTask2 = new SimpleTask(2, "Позвонить сестре");
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(simpleTask2);
        String query = "Позвонить сестре";

        Task[] expected = {simpleTask2};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfSeveralMeetings() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Meeting meeting2 = new Meeting(
                123,
                "Выкатка 1й версии приложения",
                "Приложение Банка",
                "В среду утром"
        );
        Todos todos = new Todos();

        todos.add(meeting);
        todos.add(meeting2);
        String query = "Выкатка 1й версии приложения";

        Task[] expected = {meeting2};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfSeveralEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        String[] subtasks2 = {"Сыр", "Батон", "Масло"};
        Epic epic = new Epic(55, subtasks);
        Epic epic2 = new Epic(11, subtasks2);
        Todos todos = new Todos();

        todos.add(epic);
        todos.add(epic2);
        String query = "Сыр";

        Task[] expected = {epic2};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfNoSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        Todos todos = new Todos();

        todos.add(simpleTask);
        String query = "Позвонить маме";

        Task[] expected = {};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfNoMeetings() {
        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(meeting);
        String query = "Выкатка 4й версии приложения";

        Task[] expected = {};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchIfNoEpic() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};

        Epic epic = new Epic(55, subtasks);

        Todos todos = new Todos();

        todos.add(epic);
        todos.add(epic);
        String query = "Сыр";

        Task[] expected = {};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchSeveralSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        SimpleTask simpleTask2 = new SimpleTask(2, "Позвонить сестре");
        SimpleTask simpleTask3 = new SimpleTask(7, "Позвонить начальнику");
        SimpleTask simpleTask4 = new SimpleTask(12, "Прочитать книгу");
        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(simpleTask2);
        todos.add(simpleTask3);
        todos.add(simpleTask4);
        String query = "Позвонить";

        Task[] expected = {simpleTask, simpleTask2, simpleTask3};
        Task[] actual = todos.search(query);
        Assertions.assertArrayEquals(expected, actual);
    }
}
