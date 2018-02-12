package pl.mordesku.sda.spring.hello.dao;

import pl.mordesku.sda.spring.hello.entities.Author;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created with notepad.exe.
 * Author: mprzy
 * Date: 09.02.2018
 * Time: 14:41
 */
public interface AuthorDao {
    @Transactional
    Author save(Author author);

    @Transactional
    Author load(Long id);

    @Transactional
    List<Author> findAllAuthors();

    @Transactional
    void delete(Long id);
}
