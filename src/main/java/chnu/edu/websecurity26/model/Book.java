package chnu.edu.websecurity26.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 @author Lenovo
 @project web-security26
 @class SecurityConfig
 @version 1.0.0
 @since 10/10/2026 - 19.55
*/

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Book extends Auditable {
    @Id
    @EqualsAndHashCode.Include // Порівняння відбуватиметься тільки за id
    private String id;

    private String title;
    private String author;
    private Integer year;
}