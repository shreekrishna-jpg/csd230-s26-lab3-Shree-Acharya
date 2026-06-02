package csd230.s26.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass // Changed from @Entity to avoid Single-Table collision layers
public abstract class StationeryEntity extends ProductEntity {

    @Column(name = "brand")
    private String brand;

    @Column(name = "is_eco_friendly")
    private boolean isEcoFriendly;

    // 💡 REMOVED: price and copies because they are inherited directly from ProductEntity

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public boolean getIsEcoFriendly() { return isEcoFriendly; }
    public void setIsEcoFriendly(boolean isEcoFriendly) { this.isEcoFriendly = isEcoFriendly; }
}