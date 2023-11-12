package com.example.zoo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonProperty("id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @JsonProperty("name")
    private String name;

    @Column(name = "is_predator", nullable = false)
    @JsonProperty("is_predator")
    private Boolean isPredator;

    public Animal(String name, Boolean isPredator) {
        this.name = name;
        this.isPredator = isPredator;
    }

    public Animal() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsPredator() {
        return isPredator;
    }

    public void setIsPredator(Boolean isPredator) {
        this.isPredator = isPredator;
    }
}
