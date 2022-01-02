package lt.debarz.vismabooklibrary.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String lastName;

    @NotEmpty
    @Size.List({@Size(min = 1, max = 3, message = "maximum allowed to reserve quantity 3")})
    @OneToMany(	mappedBy="user", cascade= CascadeType.ALL, orphanRemoval = true)
    private Set<Reserved> reservedBooks = new HashSet<>();
}
