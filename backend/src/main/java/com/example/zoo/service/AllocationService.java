package com.example.zoo.service;

import com.example.zoo.model.AllocationStatistic;
import com.example.zoo.model.AllocationStatisticId;
import com.example.zoo.model.Animal;
import com.example.zoo.repository.AllocationStatisticRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

@Service
public class AllocationService {

    private final AllocationStatisticRepository allocationStatisticRepository;
    private final AnimalService animalService;

    public AllocationService(
        AllocationStatisticRepository allocationStatisticRepository,
        AnimalService animalService
    ) {
        this.allocationStatisticRepository = allocationStatisticRepository;
        this.animalService = animalService;
    }

    public void saveAllocationStatistics(List<List<Long>> allocations) {
        allocations = allocations.stream().filter((l) -> {
            return l.size() == 2;
        }).toList();

        // Check if animals have same isPredator for each aviary
        if (allocations.stream().allMatch(animalService::allHaveSameIsPredator)) {
            var ids = allocations.stream().map((aviary) -> {
                return new AllocationStatisticId(
                    aviary.stream().min(Long::compareTo).get(),
                    aviary.stream().max(Long::compareTo).get()
                );
            }).toList();

            for (var id : ids) {
                var statistics = allocationStatisticRepository.findById(id);
                if (statistics.isPresent()) {
                    var s = statistics.get();
                    s.setCount(s.getCount() + 1);
                    allocationStatisticRepository.save(s);
                } else {
                    allocationStatisticRepository.save(new AllocationStatistic(id.getFirstAnimalId(), id.getSecondAnimalId(), 1));
                }
            }
        }
    }

    public List<List<Animal>> generateAllocation(int aviaryCount) {
        var availableAnimals = (List<Animal>) animalService.all();
        var statistics = (List<AllocationStatistic>) allocationStatisticRepository.findAll();
        statistics.sort(Comparator.comparing(AllocationStatistic::getCount).reversed());

        var allocations = new ArrayList<List<Animal>>();
        while (aviaryCount > 0) {
            var allocation = new ArrayList<Animal>();
            if (availableAnimals.size() > 0) {
                // Try to add from statistics
                while (!statistics.isEmpty()) {
                    var statistic = statistics.remove(0);
                    var finds = availableAnimals.stream().filter((it) -> {
                        return it.getId().equals(statistic.getFirstAnimalId()) || it.getId().equals(statistic.getSecondAnimalId());
                    }).toList();
                    if (finds.size() == 2) {
                        allocation.addAll(finds);
                        availableAnimals.removeAll(finds);
                        break;
                    }
                }
                // No statistics available
                if (allocation.size() == 0) {
                    var predators = availableAnimals.stream().filter(Animal::getIsPredator).limit(2).toList();
                    var notPredators = availableAnimals.stream().filter(Predicate.not(Animal::getIsPredator)).limit(2).toList();
                    if (predators.size() > notPredators.size()) {
                        allocation.addAll(predators);
                        availableAnimals.removeAll(predators);
                    } else {
                        allocation.addAll(notPredators);
                        availableAnimals.removeAll(notPredators);
                    }
                }
            }
            allocations.add(allocation);
            aviaryCount--;
        }
        return allocations;
    }
}
