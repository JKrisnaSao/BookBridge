package csc340.BookbridgeBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public String getAllBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books"; // Maps to "books.html"
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id).orElseThrow(() ->
                new RuntimeException("Book not found with ID: " + id)));
        return "book-details"; // Maps to "book-details.html"
    }

    @GetMapping("/new")
    public String showCreateBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book-form"; // Maps to "book-form.html"
    }

    @PostMapping("/new")
    public String createBook(@ModelAttribute Book book) {
        bookService.createBook(book);
        return "redirect:/books/all";
    }

    @GetMapping("/update/{id}")
    public String showUpdateBookForm(@PathVariable Long id, Model model) {
        model.addAttribute("book", bookService.getBookById(id).orElseThrow(() ->
                new RuntimeException("Book not found with ID: " + id)));
        return "book-form"; // Reuses "book-form.html"
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book) {
        bookService.updateBook(id, book);
        return "redirect:/books/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return "redirect:/books/all";
    }
}
