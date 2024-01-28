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
    String chatId;
    String firstName;
    String lastName;
    String phoneNumber;
    String age;
    String teacherName;
    String school;
    Integer classNumber;
    @Enumerated(EnumType.STRING)
    StepEnum stepEnum;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    List<Story> storyList;
}
