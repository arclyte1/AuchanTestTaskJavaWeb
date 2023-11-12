package com.example.zoo.service;

import com.example.zoo.model.Animal;
import com.example.zoo.repository.AnimalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnimalService {

    private final AnimalRepository repository;

    public AnimalService(AnimalRepository repository) {
        this.repository = repository;
    }

    public Iterable<Animal> all() {
        return repository.findAll();
    }

    public Animal create(Animal animal) {
        return repository.save(animal);
    }

    public void remove(Long id) {
        repository.deleteById(id);
    }

    public boolean allHaveSameIsPredator(List<Long> ids) {
        if (ids.isEmpty()) return true;
        return ids.stream().map(
            (id) -> {
                return repository.findById(id).orElse(null);
            }
        ).filter(Objects::nonNull).map(Animal::getIsPredator).distinct().limit(2).count() <= 1;
    }
}
