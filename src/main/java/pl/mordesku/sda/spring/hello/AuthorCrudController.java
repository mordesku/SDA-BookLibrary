package pl.mordesku.sda.spring.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pl.mordesku.sda.spring.hello.dao.AuthorDao;
import pl.mordesku.sda.spring.hello.entities.Author;

import javax.transaction.Transactional;


@Controller
public class AuthorCrudController {
    @Autowired
    private AuthorDao dao;

    @RequestMapping(value = "/author/add/{name:[A-Za-z0-9 ]+}")
    public ModelAndView add(@PathVariable("name") String name) {
        ModelAndView bookView = new ModelAndView("author");
        bookView.addObject("name", name);
        Author author = new Author();
        author.setName(name);
        dao.save(author);
        return bookView;
    }

    @RequestMapping(value = "/author/view/{id:[\\d]+}")
    @Transactional
    public ModelAndView view(@PathVariable("id") Long id) {
        Author loadedAuthor = dao.load(id);
        ModelAndView bookView = new ModelAndView("book");
        bookView.addObject("name", loadedAuthor.getName());
        return bookView;
    }

    @RequestMapping(value = "/author/list")
    public ModelAndView authorList() {
        ModelAndView authorList = new ModelAndView("authorList");
        authorList.addObject("authors", dao.findAllAuthors());
        return authorList;
    }

    @RequestMapping(value = "/author/delete/{id:[\\d]+}")
    @Transactional
    public ModelAndView delete(@PathVariable("id") Long id) {
        dao.delete(id);
        ModelAndView message = new ModelAndView("message");
        message.addObject("title", "author deleted");
        message.addObject("message", "the success is successful");
        return message;
    }
}
