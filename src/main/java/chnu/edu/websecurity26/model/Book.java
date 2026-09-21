package chnu.edu.websecurity26.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString

public class Book {
    private String id;
    private String title;
    private String author;
    private Integer year;
}
