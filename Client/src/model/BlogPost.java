package model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class BlogPost {
    private UUID userId;
    private String title;
    private String content;
    private LocalDate lastEditedOn;
    private String author;
    private boolean enabled;
}
