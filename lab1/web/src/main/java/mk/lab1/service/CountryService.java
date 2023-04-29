package mk.lab1.service;

import mk.lab1.model.Country;
import mk.lab1.model.dto.CountryDto;


import java.util.List;
import java.util.Optional;


public interface CountryService {
    List <Country> findAll();

    Optional<Country> findById(Long id);

    Optional<Country> findByName(String name);

    Optional<Country> save(String name, String Continent);

    Optional<Country> save(CountryDto CountryDto);

    Optional<Country> edit(Long id,String name, String Continent);

    Optional<Country> edit(Long id, CountryDto CountryDto);

    void deleteById(Long id);
}
