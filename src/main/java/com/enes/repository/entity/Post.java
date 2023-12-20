package com.enes.repository.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 256)
    private String title;

    @Column(length = 2048)
    private String content;

    @Column(name = "published_at")
    @Builder.Default
    private LocalDate publishedat = LocalDate.now();

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    @OneToOne(fetch = FetchType.EAGER)
    private Category category;

    @ManyToMany
    @JsonIgnore
    private List<User> likes;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<Comment> comments;

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", publishedat=" + publishedat +
                ", user=" + user.getFirstname() + " " + user.getLastname() +
                ", category=" + category.getName() +
                ", likes=" + likes.stream().map(user -> user.getFirstname() + " " + user.getLastname()).toList() +
                ", comments=" + comments.stream().map(comment -> comment.getUser().getFirstname() + " " + comment.getUser().getLastname() + "\n" + comment.getContent()) +
                '}';
    }
}
