package com.enes.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024)
    private String content;

    @Column(name = "published_at")
    @Builder.Default
    private LocalDate publishedat = LocalDate.now();

    @ManyToOne
    private Post post;

    @OneToOne
    private User user;
}
