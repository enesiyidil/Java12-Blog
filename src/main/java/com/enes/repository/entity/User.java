package com.enes.repository.entity;

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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firts_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    private String password;

    private String email;

    private String img;

    private LocalDate birthdate;

    @Builder.Default
    private LocalDate registerdate = LocalDate.now();

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
