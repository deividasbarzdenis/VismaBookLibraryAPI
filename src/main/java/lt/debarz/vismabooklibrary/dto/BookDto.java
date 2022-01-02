package lt.debarz.vismabooklibrary.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BookDto {

    private Long Id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private Set<AuthorDto> authors = new HashSet<>();
    private Set<CategoryDto> categories = new HashSet<>();

    @NotBlank(message = "Language is mandatory")
    private String language;

    @NotBlank(message = "Published date is mandatory")
    private String publishedDate;

    @NotBlank(message = "ISBN-10 number is mandatory")
    @Pattern(regexp = "^(?:ISBN(?:-10)?:? )?(?=[0-9X]{10}$|(?=(?:[0-9]+[- ]){3})\n" +
            "[- 0-9X]{13}$)[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9X]$", message = "It should be 10 characters")
    private String isbn10;

    @NotBlank(message = "ISBN-13 number is mandatory")
    @Pattern(regexp = "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)\n" +
            "97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$", message = "It should be 13 characters")
    private String isbn13;

    @NotBlank(message = "GUID number is mandatory")
    @Pattern(regexp = "^[{]?[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}[}]?$",
            message = "It should be 36 characters")
    private String guid;

    private ReservedDto reserved;
}
