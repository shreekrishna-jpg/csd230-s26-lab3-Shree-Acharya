package csd230.s26.lab1.entities;

import csd230.s26.lab1.entities.StationeryEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PEN")
public class PenEntity extends StationeryEntity {
    @Column(name = "ink_color")
    private String inkColor;

    @Column(name = "tip_size", nullable = true)
    private double tipSize;

    @Column(name = "brand")
    private String brand;

    @Column(name = "is_eco_friendly", nullable = true)
    private boolean isEcoFriendly;

    public String getInkColor() {
        return inkColor;
    }

    public void setInkColor(String inkColor) {
        this.inkColor = inkColor;
    }

    public double getTipSize() {
        return tipSize;
    }

    public void setTipSize(double tipSize) {
        this.tipSize = tipSize;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean getIsEcoFriendly() {
        return isEcoFriendly;
    }

    public void setIsEcoFriendly(boolean isEcoFriendly) {
        this.isEcoFriendly = isEcoFriendly;
    }

    @Override
    public void sellItem() {

    }

    @Override
    public double getPrice() {
        return 0;
    }
}
