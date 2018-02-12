package pl.mordesku.sda.spring.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.mordesku.sda.spring.hello.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 12.02.2018
 * Time: 19:28
 */

public class DbAuthenticationManager implements AuthenticationManager {
    Logger log = Logger.getLogger(getClass().getName());
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String login = a.getName();
        String credentials = (String)a.getCredentials();
        User user = userService.loginUser(login, credentials);
        log.info("Attempting to log "+login + " pass "+credentials);
        if (user != null) {
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new UsernamePasswordAuthenticationToken(login, credentials, grantedAuths);
        }
        return a;
    }

}