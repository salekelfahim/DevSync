package org.sicario.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sicario.model.enums.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinTable(name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private List<Tag> tags;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "assignee_id", nullable = false )
    private User assignee;

    private boolean isRefused;

    @OneToMany(mappedBy = "task", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Request> requests;

    public Task(String title, String description, LocalDate creationDate, LocalDate dueDate, TaskStatus status, List<Tag> tags, User creator, Boolean isRefused) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.status = status;
        this.tags = tags != null ? tags : new ArrayList<>();
        this.creator = creator;
        this.isRefused = isRefused;
    }
}
