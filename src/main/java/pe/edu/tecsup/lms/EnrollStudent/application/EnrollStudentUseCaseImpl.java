package pe.edu.tecsup.lms.EnrollStudent.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.tecsup.lms.EnrollStudent.domain.event.StudentEnrolledEvent;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;

@Slf4j
@RequiredArgsConstructor
public class EnrollStudentUseCaseImpl implements EnrollStudentUseCase {

  private final EventPublisher eventPublisher;

  @Override
  public void enrollStudent(
      String enrollmentId,
      String studentId,
      String studentName,
      String studentEmail,
      String courseId,
      String courseTitle) {
    StudentEnrolledEvent event = new StudentEnrolledEvent(
        enrollmentId,
        studentId,
        studentName,
        studentEmail,
        courseId,
        courseTitle);

    log.info("Student enrolled event created: {}", event);
    eventPublisher.publish(event);
  }

}
