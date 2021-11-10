package com.codegym.dethimodule4.controller;

import com.codegym.dethimodule4.model.City;
import com.codegym.dethimodule4.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("city")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping
    public ResponseEntity<Iterable<City>> showAll() {
        return new ResponseEntity<>(cityService.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<City> findOneCity(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if(!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cityOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<City> addProduct(@RequestBody City city) {
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<City> editProduct(@PathVariable Long id, @RequestBody City city) {
        Optional<City> cityOptional = cityService.findById(id);
        if(!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            if(city.getId() != null) {
                city.setId(id);
            }
            return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<City> deleteProduct(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findById(id);
        if(!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else  {
            cityService.remove(id);
            return new ResponseEntity<>(cityOptional.get(), HttpStatus.OK);
        }
    }

}
