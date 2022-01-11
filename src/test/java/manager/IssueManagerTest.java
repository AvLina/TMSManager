package manager;

import domain.Issue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.IssueRepository;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class IssueManagerTest {

    private IssueRepository repository = new IssueRepository();
    private IssueManager issueManager = new IssueManager(repository);

    private Issue one = new Issue(1111, "JUnit 4 migration tips should mention switched assertion message position", false,
            "Marcono1234", "No one assigned", Set.of("JUnit"));

    @BeforeEach
    public void setUp() {
        issueManager.add(one);
    }

    @Test
    public void statusOpenWithNotFoundValue() {
        List<Issue> expected = List.of();
        List<Issue> actual = (List<Issue>) issueManager.statusOpen();

        assertEquals(expected, actual);
    }

    @Test
    public void shouldRemoveById() {
        issueManager.removeById(1111);
        List<Issue> expected = List.of();
        List<Issue> actual = (List<Issue>) repository.findAll();

        assertEquals(expected, actual, "Удаление Issue по id");
    }

    @Test
    public void shouldClosingAndOpeningIssueById() {
        issueManager.closingAndOpeningIssueById(1111);
        assertTrue(repository.findById(1111).isStatus(), "Открытие Issue по id");

        issueManager.closingAndOpeningIssueById(1111);
        assertFalse(repository.findById(1111).isStatus(), "Закрытие Issue по id");
    }

    @Test
    public void shouldFilterByAuthorWithSingleValue() {
        List<Issue> expected = List.of(one);
        List<Issue> actual = (List<Issue>) issueManager.filterByAuthor("Marcono1234");

        assertEquals(expected, actual);
    }

}