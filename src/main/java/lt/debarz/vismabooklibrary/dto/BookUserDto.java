package lt.debarz.vismabooklibrary.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BookUserDto {

    private String name;
    private String language;
    private String publishedDate;
    private String isbn10;
    private String isbn13;
    private String guid;
    private ReservedDto reserved;
    private String username;
    private String lastName;
}
