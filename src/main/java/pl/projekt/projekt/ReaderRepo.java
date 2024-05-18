package pl.projekt.projekt;

import lombok.Locked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReaderRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ReaderRepo(){}

    public List<Reader> getAllReaders(){
        return this.jdbcTemplate.query("SELECT id, name, lastname, email FROM READERS", BeanPropertyRowMapper.newInstance(Reader.class));
    }

    public Reader getAuthorById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Readers WHERE id = ?", new Object[]{id}, BeanPropertyRowMapper.newInstance(Reader.class));
    }

    public int addReader(List<Reader> readers) {
        readers.forEach((reader) -> {
            jdbcTemplate.update("INSERT INTO readers (name, lastname, email) VALUES(?, ?, ?)", new Object[]{reader.getName(), reader.getLastname(), reader.getEmail()});
        });
        return 1;
    }

    public List<Reader> getReaderByName(String reader) {
        return jdbcTemplate.query(
                "SELECT * FROM readers WHERE name LIKE ?",
                new Object[]{"%" + reader + "%"},
                BeanPropertyRowMapper.newInstance(Reader.class)
        );
    }

    public int updateReader(Reader reader) {
        return jdbcTemplate.update(
                "UPDATE Readers SET name = ?, lastname = ?, email = ? WHERE id = ?",
                reader.getName(), reader.getLastname(), reader.getEmail(), reader.getId()
        );
    }

    public List<Reader> findByNameOrLastName(String keyword) {
        return jdbcTemplate.query(
                "SELECT * FROM readers WHERE name LIKE ? OR lastname LIKE ?",
                new Object[]{"%" + keyword + "%", "%" + keyword + "%"},
                BeanPropertyRowMapper.newInstance(Reader.class)
        );
    }
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM Readers WHERE id = ?", id);
    }
}
