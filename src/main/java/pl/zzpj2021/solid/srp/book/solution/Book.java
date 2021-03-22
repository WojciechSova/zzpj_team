package pl.zzpj2021.solid.srp.book.solution;

import java.util.HashMap;
import java.util.Map;

public class Book {

    private Map<Integer, String> pages = new HashMap<>();

    private String libraryRoomName;

    private int indexOnShelf;

    public String getTitle() {
        return "A Great Book";
    }

    public String getAuthor() {
        return "John Doe";
    }

    public Map<Integer, String> getPages() {
        return pages;
    }

    public String getLibraryRoomName() {
        return libraryRoomName;
    }

    public int getIndexOnShelf() {
        return indexOnShelf;
    }

}
