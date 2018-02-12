package pl.mordesku.sda.spring.hello.dao;

import org.springframework.stereotype.Repository;
import pl.mordesku.sda.spring.hello.entities.Author;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 09.02.2018
 * Time: 14:33
 */
@Repository
public class AuthorDaoImpl extends GenericDao implements AuthorDao {
    @Override
    @Transactional
    public Author save(Author author) {
        currentSession().saveOrUpdate(author);
        return author;
    }

    @Override
    @Transactional
    public Author load(Long id) {
        return currentSession().load(Author.class, id);
    }

    @Override
    @Transactional
    public List<Author> findAllAuthors() {
        return currentSession().createQuery("from Author").getResultList();
    }

    @Override
    public void delete(Long id) {
        currentSession().delete(load(id));
    }
}
