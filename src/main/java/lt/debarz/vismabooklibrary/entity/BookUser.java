package lt.debarz.vismabooklibrary.entity;

import lombok.*;
import lt.debarz.vismabooklibrary.dto.UserDto;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BookUser {
    private String name;
    private String language;
    private String publishedDate;
    private String isbn10;
    private String isbn13;
    private String guid;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDate;
    private int reserveDays;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private String username;
    private String lastName;
}
