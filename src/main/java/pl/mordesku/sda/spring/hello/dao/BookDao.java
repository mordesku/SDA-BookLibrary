package pl.mordesku.sda.spring.hello.dao;

import pl.mordesku.sda.spring.hello.entities.Book;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 08.02.2018
 * Time: 15:04
 */
public interface BookDao {
    @Transactional
    Book save(Book book);

    @Transactional
    Book load(Long id);

    @Transactional
    List<Book> findAllBooks();

    @Transactional
    void delete(Long id);
}
