package pe.edu.tecsup.lms.Lesson.domain.event;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;

@AllArgsConstructor
@Getter
@ToString
@Builder
public class CommentCompletedEvent extends DomainEvent {

  private final String courseCommentId;
  private final String studentId;
  private final String studentComment;
  private final String courseId;
  private final LocalDateTime completedAt;
  private final int newProgressPercentage;
}
