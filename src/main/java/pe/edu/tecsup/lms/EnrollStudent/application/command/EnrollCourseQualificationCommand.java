package pe.edu.tecsup.lms.EnrollStudent.application.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnrollCourseQualificationCommand {
  private final String studentId;
  private final String courseQualification;
  private final String courseId;
}
