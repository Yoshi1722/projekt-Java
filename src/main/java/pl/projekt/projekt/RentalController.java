package pl.projekt.projekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private BookReaderRepository bookReaderRepository;

    @PostMapping("/rent")
    public int rentBook(@RequestBody List<Rental> rentals) {return bookReaderRepository.rentBook(rentals);}

    @GetMapping
    public List<Rental> getAllRentals() {
        return bookReaderRepository.getAllRentals();
    }

    @DeleteMapping("/delete")
    public void deleteRental(@RequestParam int bookId, @RequestParam int readerId) {
        bookReaderRepository.deleteRental(bookId, readerId);
    }

}


