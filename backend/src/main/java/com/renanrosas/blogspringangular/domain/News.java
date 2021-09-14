package com.renanrosas.blogspringangular.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Set;

@Builder
@Data
@Entity
@Table(name = "tb_news")
public class News implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column
    private String title;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant date;
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String content;
    @NotBlank
    @Column
    private String authorName;
    @ElementCollection(targetClass=String.class)
    private List<String> tags;
    @OneToMany
    private Set<Comment> comments;
}
