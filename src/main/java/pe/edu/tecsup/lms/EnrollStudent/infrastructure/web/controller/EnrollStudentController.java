package pe.edu.tecsup.lms.EnrollStudent.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.edu.tecsup.lms.EnrollStudent.application.EnrollStudentUseCase;
import pe.edu.tecsup.lms.EnrollStudent.infrastructure.web.dto.EnrollStudentRequest;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class EnrollStudentController {

  private final EnrollStudentUseCase enrollStudentUseCase;

  @PostMapping("/{id}/enroll")
  public ResponseEntity<Void> enrollStudent(@PathVariable Long id, @RequestBody EnrollStudentRequest request) {
    enrollStudentUseCase.enrollStudent(
        request.getEnrollmentId(),
        request.getStudentId(),
        request.getStudentName(),
        request.getStudentEmail(),
        String.valueOf(id),
        request.getCourseTitle());
    return ResponseEntity.accepted().build();
  }
}
