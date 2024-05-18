package pl.projekt.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/books"})
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    public BookController() {
    }

    @GetMapping
    public List<Book> getAllBook() {
        return this.bookRepository.getAllBooks();
    }

    @GetMapping({"/{id}"})
    public Book getBookById(@PathVariable("id") int id) {
        return this.bookRepository.getBookById(id);
    }

    @PostMapping
    public int addBook(@RequestBody List<Book> books) {
        return bookRepository.addBook(books);
    }

    @GetMapping("/author/{author}")
    public List<Book> getBooksByAuthor(@PathVariable String author) {
        return bookRepository.findByAuthor(author);
    }

    @GetMapping("/author")
    public List<Book> getAllAuthors() {
        return bookRepository.getAllAuthors();
    }

    @PutMapping("/{id}/quantity/{quantity}")
    public void updateBookQuantity(@PathVariable int id, @PathVariable int quantity) {
        bookRepository.updateBookQuantity(id, quantity);
    }
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String keyword) {
        return bookRepository.findByTitleOrAuthor(keyword);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        bookRepository.deleteBookById(id);
    }

}
