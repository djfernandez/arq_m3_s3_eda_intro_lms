package pe.edu.tecsup.lms.courses.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.edu.tecsup.lms.courses.application.CreateCourseUseCase;
import pe.edu.tecsup.lms.courses.application.PublishCourseUseCase;
import pe.edu.tecsup.lms.courses.domain.model.Course;
import pe.edu.tecsup.lms.courses.infrastructure.web.dto.CreateCourseRequest;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CreateCourseUseCase createCourseUseCase;
    private final PublishCourseUseCase publishCourseUseCase;

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody CreateCourseRequest request) {
        Course course = createCourseUseCase.createCourse(
                request.getTitle(),
                request.getDescription(),
                request.getInstructor());
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<Course> publishCourse(@PathVariable Long id) {
        Course course = publishCourseUseCase.publishCourse(id);
        return ResponseEntity.ok(course);
    }
}
