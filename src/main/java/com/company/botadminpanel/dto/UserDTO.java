package com.company.botadminpanel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    Integer id;
    String chatId;
    String firstName;
    String lastName;
    String phoneNumber;
    String age;
    String teacherName;
    String school;
    String classNumber;
}
