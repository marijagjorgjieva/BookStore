package mk.lab1.service;

import mk.lab1.model.Book;
import mk.lab1.model.Category;
import mk.lab1.model.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(String name, Category category, Long authorId, Integer available);

    Optional<Book> save(BookDto BookDto);

    Optional<Book> edit(Long id,String name, Category category, Long authorId, Integer available);

    Optional<Book> edit(Long id, BookDto BookDto);

    void deleteById(Long id);

    Optional<Book> markAsTaken(Long id);

    Page<Book> findAllWithPagination(Pageable pageable);
}
