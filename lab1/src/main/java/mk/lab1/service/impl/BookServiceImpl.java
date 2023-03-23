package mk.lab1.service.impl;

import mk.lab1.model.Author;
import mk.lab1.model.Book;
import mk.lab1.model.Category;
import mk.lab1.model.dto.BookDto;
import mk.lab1.model.exceptions.AuthorIdNotVaildExcepion;
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
    public Optional<Book> findById(Long id) {
        return Optional.of(this.bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new));
    }

    @Override
    public Optional<Book> findByName(String name) {
        return Optional.of(this.bookRepository.findByName(name).orElseThrow(BookNameNotFoundException::new));
    }


    @Override
    @Transactional
    public Optional<Book> save(String name, Category category, Long authorId, Integer available) {


        Author author = authorService.findById(authorId).orElseThrow(AuthorIdNotVaildExcepion::new);

        this.bookRepository.deleteByName(name);
        return Optional.of(this.bookRepository.save(new Book(name, category, author, available)));
    }

    @Override
    public Optional<Book> save(BookDto BookDto) {

        this.bookRepository.deleteByName(BookDto.getName());
        Author author = authorService.findById(BookDto.getAuthor()).orElseThrow(AuthorIdNotVaildExcepion::new);
        return Optional.of(this.bookRepository.save(new Book(BookDto.getName(), BookDto.getCategory(), author, BookDto.getAvailableCopies())));
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, String name, Category category, Long authorId, Integer available) {

        Book book = this.bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new);

        book.setName(name);
        book.setAuthor(authorService.findById(authorId).orElseThrow(AuthorIdNotVaildExcepion::new));
        book.setCategory(category);
        book.setAvailableCopies(available);


        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> edit(Long id, BookDto BookDto) {
        Book Book = this.bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new);

        Book.setName(BookDto.getName());
        Book.setCategory(BookDto.getCategory());
        Book.setAuthor(authorService.findById(BookDto.getAuthor()).orElseThrow(AuthorIdNotVaildExcepion::new));
        Book.setAvailableCopies(BookDto.getAvailableCopies());


        return Optional.of(this.bookRepository.save(Book));
    }

    @Override
    public void deleteById(Long id) {
        if(bookRepository.findById(id).isPresent())
        this.bookRepository.deleteById(id);
        else throw new BookIdNotFoundException();

    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.bookRepository.findById(id).orElseThrow(BookIdNotFoundException::new);


        book.setAvailableCopies(book.getAvailableCopies()-1);


        return Optional.of(this.bookRepository.save(book));
    }

}
