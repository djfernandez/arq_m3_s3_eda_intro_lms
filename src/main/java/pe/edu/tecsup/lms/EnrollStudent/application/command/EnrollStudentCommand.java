package pe.edu.tecsup.lms.EnrollStudent.application.command;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EnrollStudentCommand {
  private final String enrollmentId;
  private final String studentId;
  private final String studentName;
  private final String studentEmail;
  private final String courseId;
  private final String courseTitle;
}
