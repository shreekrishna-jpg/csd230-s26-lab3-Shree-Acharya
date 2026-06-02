package csd230.s26.lab1.pojos;


public abstract class Stationery {
    private String brand;
    private boolean isEcoFriendly;

    public Stationery() {}

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public boolean isEcoFriendly() { return isEcoFriendly; }
    public void setEcoFriendly(boolean ecoFriendly) { this.isEcoFriendly = ecoFriendly; }
}