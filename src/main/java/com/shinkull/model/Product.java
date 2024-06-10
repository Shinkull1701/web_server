package com.shinkull.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="id",nullable = false)
    private Long id;

    @Column(name = "name ",nullable = false,unique = true)
    private String name;

    @Column(name="short_description",nullable = false)
    private String shotDescription;

    @Column(name="lhort_description",nullable = false)
    private String lhotDescription;

    @Column(name="price",nullable = false)
    private Double price;

    @OneToOne(mappedBy = "product",cascade = CascadeType.REMOVE,optional = false,orphanRemoval = true)
    private Inventory inventory;

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

    public String getShotDescription() {
        return shotDescription;
    }

    public void setShotDescription(String shotDescription) {
        this.shotDescription = shotDescription;
    }

    public String getLhotDescription() {
        return lhotDescription;
    }

    public void setLhotDescription(String lhotDescription) {
        this.lhotDescription = lhotDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    
}
