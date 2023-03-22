package mk.lab1.service;

import mk.lab1.model.Author;
import mk.lab1.model.Book;
import mk.lab1.model.Category;
import mk.lab1.model.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    Book findById(Long id);

    Book findByName(String name);

    Book save(String name, Category category, Long authorId, Integer available);

    Book save(BookDto BookDto);

    Book edit(Long id,String name, Category category, Long authorId, Integer available);

    Book edit(Long id, BookDto BookDto);

    void deleteById(Long id);


}
