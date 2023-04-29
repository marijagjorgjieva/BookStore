package mk.lab1.Init;


import mk.lab1.model.Author;
import mk.lab1.model.Book;
import mk.lab1.model.Category;
import mk.lab1.model.Country;
import mk.lab1.repository.AuthorRepository;
import mk.lab1.repository.BookRepository;
import mk.lab1.repository.CountryRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInit {
    private final CountryRepository countryRepository;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public DataInit(CountryRepository countryRepository, AuthorRepository authorRepository, BookRepository bookRepository) {
        this.countryRepository = countryRepository;
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void init() {
        if (countryRepository.count() == 0 && authorRepository.count() == 0 && bookRepository.count() == 0) {
            for (int i = 0; i < 20; i++) {
                Country c = new Country();
                c.setName(String.format("Country %d", i));
                c.setContinent(String.format("Continent %d", i));
                countryRepository.save(c);

                Author a = new Author();
                a.setName(String.format("Name %d", i));
                a.setSurname(String.format("Surname %d", i));
                a.setCountry(c);
                authorRepository.save(a);

                Book b = new Book();
                b.setName(String.format("Name %d", i));
                b.setCategory(Category.values()[i % Category.values().length]);
                b.setAuthor(a);
                b.setAvailableCopies(i);
                bookRepository.save(b);
            }
        }


    }

}