package mk.lab1.service.impl;

import mk.lab1.model.Author;
import mk.lab1.model.Book;
import mk.lab1.model.Category;
import mk.lab1.model.dto.BookDto;
import mk.lab1.model.exceptions.BookNameNotFoundException;
import mk.lab1.model.exceptions.BookIdNotFoundException;
import mk.lab1.repository.BookRepository;
import mk.lab1.service.AuthorService;
import mk.lab1.service.BookService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }


    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new);
    }

    @Override
    public Book findByName(String name) {
        return this.bookRepository.findByName(name).orElseThrow(BookNameNotFoundException::new);
    }


    @Override
    @Transactional
    public Book save(String name, Category category, Long authorId, Integer available) {


        Author author = authorService.findById(authorId);

        this.bookRepository.deleteByName(name);
        return this.bookRepository.save(new Book(name, category, author, available));
    }

    @Override
    public Book save(BookDto BookDto) {

        this.bookRepository.deleteByName(BookDto.getName());
        Author author = authorService.findById(BookDto.getAuthor());
        return this.bookRepository.save(new Book(BookDto.getName(), BookDto.getCategory(), author, BookDto.getAvailableCopies()));
    }

    @Override
    @Transactional
    public Book edit(Long id, String name, Category category, Long authorId, Integer available) {

        Book book = this.bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new);

        book.setName(name);
        book.setAuthor(authorService.findById(authorId));
        book.setCategory(category);
        book.setAvailableCopies(available);


        return this.bookRepository.save(book);
    }

    @Override
    public Book edit(Long id, BookDto BookDto) {
        Book Book = this.bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new);

        Book.setName(BookDto.getName());
        Book.setCategory(BookDto.getCategory());
        Book.setAuthor(authorService.findById(BookDto.getAuthor()));
        Book.setAvailableCopies(BookDto.getAvailableCopies());


        return this.bookRepository.save(Book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

}
