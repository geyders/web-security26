package chnu.edu.websecurity26.controller;

/*
 @author Lenovo
 @project web-security26
 @class BookController
 @version 1.0.0
 @since 9/21/2026 - 17.46
*/
import chnu.edu.websecurity26.model.Book;
import chnu.edu.websecurity26.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor

public class BookController {
    private final BookService bookService;

    @GetMapping
    public List<Book> getAll(){
        return bookService.getAllItems();
    }

    @GetMapping("/{id}")
    public Book getItem(@PathVariable String id){
        return bookService.getItem(id);
    }

    @PostMapping
    public Book createItem(@RequestBody Book book){
        return bookService.createItem(book);
    }

    @PutMapping
    public Book updateItem(@RequestBody Book book){
        return bookService.updateItem(book);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable String id){
        bookService.deleteItem(id);
    }


//    roles
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("hello/admin")
    public String helloAdmin() {
        return "Hello Admin!";
    }

    @GetMapping("hello/unknown")
    public String helloUnknown() {
        return "Hello Unknown!";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/hello/user")
    public String hello() { return "Hello! User"; }


    @PreAuthorize("hasAnyRole('ADMIN', 'SUPERADMIN')")
    @GetMapping("/hello/man")
    public String helloMan() { return "Hello, man!"; }

}
