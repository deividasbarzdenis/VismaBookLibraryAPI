package lt.debarz.vismabooklibrary.dto;

import lombok.*;
import lt.debarz.vismabooklibrary.entity.Reserved;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UserDto {

    private Long Id;

    @NotBlank(message = "Username is mandatory")
    private String username;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    private Set<Reserved> reservedBooks = new HashSet<>();
}
