package lt.debarz.vismabooklibrary.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique=true, nullable = false)
    private String name;

    @Column(name="last_name", unique=true, nullable = false)
    private String lastName;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String aboutAuthor;

}
