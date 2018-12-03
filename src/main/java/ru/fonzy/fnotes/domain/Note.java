package ru.fonzy.fnotes.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String text;

    @Enumerated(EnumType.STRING)
    private Importance importance;

    @ManyToOne
    @JoinColumn
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    public Note(String title, String text, Importance importance, Category category, User author) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.importance = importance;
        this.category = category;
    }

}
