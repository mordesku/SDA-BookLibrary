package pl.mordesku.sda.spring.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mordesku.sda.spring.hello.dao.AuthorDao;
import pl.mordesku.sda.spring.hello.dao.BookDao;
import pl.mordesku.sda.spring.hello.entities.Author;
import pl.mordesku.sda.spring.hello.entities.Book;

import javax.transaction.Transactional;


@Controller
public class BookCrudController {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private AuthorDao authorDao;

    @RequestMapping(value = "/book/add/{title:[A-Za-z0-9 ]+}/{author:[0-9]+}")
    @Transactional
    public ModelAndView add(@PathVariable("title") String title,
                            @PathVariable("author") Long authorId) {
        Author author = authorDao.load(authorId);
        ModelAndView bookView = new ModelAndView("book");
        bookView.addObject("title", title);
        bookView.addObject("author", author.getName());
        Book book = new Book();
        book.setAuthor(author);
        book.setTitle(title);
        bookDao.save(book);
        return bookView;
    }

    @RequestMapping(value = "/book/view/{id:[\\d]+}")
    @Transactional
    public ModelAndView view(@PathVariable("id") Long id) {
        Book loadedBook = bookDao.load(id);
        ModelAndView bookView = new ModelAndView("book");
        bookView.addObject("title", loadedBook.getTitle());
        bookView.addObject("author", loadedBook.getAuthor());
        return bookView;
    }

    @RequestMapping(value = "/book/list")
    public ModelAndView bookList() {
        ModelAndView bookList = new ModelAndView("bookList");
        bookList.addObject("books", bookDao.findAllBooks());
        return bookList;
    }

    @RequestMapping(value = "/book/delete/{id:[\\d]+}")
    @Transactional
    public ModelAndView delete(@PathVariable("id") Long id) {
        bookDao.delete(id);
        ModelAndView message = new ModelAndView("message");
        message.addObject("title", "book deleted");
        message.addObject("message", "the success is successful");
        return message;
    }
}
