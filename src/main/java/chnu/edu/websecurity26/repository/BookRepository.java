package chnu.edu.websecurity26.repository;

/*
 @author Lenovo
 @project web-security26
 @class BookRepository
 @version 1.0.0
 @since 10/5/2026 - 20.07
*/

import chnu.edu.websecurity26.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

}
