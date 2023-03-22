package mk.lab1.service;

import mk.lab1.model.Author;
import mk.lab1.model.Category;
import mk.lab1.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();

    Author findById(Long id);

    Author findByName(String name);

    Author save(String name,  String surname, Long countryId);

    Author save(AuthorDto AuthorDto);

    Author edit(Long id, String name, String surname, Long countryId);

    Author edit(Long id, AuthorDto AuthorDto);

    void deleteById(Long id);
}
