package ru.fonzy.fnotes.domain;

import jdk.nashorn.internal.objects.annotations.Constructor;
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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    public Note(String title, String text, User author) {
        this.title = title;
        this.text = text;
        this.author = author;
    }

}
