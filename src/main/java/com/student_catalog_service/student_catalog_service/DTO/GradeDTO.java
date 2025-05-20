package com.student_catalog_service.student_catalog_service.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GradeDTO {
    private Long id;
    private double grade;
    private Long StudentId;
    private String courseCode;
}
