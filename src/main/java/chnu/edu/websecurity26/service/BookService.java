package chnu.edu.websecurity26.service;

import chnu.edu.websecurity26.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class BookService {
    private final List<Book> items = new ArrayList<>();

    {
        items.add(new Book("1", "name1", "author1", 2000));
        items.add(new Book("2", "name2", "autrho2", 1998));
        items.add(new Book("3", "name3", "author3",2024));
    }


    public List<Book> getAllItems() {
        return items;
    }
    public Book createItem(Book book){
        items.add(book);
        return book;
    }

    public Book getItem(String id){
        return items.stream()
                .filter(item-> item.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Book updateItem(Book book){
        Book oldBook = getItem(book.getId());
        items.remove(oldBook);
        items.add(book);
        return book;
    }


    public void deleteItem(String id){
        Book book = getItem(id);
        items.remove(book);
    }
}
