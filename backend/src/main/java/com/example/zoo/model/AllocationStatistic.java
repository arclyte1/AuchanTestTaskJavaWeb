package com.example.zoo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "allocation_statistic")
@IdClass(AllocationStatisticId.class)
public class AllocationStatistic {

    @Id
    @Column(name = "first_animal_id", nullable = false)
    private Long firstAnimalId;

    @Id
    @Column(name = "second_animal_id", nullable = false)
    private Long secondAnimalId;

    @Column(name = "count", nullable = false)
    private Integer count;

    public AllocationStatistic(Long firstAnimalId, Long secondAnimalId, Integer count) {
        this.firstAnimalId = firstAnimalId;
        this.secondAnimalId = secondAnimalId;
        this.count = count;
    }

    public AllocationStatistic() { }

    public Long getFirstAnimalId() {
        return firstAnimalId;
    }

    public void setFirstAnimalId(Long firstAnimalId) {
        this.firstAnimalId = firstAnimalId;
    }

    public Long getSecondAnimalId() {
        return secondAnimalId;
    }

    public void setSecondAnimalId(Long secondAnimalId) {
        this.secondAnimalId = secondAnimalId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
