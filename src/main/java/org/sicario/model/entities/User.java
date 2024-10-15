package org.sicario.model.entities;

import jakarta.persistence.*;
import lombok.*;
import org.sicario.model.enums.UserRole;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private int tokenDelete;
    private int tokenRefuse;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Request> requests;

    public User(String firstName, String lastName, String email, String password, UserRole role, int tokenDelete, int tokenRefuse) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.tokenDelete = tokenDelete;
        this.tokenRefuse = tokenRefuse;
    }

    public User(String firstName, String lastName, String email, String password, UserRole role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
