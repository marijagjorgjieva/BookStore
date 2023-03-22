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
    public Author findById(Long id) {
        return authorRepository.findById(id).orElseThrow(AuthorIdNotVaildExcepion::new);
    }

    @Override
    public Author findByName(String name) {
        return authorRepository.findByName(name).orElseThrow(AuthorNameNotFoundException::new);
    }

    @Override
    public Author save(String name, String surname, Long countryId) {
        Country country = countryService.findById(countryId);

        this.authorRepository.deleteByName(name);
        return this.authorRepository.save(new Author(name, surname, country));
    }

    @Override
    public Author save(AuthorDto AuthorDto) {

        this.authorRepository.deleteByName(AuthorDto.getName());
        Country country = countryService.findById(AuthorDto.getCountry());
        return this.authorRepository.save(new Author(AuthorDto.getName(), AuthorDto.getSurname(), country));
    }

    @Override
    public Author edit(Long id, String name, String surname, Long countryId) {
        Author author = this.authorRepository.findById(id).orElseThrow(AuthorIdNotVaildExcepion::new);

        author.setName(name);
        author.setSurname(surname);
        author.setCountry(countryService.findById(countryId));

        return authorRepository.save(author);
    }

    @Override
    public Author edit(Long id, AuthorDto AuthorDto) {
        Author author = this.authorRepository.findById(id).orElseThrow(AuthorIdNotVaildExcepion::new);

        author.setName(AuthorDto.getName());
        author.setSurname(AuthorDto.getSurname());
        author.setCountry(countryService.findById(AuthorDto.getCountry()));

        return authorRepository.save(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
