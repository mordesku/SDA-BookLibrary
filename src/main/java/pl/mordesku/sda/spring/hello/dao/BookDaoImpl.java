package pl.mordesku.sda.spring.hello.dao;

import org.springframework.stereotype.Repository;
import pl.mordesku.sda.spring.hello.entities.Book;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class BookDaoImpl extends GenericDao implements BookDao {
    @Override
    @Transactional
    public Book save(Book book) {
        currentSession().saveOrUpdate(book);
        return book;
    }

    @Override
    @Transactional
    public Book load(Long id) {
        return currentSession().load(Book.class, id);
    }

    @Override
    @Transactional
    public List<Book> findAllBooks() {
        return currentSession().createQuery("from Book").getResultList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        currentSession().delete(load(id));
    }
}
