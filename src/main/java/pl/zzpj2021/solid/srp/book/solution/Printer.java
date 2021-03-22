package pl.zzpj2021.solid.srp.book.solution;

import java.util.Map;

public class Printer {
    private final Book book;

    private int currentPage = 0;

    private String rowLocator;

    public Printer(Book book) {
        this.book = book;
    }

    public String getCurrentPageContents() {
        return book.getPages().get(currentPage);
    }

    public void turnPage() {
        currentPage++;
    }

    public void printCurrentPage() {
        System.out.println(book.getPages().get(currentPage));
    }

    public String getLocationRowLocator() {
        return rowLocator;
    }

    public String printAllPages() {

        StringBuilder allPages = new StringBuilder();
        for (Map.Entry<Integer, String> page : book.getPages().entrySet()) {
            allPages.append(page.getKey()).append(" ").append(page.getValue());
        }
        return allPages.toString();
    }
}
