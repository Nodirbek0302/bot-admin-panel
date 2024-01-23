package com.company.botadminpanel.model;

import com.company.botadminpanel.enums.StepEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    String chatId;
    String firstName;
    String lastName;
    String phoneNumber;
    String age;
    String teacherName;
    String school;
    String classNumber;
    @Enumerated(EnumType.STRING)
    StepEnum step;
}
