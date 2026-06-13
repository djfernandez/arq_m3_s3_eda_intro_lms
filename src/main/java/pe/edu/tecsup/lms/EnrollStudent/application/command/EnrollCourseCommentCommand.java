package pe.edu.tecsup.lms.EnrollStudent.application.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnrollCourseCommentCommand {
  private final String studentId;
  private final String studentComment;
  private final String courseId;
}