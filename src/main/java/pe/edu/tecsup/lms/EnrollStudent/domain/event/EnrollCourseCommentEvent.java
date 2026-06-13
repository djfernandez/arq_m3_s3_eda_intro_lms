package pe.edu.tecsup.lms.EnrollStudent.domain.event;

import lombok.Builder;
import lombok.Getter;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;

@Getter
@Builder
public class EnrollCourseCommentEvent extends DomainEvent {
  private final String courseCommentId;
  private final String studentId;
  private final String studentComment;
  private final String courseId;
}
