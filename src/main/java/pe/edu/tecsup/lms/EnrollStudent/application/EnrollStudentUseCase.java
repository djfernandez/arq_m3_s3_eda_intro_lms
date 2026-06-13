package pe.edu.tecsup.lms.EnrollStudent.application;

public interface EnrollStudentUseCase {

  void enrollStudent(

      String enrollmentId,
      String studentId,
      String studentName,
      String studentEmail,
      String courseId,
      String courseTitle);

}
