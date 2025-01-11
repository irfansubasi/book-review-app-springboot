package com.bookreview.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
    @NotNull(message = "title cannot be null")
    @NotBlank(message = "title cannot be blank")
    private String title;

    @Column(name = "author")
    @Size(min = 1, max = 255, message = "Author must be between 1 and 255 characters")
    @NotNull(message = "author cannot be null")
    @NotBlank(message = "author cannot be blank")
    private String author;

    @Column(name = "description")
    private String description;

}
