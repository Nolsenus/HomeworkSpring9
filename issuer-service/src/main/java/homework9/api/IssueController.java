package homework9.api;

import com.github.javafaker.Faker;
import homework9.BookProvider;
import homework9.ReaderProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.util.*;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    private final List<Issue> issues;
    private final Faker faker;
    private final BookProvider bookProvider;
    private final ReaderProvider readerProvider;

    public IssueController(BookProvider bookProvider, ReaderProvider readerProvider) {
        faker = new Faker();
        issues = new ArrayList<>();
        this.bookProvider = bookProvider;
        this.readerProvider = readerProvider;
        refreshData();
    }

    @GetMapping
    public List<Issue> getAll() {
        return issues;
    }

    @GetMapping("/refresh")
    public List<Issue> refresh() {
        refreshData();
        return issues;
    }

    private void refreshData() {
        issues.clear();
        for (int i = 0; i < 15; i++) {
            Issue issue = new Issue();
            issue.setId(UUID.randomUUID());
            Date date = faker.date().between(startOfYear(), endOfYear());
            issue.setIssuedAt(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            issue.setBook(bookProvider.getRandomBook());
            issue.setReader(readerProvider.getRandomReader());

            issues.add(issue);
        }
    }

    private Date startOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    private Date endOfYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2024);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        return calendar.getTime();
    }
}
