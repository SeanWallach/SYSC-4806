package Entities.Controllers;

import Entities.Book;
import Entities.Repositories.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class OwnerController {
    @Autowired
    private BookRepo books;

    @GetMapping("/createBook")
    public String createAddressBook(Model model){
        model.addAttribute("Book", new Book());

        return "bookCreation";
    }

    @PostMapping("/createBook")
    public String publishBook(@RequestParam(value = "name") String name,
                              @RequestParam(value = "description") String description,
                              @RequestParam(value = "author") String author,
                              @RequestParam(value = "publisher") String publisher){
        Book book = new Book(name, description, author, publisher);

        books.save(book);
        return "bookDisplay";
    }
}
