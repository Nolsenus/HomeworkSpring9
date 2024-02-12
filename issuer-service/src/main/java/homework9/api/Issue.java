package homework9.api;

import homework9.Book;
import homework9.Reader;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class Issue {

    private UUID id;
    private LocalDate issuedAt;
    private Book book;
    private Reader reader;
}
