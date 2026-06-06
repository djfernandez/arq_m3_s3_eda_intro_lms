package pe.edu.tecsup.lms.Lesson.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@ToString
public class LessonCompletedEvent extends DomainEvent {

  private final String studentId;
  private final String courseId;
  private final String lessonId;
  private final String lessonTitle;
  private final LocalDateTime completedAt;

}
