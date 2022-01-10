package manager;

import domain.Issue;
import domain.NotFoundException;
import repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

public class IssueManager {

    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void add (Issue item) {
        repository.save(item);
    }

    public void removeById (int id) {
        repository.removeById(id);
    }

    public Collection<Issue> statusOpen() {
        List<Issue> result = new ArrayList();
        for (Issue tmp : repository.findAll()) {
            if (tmp.isStatus()) {
                result.add(tmp);
            }
        }
        return result;
    }

    public Collection<Issue> statusClose() {
        List<Issue> result = new ArrayList();
        for (Issue tmp : repository.findAll()) {
            if (!tmp.isStatus()) {
                result.add(tmp);
            }
        }
        return result;
    }

    public Collection<Issue> filterByAuthor(String author) {
        return filterBy(issue -> issue.getAuthor().equals(author));
    }

    public Collection<Issue> filterByAssignee(String assignee) {
        return filterBy(issue -> issue.getAssignee().equals(assignee));
    }

    public void closingAndOpeningIssueById(int id) {
        Issue tmp = repository.findById(id);
        if (tmp == null) {
            throw new NotFoundException("Element with id: " + id + " not found");
        }
        if (tmp.isStatus()) {
            repository.findById(id).setStatus(false);
        } else {
            repository.findById(id).setStatus(true);
        }
    }

    private Collection<Issue> filterBy(Predicate<Issue> filter) {
        List<Issue> result = new ArrayList<>();
        for (Issue tmp : repository.findAll()) {
            if (filter.test(tmp)) {
                result.add(tmp);
            }
        }
        return result;
    }
}
