package homework9.api;

import com.github.javafaker.Faker;
import homework9.Author;
import homework9.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final List<Book> books;
    private final Faker faker;


    public BookController() {
        books = new ArrayList<>();
        faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Book book = new Book();
            book.setId(UUID.randomUUID());
            book.setName(faker.book().title());

            Author author = new Author();
            author.setId(UUID.randomUUID());
            author.setFirstName(faker.name().firstName());
            author.setLastName(faker.name().lastName());

            book.setAuthor(author);

            books.add(book);
        }
    }

    @GetMapping()
    public List<Book> getAll() {
        return books;
    }

    @GetMapping("/random")
    public Book getRandom() {
        return books.get(faker.number().numberBetween(0, books.size()));
    }

}
