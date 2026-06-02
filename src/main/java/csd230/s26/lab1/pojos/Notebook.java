package csd230.s26.lab1.pojos;


public class Notebook extends Stationery {
    private int pageCount;
    private String paperRuling;

    public Notebook() {}

    public int getPageCount() { return pageCount; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }
    public String getPaperRuling() { return paperRuling; }
    public void setPaperRuling(String paperRuling) { this.paperRuling = paperRuling; }
}