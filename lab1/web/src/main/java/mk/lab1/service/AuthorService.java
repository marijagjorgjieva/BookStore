package mk.lab1.service;

import mk.lab1.model.Author;
import mk.lab1.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Optional<Author> findById(Long id);

    Optional<Author> findByName(String name);

    Optional<Author> save(String name,  String surname, Long countryId);

    Optional<Author> save(AuthorDto AuthorDto);

    Optional<Author> edit(Long id, String name, String surname, Long countryId);

    Optional<Author> edit(Long id, AuthorDto AuthorDto);

    void deleteById(Long id);
}
