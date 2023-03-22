package mk.lab1.service;

import mk.lab1.model.Category;
import mk.lab1.model.Country;
import mk.lab1.model.dto.CountryDto;
import mk.lab1.model.dto.CountryDto;


import java.util.List;


public interface CountryService {
    List <Country> findAll();

    Country findById(Long id);

    Country findByName(String name);

    Country save(String name, String Continent);

    Country save(CountryDto CountryDto);

    Country edit(Long id,String name, String Continent);

    Country edit(Long id, CountryDto CountryDto);

    void deleteById(Long id);
}
