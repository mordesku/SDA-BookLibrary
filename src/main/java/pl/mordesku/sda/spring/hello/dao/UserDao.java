package pl.mordesku.sda.spring.hello.dao;

import pl.mordesku.sda.spring.hello.entities.User;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 12.02.2018
 * Time: 19:26
 */
public interface UserDao {
    User findByLoginAndPassword(String login, String passowrd);
}
