package com.student_catalog_service.student_catalog_service.controller;

import com.student_catalog_service.student_catalog_service.DTO.CourseDTO;
import com.student_catalog_service.student_catalog_service.DTO.GradeDTO;
import com.student_catalog_service.student_catalog_service.DTO.StudentDTO;
import com.student_catalog_service.student_catalog_service.model.Catalog;
import com.student_catalog_service.student_catalog_service.model.StudentGrade;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{courseCode}")
    public ResponseEntity<?>getCatalog(@PathVariable String courseCode) {
        CourseDTO course = restTemplate.getForObject("http://course-service/api/course/" + courseCode, CourseDTO.class);
        GradeDTO[] grades=restTemplate.getForObject("http://grade-service/api/grades/" + courseCode, GradeDTO[].class);
        if (course == null || grades == null) {
            return ResponseEntity.notFound().build();

        }
        List<StudentGrade> studentGrades = Arrays.stream(grades).map(
                g->{
                    StudentDTO student=
                        restTemplate.getForObject("http://student-service/api/students/" + g.getStudentId(),
                                StudentDTO.class);
                    return new StudentGrade(student.getId(), student.getName(), student.getAge(), g.getGrade());
                }).toList();
        Catalog catalog = new Catalog();
        return ResponseEntity.ok(catalog);
    }

}
