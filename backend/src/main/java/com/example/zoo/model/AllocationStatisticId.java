package com.example.zoo.model;

import java.io.Serializable;

public class AllocationStatisticId implements Serializable {
    private Long firstAnimalId;
    private Long secondAnimalId;

    public AllocationStatisticId(Long firstAnimalId, Long secondAnimalId) {
        this.firstAnimalId = firstAnimalId;
        this.secondAnimalId = secondAnimalId;
    }

    public AllocationStatisticId() { }

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
}
