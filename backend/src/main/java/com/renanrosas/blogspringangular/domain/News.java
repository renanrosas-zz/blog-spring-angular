package com.renanrosas.blogspringangular.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Data
@EqualsAndHashCode(of = {"id"})
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
    @JsonIgnore
    @ElementCollection(targetClass=String.class)
    private List<String> tags;
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
