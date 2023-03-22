package mk.lab1.service.impl;

import mk.lab1.model.Category;
import mk.lab1.model.Country;
import mk.lab1.model.dto.CountryDto;
import mk.lab1.model.exceptions.CountryIdNotFoundException;
import mk.lab1.model.exceptions.CountryNameNotFoundException;
import mk.lab1.repository.CountryRepository;
import mk.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(CountryIdNotFoundException::new);
    }

    @Override
    public Country findByName(String name) {
        return countryRepository.findByName(name).orElseThrow(CountryNameNotFoundException::new);
    }

    @Override
    public Country save(String name, String Continent) {
        Country country = this.findByName(name);
        countryRepository.delete(country);
        return countryRepository.save(new Country(name,Continent));
    }

    @Override
    public Country save(CountryDto CountryDto) {
        Country country = this.findByName(CountryDto.getName());
        countryRepository.delete(country);
        return countryRepository.save(new Country(CountryDto.getName(),CountryDto.getContinent()));
    }

    @Override
    public Country edit(Long id, String name, String Continent) {
        Country country = this.findById(id);
        country.setName(name);
        country.setContinent(Continent);
        return countryRepository.save(country);
    }

    @Override
    public Country edit(Long id, CountryDto CountryDto) {
        Country country = this.findById(id);
        country.setName(CountryDto.getName());
        country.setContinent(CountryDto.getContinent());
        return countryRepository.save(country);
    }

    @Override
    public void deleteById(Long id) {
    countryRepository.deleteById(id);
    }
}
