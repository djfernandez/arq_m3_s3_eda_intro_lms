package pe.edu.tecsup.lms.Lesson.infrastructure.web.dto;

import lombok.Data;

@Data
public class CompleteLessonRequest {

  private String studentId;
  private String lessonId;
  private String lessonTitle;

}
