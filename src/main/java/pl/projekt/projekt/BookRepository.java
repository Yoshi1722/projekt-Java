package pl.projekt.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public BookRepository() {
    }

    public List<Book> getAllBooks() {
        return this.jdbcTemplate.query("SELECT id, title, author, quantity FROM books", BeanPropertyRowMapper.newInstance(Book.class));
    }

    public List<Book> getAllAuthors() {
        return this.jdbcTemplate.query("SELECT author FROM books", BeanPropertyRowMapper.newInstance(Book.class));
    }
    public Book getBookById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT id, title, author, quantity FROM books WHERE id = ?",
                new Object[]{id},
                BeanPropertyRowMapper.newInstance(Book.class)
        );
    }

    public int addBook(List<Book> books) {
        books.forEach((book) -> {
            jdbcTemplate.update("INSERT INTO books (title, author, quantity) VALUES(?, ?, ?)", new Object[]{book.getTitle(), book.getAuthor(), book.getQuantity()});
        });
        return 1;
    }

    public List<Book> findByAuthor(String author) {
        return jdbcTemplate.query(
                "SELECT * FROM books WHERE author LIKE ?",
                new Object[]{"%" + author + "%"},
                BeanPropertyRowMapper.newInstance(Book.class)
        );
    }

    public List<Book> findByTitle(String title) {
        return jdbcTemplate.query(
                "SELECT * FROM books WHERE title LIKE ?",
                new Object[]{"%" + title + "%"},
                BeanPropertyRowMapper.newInstance(Book.class)
        );
    }

    public List<Book> findByTitleOrAuthor(String keyword) {
        return jdbcTemplate.query(
                "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?",
                new Object[]{"%" + keyword + "%", "%" + keyword + "%"},
                BeanPropertyRowMapper.newInstance(Book.class)
        );
    }
    public int updateBookQuantity(int id, int quantity) {
        return jdbcTemplate.update(
                "UPDATE books SET quantity = ? WHERE id = ?",
                quantity, id
        );
    }


    public int deleteBookById(int id) {
        return jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }
}
