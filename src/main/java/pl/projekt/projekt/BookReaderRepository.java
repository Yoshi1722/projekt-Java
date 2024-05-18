package pl.projekt.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookReaderRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int rentBook(List<Rental> rentals) {
        rentals.forEach((rent) -> {
            jdbcTemplate.update("INSERT INTO book_reader (book_id, reader_id) VALUES(?, ?)", new Object[]{rent.getBookId(), rent.getReaderId()});
        });
        return 1;
    }

    public List<Rental> getAllRentals() {
        String sql = "SELECT br.book_id, b.title, br.reader_id, r.name, r.lastname " +
                "FROM Book_Reader br " +
                "JOIN Books b ON br.book_id = b.id " +
                "JOIN Readers r ON br.reader_id = r.id";

        return jdbcTemplate.query(sql, new RowMapper<Rental>() {
            @Override
            public Rental mapRow(ResultSet rs, int rowNum) throws SQLException {
                Rental rental = new Rental();
                rental.setBookId(rs.getInt("book_id"));
                rental.setBookTitle(rs.getString("title"));
                rental.setReaderId(rs.getInt("reader_id"));
                rental.setReaderName(rs.getString("name"));
                rental.setReaderLastname(rs.getString("lastname"));
                return rental;
            }
        });
    }
    public int deleteRental(int bookId, int readerId) {
        return jdbcTemplate.update("DELETE FROM Book_Reader WHERE book_id = ? AND reader_id = ?", bookId, readerId);
    }
}

