package pl.mordesku.sda.spring.hello.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 09.02.2018
 * Time: 14:20
 */
public abstract class GenericDao {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
}
