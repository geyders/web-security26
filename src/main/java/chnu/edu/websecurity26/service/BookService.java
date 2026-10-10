package chnu.edu.websecurity26.service;

import chnu.edu.websecurity26.model.Book;
import chnu.edu.websecurity26.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    @PostConstruct
    void init() {
        // 1. Очищаємо базу при запуску
        bookRepository.deleteAll();

        // 2. Створюємо початкові дані
        List<Book> initialBooks = List.of(
                new Book("1", "name1", "author1", 2000),
                new Book("2", "name2", "author2", 1998),
                new Book("3", "name3", "author3", 2024)
        );

        // 3. ЗБЕРІГАЄМО В БАЗУ MONGODB
        bookRepository.saveAll(initialBooks);
    }

    public List<Book> getAllItems() {
        return bookRepository.findAll();
    }

    public Book createItem(Book book) {
        return bookRepository.save(book);
    }

    public Book getItem(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book updateItem(Book book) {
        return bookRepository.save(book);
    }

    public void deleteItem(String id) {
        bookRepository.deleteById(id);
    }
}