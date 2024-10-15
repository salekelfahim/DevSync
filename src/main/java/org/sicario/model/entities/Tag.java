package org.sicario.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "tags", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Tag(String name, List<Task> tasks) {
        this.name = name;
        this.tasks = tasks == null ? new ArrayList<Task>() : tasks;
    }

    public Tag(String name) {
        this.name = name;
    }
}
