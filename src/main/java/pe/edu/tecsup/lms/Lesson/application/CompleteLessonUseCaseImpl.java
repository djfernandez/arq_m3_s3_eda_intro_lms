package pe.edu.tecsup.lms.Lesson.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.tecsup.lms.Lesson.domain.event.LessonCompletedEvent;
import pe.edu.tecsup.lms.shared.domain.event.EventPublisher;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
public class CompleteLessonUseCaseImpl implements CompleteLessonUseCase {

  private final EventPublisher eventPublisher;

  @Override
  public void completeLesson(
      String studentId,
      String courseId,
      String lessonId,
      String lessonTitle,
      String enrollmentId) {
    LessonCompletedEvent event = new LessonCompletedEvent(
        enrollmentId,
        studentId,
        courseId,
        lessonId,
        lessonTitle,
        LocalDateTime.now(),
        0);

    log.info("Lesson completed event created: {}", event);
    eventPublisher.publish(event);
  }

}
