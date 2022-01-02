package lt.debarz.vismabooklibrary.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private Long id;
    @NotBlank(message = "Category name date is mandatory")
    private String name;

}
