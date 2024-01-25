package com.company.botadminpanel.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoryDTO {
    Integer id;
    String body;
    String score;
    SectionDTO sectionDTO;
    UserDTO userDTO;
}
