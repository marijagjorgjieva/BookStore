package mk.lab1.web_rest;

import mk.lab1.model.Book;
import mk.lab1.model.Category;
import mk.lab1.model.dto.BookDto;
import mk.lab1.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    //find by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        return this.bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //List all
    @GetMapping
    public List<Book> listAll() {
        return this.bookService.findAll();
    }

    //create
    @PostMapping("/add")
    public ResponseEntity<Book> create(@RequestBody BookDto bookDto) {
        return this.bookService.save(bookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //edit
    @PutMapping("/update/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.edit(id, bookDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {

        if (!bookService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Book book = bookService.findById(id).get();
            bookService.deleteById(id);
            return ResponseEntity.ok(book);
        }
    }

    //zemi kniga
    @PostMapping("/markastaken/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id) {
        return this.bookService.markAsTaken(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //site kategorii
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return Arrays.asList(Category.values());
    }


}
