package com.ct.springAssignmentProj.translation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Translation {
    @SequenceGenerator(
            name = "translation_sequence",
            sequenceName = "translation_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "translation_sequence"
    )

    private Long id;
    @Size(max = 127, message = "Translation's text is limited to 127 characters.")
    @NotEmpty(message = "Translation's text cannot be empty.")
    private String text;
    @Size(min = 2, max = 2, message = "Translation's language code should be 2 characters long.")
    @NotEmpty(message = "Translation's language code cannot be empty.")
    private String language;

    public Translation(String text,
                       String language) {
        this.text = text;
        this.language = language;
    }
}
