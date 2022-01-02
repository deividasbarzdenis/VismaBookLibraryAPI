package lt.debarz.vismabooklibrary.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reserved {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Book book;


    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @Column(name="reserve_date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate reserveDate = LocalDate.now();

    @Column(nullable = false)
    @Min(value = 1, message = "Book reservation are required at least for one day")
    @Max(value = 60, message = "Book reservation are allowed for 60 days")
    private int reserveDays;

    @Column(name="due_date")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @Column(name="return_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;


    public void setDueDate(LocalDate dueDate) {
        this.dueDate = reserveDate.plusDays(reserveDays);
    }

}
