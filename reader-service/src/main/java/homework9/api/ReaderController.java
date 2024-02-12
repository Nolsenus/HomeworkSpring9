package homework9.api;

import com.github.javafaker.Faker;
import homework9.Reader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reader")
public class ReaderController {

    private final List<Reader> readers;
    private final Faker faker;


    public ReaderController() {
        readers = new ArrayList<>();
        faker = new Faker();
        for (int i = 0; i < 15; i++) {
            Reader reader = new Reader();
            reader.setId(UUID.randomUUID());
            reader.setFirstName(faker.name().firstName());
            reader.setLastName(faker.name().lastName());
            readers.add(reader);
        }
    }

    @GetMapping
    public List<Reader> getAll() {
        return readers;
    }

    @GetMapping("/random")
    public Reader getRandom() {
        return readers.get(faker.number().numberBetween(0, readers.size()));
    }
}
