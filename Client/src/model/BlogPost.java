package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    private int id;
    private String title;
    private String content;
    private LocalDate lastEditedOn;
    private String author;
    private boolean enabled;
}
