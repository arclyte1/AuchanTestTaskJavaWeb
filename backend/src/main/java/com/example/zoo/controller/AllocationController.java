package com.example.zoo.controller;

import com.example.zoo.model.AllocationStatistic;
import com.example.zoo.model.Animal;
import com.example.zoo.service.AllocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("allocations")
public class AllocationController {

    private final AllocationService allocationService;

    public AllocationController(AllocationService allocationService) {
        this.allocationService = allocationService;
    }

    @GetMapping("generate")
    public List<List<Animal>> generateAllocations(@RequestParam("aviary_count") int aviaryCount) {
        // Limits (1 <= aviaryCount <= 1000)
        if (aviaryCount <= 0) {
            aviaryCount = 1;
        } else if (aviaryCount > 1000) {
            aviaryCount = 1000;
        }
        return allocationService.generateAllocation(aviaryCount);
    }

    @PostMapping
    public void saveAnimalAllocation(@RequestBody List<List<Long>> allocation) {
        allocationService.saveAllocationStatistics(allocation);
    }
}
