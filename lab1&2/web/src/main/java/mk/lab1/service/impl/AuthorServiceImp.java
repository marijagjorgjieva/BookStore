package mk.lab1.service.impl;

import mk.lab1.model.Author;
import mk.lab1.model.Country;
import mk.lab1.model.dto.AuthorDto;
import mk.lab1.model.exceptions.AuthorIdNotVaildExcepion;
import mk.lab1.model.exceptions.AuthorNameNotFoundException;
import mk.lab1.model.exceptions.CountryIdNotFoundException;
import mk.lab1.repository.AuthorRepository;
import mk.lab1.service.AuthorService;
import mk.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImp implements AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryService countryService;

    public AuthorServiceImp(AuthorRepository authorRepository, CountryService countryService) {
        this.authorRepository = authorRepository;
        this.countryService = countryService;
    }


    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return Optional.ofNullable(authorRepository.findById(id).orElseThrow(AuthorIdNotVaildExcepion::new));
    }

    @Override
    public Optional<Author> findByName(String name) {
        return Optional.ofNullable(authorRepository.findByName(name).orElseThrow(AuthorNameNotFoundException::new));
    }

    @Override
    public Optional<Author> save(String name, String surname, Long countryId) {
        Country country = countryService.findById(countryId).orElseThrow(CountryIdNotFoundException::new);

        this.authorRepository.deleteByName(name);
        return Optional.of(this.authorRepository.save(new Author(name, surname, country)));
    }

    @Override
    public Optional<Author> save(AuthorDto AuthorDto) {

        this.authorRepository.deleteByName(AuthorDto.getName());
        Country country = countryService.findById(AuthorDto.getCountry()).orElseThrow(CountryIdNotFoundException::new);
        return Optional.of(this.authorRepository.save(new Author(AuthorDto.getName(), AuthorDto.getSurname(), country)));
    }

    @Override
    public Optional<Author> edit(Long id, String name, String surname, Long countryId) {
        Author author = this.authorRepository.findById(id).orElseThrow(AuthorIdNotVaildExcepion::new);

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(countryService.findById(countryId).orElseThrow(CountryIdNotFoundException::new));

        return Optional.of(authorRepository.save(author));
    }

    @Override
    public Optional<Author> edit(Long id, AuthorDto AuthorDto) {
        Author author = this.authorRepository.findById(id).orElseThrow(AuthorIdNotVaildExcepion::new);

        author.setName(AuthorDto.getName());
        author.setSurname(AuthorDto.getSurname());
        author.setCountry(countryService.findById(AuthorDto.getCountry()).orElseThrow(CountryIdNotFoundException::new));

        return Optional.of(authorRepository.save(author));
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
