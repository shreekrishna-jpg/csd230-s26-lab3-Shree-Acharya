package csd230.s26.lab1.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PUBLICATION")
public abstract class PublicationEntity extends ProductEntity {
    private String title;

    public PublicationEntity() {
        setTitle("Publication");
        setPrice(0.0);
        setCopies(0);
    }

    public PublicationEntity(String title, double price, int copies) {
        setTitle(title);
        setPrice(price);
        setCopies(copies);
    }

    // Getters and Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
}