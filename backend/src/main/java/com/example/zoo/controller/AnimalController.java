package com.example.zoo.controller;

import com.example.zoo.model.Animal;
import com.example.zoo.service.AnimalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("animals")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public Iterable<Animal> index() {
        return animalService.all();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Animal create(@RequestBody Animal animal) {
        return animalService.create(animal);
    }

    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        animalService.remove(id);
    }
}
