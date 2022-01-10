package domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Issue implements Comparable <Issue> {

    private int id;
    private String name;
    private boolean status;
    private String author;
    private String assignee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                status == issue.status &&
                name.equals(issue.name) &&
                author.equals(issue.author) &&
                assignee.equals(issue.assignee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, author, assignee);
    }

    @Override
    public int compareTo(Issue o) {
        return 0;
    }
}
