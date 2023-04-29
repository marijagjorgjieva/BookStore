package mk.lab1.web_rest;


import mk.lab1.model.Country;
import mk.lab1.model.dto.CountryDto;
import mk.lab1.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> listAll() {
        return this.countryService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<Country> create(CountryDto CountryDto) {
        return this.countryService.save(CountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Country> update(@PathVariable Long id,  CountryDto CountryDto) {
        return this.countryService.edit(id, CountryDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Country> delete(@PathVariable Long id) {
        if (!countryService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            Country Country = countryService.findById(id).get();
            countryService.deleteById(id);
            return ResponseEntity.ok(Country);
        }
    }
}