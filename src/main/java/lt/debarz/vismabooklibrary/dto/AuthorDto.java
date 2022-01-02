package lt.debarz.vismabooklibrary.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class AuthorDto {

    private Long id;
    @NotBlank(message = "Author name is mandatory")
    private String name;
    @NotBlank(message = "Author last name is mandatory")
    private String lastName;
    @NotBlank(message = "About author is mandatory")
    private String aboutAuthor;
}
