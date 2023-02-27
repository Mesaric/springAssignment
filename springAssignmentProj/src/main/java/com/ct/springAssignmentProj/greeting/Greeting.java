package com.ct.springAssignmentProj.greeting;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class Greeting {
    @SequenceGenerator(
            name = "greeting_sequence",
            sequenceName = "greeting_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "greeting_sequence"
    )

    private Long id;
    private String text;
    private String language;

    public Greeting(String text,
                    String language) {
        this.text = text;
        this.language = language;
    }
}
