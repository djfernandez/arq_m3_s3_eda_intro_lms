package pe.edu.tecsup.lms.EnrollStudent.infrastructure.web.dto;

import lombok.Data;

@Data
public class EnrollStudentRequest {

  private String enrollmentId;
  private String studentId;
  private String studentName;
  private String studentEmail;
  private String courseTitle;

}
