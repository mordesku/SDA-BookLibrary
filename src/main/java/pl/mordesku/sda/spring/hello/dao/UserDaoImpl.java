package pl.mordesku.sda.spring.hello.dao;

import org.springframework.stereotype.Repository;
import pl.mordesku.sda.spring.hello.entities.User;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 12.02.2018
 * Time: 19:16
 */
@Repository
public class UserDaoImpl extends GenericDao implements UserDao {

    @Override
    @Transactional
    public User findByLoginAndPassword(String login, String password) {
        List<User> resultList = currentSession()
                .createQuery("from User where login = :login and password = :password")
                .setParameter("login", login)
                .setParameter("password", password).getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }

}
