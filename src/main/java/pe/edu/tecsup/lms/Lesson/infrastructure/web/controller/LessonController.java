package pe.edu.tecsup.lms.Lesson.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pe.edu.tecsup.lms.Lesson.application.CompleteLessonUseCase;
import pe.edu.tecsup.lms.Lesson.infrastructure.web.dto.CompleteLessonRequest;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class LessonController {

  private final CompleteLessonUseCase completeLessonUseCase;

  @PostMapping("/{id}/lessons/complete")
  public ResponseEntity<Void> completeLesson(@PathVariable Long id, @RequestBody CompleteLessonRequest request) {
    completeLessonUseCase.completeLesson(
        request.getStudentId(),
        String.valueOf(id),
        request.getLessonId(),
        request.getLessonTitle());
    return ResponseEntity.accepted().build();
  }
}
