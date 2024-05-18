package pl.projekt.projekt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;



import java.util.List;

@Repository
public class Rental {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private int bookId;
    private String bookTitle;
    private int readerId;
    private String readerName;
    private String readerLastname;

    public String getBookTitle() {
        return bookTitle;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getReaderName() {
        return readerName;
    }

    public void setReaderName(String readerName) {
        this.readerName = readerName;
    }

    public String getReaderLastname() {
        return readerLastname;
    }

    public void setReaderLastname(String readerLastname) {
        this.readerLastname = readerLastname;
    }
    public int rentBook(List<Rental> rentals) {
        rentals.forEach((rent) -> {
            jdbcTemplate.update("INSERT INTO BOOK_READER (book_ID, reader_ID) VALUES(?, ?)", new Object[]{rent.getBookId(), rent.getReaderId()});
        });
        return 1;
    }
}

