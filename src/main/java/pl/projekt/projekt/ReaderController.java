package pl.projekt.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/readers"})
public class ReaderController {

    @Autowired
    private ReaderRepo readerRepo;

    public ReaderController(){}

    @GetMapping
    public List<Reader> getAllReaders() {
        return readerRepo.getAllReaders();
    }


    @GetMapping({"/{id}"})
    public Reader getAuthorById(@PathVariable("id") int id) {
        return this.readerRepo.getAuthorById(id);
    }

    @PostMapping
    public int addReader(@RequestBody List<Reader> readers) { return readerRepo.addReader(readers); }

    @GetMapping("/reader/{reader}")
    public List<Reader> getReaderByName(@PathVariable String reader) {
        return readerRepo.getReaderByName(reader);
    }


    @PutMapping("/{id}")
    public Reader updateReader(@PathVariable int id, @RequestBody Reader reader) {
        reader.setId(id);
        readerRepo.updateReader(reader);
        return reader;
    }

    @GetMapping("/search")
    public List<Reader> searchReader(@RequestParam String keyword) {
        return readerRepo.findByNameOrLastName(keyword);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable int id) {
        readerRepo.deleteById(id);
    }

}
