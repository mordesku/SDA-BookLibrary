package pl.mordesku.sda.spring.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.mordesku.sda.spring.hello.dao.UserDao;
import pl.mordesku.sda.spring.hello.entities.User;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 12.02.2018
 * Time: 19:26
 */
@Component
public class UserService {
    Logger log = Logger.getLogger(getClass().getName());
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void encodeBasePassword() {
        log.info("Password base encoded = "+passwordEncoder.encode("haslo.123"));
    }

    public User loginUser(String login, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        log.info("encoded password "+encodedPassword);
        User user = userDao.findByLoginAndPassword(login, encodedPassword);
        if (user != null) {
            return user;
        }
        return null;
    }
}
