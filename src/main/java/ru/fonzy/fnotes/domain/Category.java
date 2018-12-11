package ru.fonzy.fnotes.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category")
    private List<Note> notes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    public Category(String name, User author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
