package com.example.zoo.repository;

import com.example.zoo.model.AllocationStatistic;
import com.example.zoo.model.AllocationStatisticId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationStatisticRepository extends CrudRepository<AllocationStatistic, AllocationStatisticId> { }
