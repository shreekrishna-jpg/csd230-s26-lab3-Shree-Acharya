package csd230.s26.lab1.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("NOTEBOOK")
public class NotebookEntity extends StationeryEntity {

    @Column(name = "page_count")
    private int pageCount;

    @Column(name = "paper_ruling")
    private String paperRuling;


    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getPaperRuling() {
        return paperRuling;
    }

    public void setPaperRuling(String paperRuling) {
        this.paperRuling = paperRuling;
    }

    // --- Added to fix the 'sellItem()' error ---
    @Override
    public void sellItem() {
        System.out.println("Selling notebook: " + this.getBrand() + " (" + this.paperRuling + ")");
    }

    // FIX: Return the inherited base price field instead of always returning 0!
    @Override
    public double getPrice() {
        return super.getPrice();
    }

    // FIX: Satisfy the notebook.setTitle() method call in your test suite
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}