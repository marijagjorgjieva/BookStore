package mk.lab1.web_rest;

import mk.lab1.model.Author;
import mk.lab1.model.dto.AuthorDto;
import mk.lab1.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> listAll() {
        return this.authorService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Author> create(AuthorDto authorDto) {
        return this.authorService.save(authorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> update(@PathVariable Long id,  AuthorDto authorDto) {
        return this.authorService.edit(id, authorDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Author> delete(@PathVariable Long id) {
        if (!authorService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Author author = authorService.findById(id).get();
            authorService.deleteById(id);
            return ResponseEntity.ok(author);
        }
    }
}