package com.codegym.dethimodule4.controller;

import com.codegym.dethimodule4.model.Country;
import com.codegym.dethimodule4.service.country.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("countries")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @GetMapping
    public ResponseEntity<Iterable<Country>> showAll() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Country> created(@RequestBody Country country){
        return new ResponseEntity<>(countryService.save(country),HttpStatus.CREATED);
    }
    @PutMapping("/id")
    public ResponseEntity<Country> edit( @PathVariable("id") Long id , @RequestBody Country country ){
        Optional<Country> optionalCountry = countryService.findById(id);
        if (!optionalCountry.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(countryService.save(country),HttpStatus.CREATED);
    }
    @DeleteMapping("/id")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        Optional<Country> countryOptional = countryService.findById(id);
        if (!countryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        countryService.remove(id);
        return new ResponseEntity<>(countryOptional.get(),HttpStatus.OK);
    }
}
