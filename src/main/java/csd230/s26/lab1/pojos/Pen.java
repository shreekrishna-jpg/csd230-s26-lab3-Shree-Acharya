package csd230.s26.lab1.pojos;

public class Pen extends Stationery {
    private String inkColor;
    private double tipSize;

    public Pen() {}

    public String getInkColor() { return inkColor; }
    public void setInkColor(String inkColor) { this.inkColor = inkColor; }
    public double getTipSize() { return tipSize; }
    public void setTipSize(double tipSize) { this.tipSize = tipSize; }
}