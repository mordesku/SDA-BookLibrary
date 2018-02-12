package pl.mordesku.sda.spring.hello.entities;

import javax.persistence.*;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 12.02.2018
 * Time: 19:15
 */
@Entity
public class User {
    @Id
    @SequenceGenerator(name="iduser_seq",
            sequenceName="iduser_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="iduser_seq")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
