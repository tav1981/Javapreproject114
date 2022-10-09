package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь
        System.out.println("privet");
        //var util = new Util();

        //util.getMySQLConnection();

        //Создание таблицы User(ов)
        UserServiceImpl userService = new UserServiceImpl();

        userService.dropUsersTable();

        userService.createUsersTable();
        //Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        userService.saveUser("вася", "vasichkin", (byte) 9);
        System.out.println("User с именем - " + userService.getUserNameById(1) + " добавлен в базу данных" );
        userService.saveUser("petya", "vasichkin", (byte) 10);
        System.out.println("User с именем - " + userService.getUserNameById(2) + " добавлен в базу данных" );
        userService.saveUser("вася", "петичкин", (byte) 13);
        System.out.println("User с именем - " + userService.getUserNameById(3) + " добавлен в базу данных" );
        userService.saveUser("вано", "ванович", (byte) 99);
        System.out.println("User с именем - " + userService.getUserNameById(4) + " добавлен в базу данных" );
        //Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        System.out.println(userService.toString());
        //Очистка таблицы User(ов)
        userService.cleanUsersTable();
        //Удаление таблицы
        userService.dropUsersTable();
    }
}
