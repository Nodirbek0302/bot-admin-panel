package com.company.botadminpanel.model;

import com.company.botadminpanel.enums.StepEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(nullable = false,unique = true)
    String chatId;
    String firstName;
    String lastName;
    @Column(nullable = false,unique = true)
    String phoneNumber;
    @Column(nullable = false)
    String age;
    @Column(nullable = false)
    String teacherName;
    String school;
    String classNumber;
    @Enumerated(EnumType.STRING)
    StepEnum step;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Story> storyList;
}
