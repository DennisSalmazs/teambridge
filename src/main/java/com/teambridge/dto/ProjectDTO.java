package com.teambridge.dto;

import com.teambridge.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDTO {

    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String projectDetail;
    private Status projectStatus;
}
