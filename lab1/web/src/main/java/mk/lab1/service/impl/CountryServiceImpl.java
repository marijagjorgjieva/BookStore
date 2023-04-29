package mk.lab1.service.impl;

import mk.lab1.model.Country;
import mk.lab1.model.dto.CountryDto;
import mk.lab1.model.exceptions.CountryIdNotFoundException;
import mk.lab1.model.exceptions.CountryNameNotFoundException;
import mk.lab1.repository.CountryRepository;
import mk.lab1.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<Country> findById(Long id) {
        return Optional.ofNullable(countryRepository.findById(id).orElseThrow(CountryIdNotFoundException::new));
    }

    @Override
    public Optional<Country> findByName(String name) {
        return Optional.ofNullable(countryRepository.findByName(name).orElseThrow(CountryNameNotFoundException::new));
    }

    @Override
    public Optional<Country> save(String name, String Continent) {
        Country country = this.findByName(name).orElseThrow(CountryIdNotFoundException::new);
        countryRepository.delete(country);
        return Optional.of(countryRepository.save(new Country(name,Continent)));
    }

    @Override
    public Optional<Country> save(CountryDto CountryDto) {
        Country country = this.findByName(CountryDto.getName()).orElseThrow(CountryNameNotFoundException::new);
        countryRepository.delete(country);
        return Optional.of(countryRepository.save(new Country(CountryDto.getName(),CountryDto.getContinent())));
    }

    @Override
    public Optional<Country> edit(Long id, String name, String Continent) {
        Country country = this.findById(id).orElseThrow(CountryIdNotFoundException::new);
        country.setName(name);
        country.setContinent(Continent);
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public Optional<Country> edit(Long id, CountryDto CountryDto) {
        Country country = this.findById(id).orElseThrow(CountryIdNotFoundException::new);
        country.setName(CountryDto.getName());
        country.setContinent(CountryDto.getContinent());
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
    countryRepository.deleteById(id);
    }
}
