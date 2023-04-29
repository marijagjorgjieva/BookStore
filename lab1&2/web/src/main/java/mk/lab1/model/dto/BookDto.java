package mk.lab1.model.dto;

import lombok.Data;
import mk.lab1.model.Category;

@Data
public class BookDto {
    private String name;

    private Category category;

    private Long author;

    private Integer availableCopies;
}
