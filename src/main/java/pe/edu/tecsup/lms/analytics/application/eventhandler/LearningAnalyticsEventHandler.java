package pe.edu.tecsup.lms.analytics.application.eventhandler;

import java.util.Random;

import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.edu.tecsup.lms.EnrollStudent.domain.event.StudentEnrolledEvent;
import pe.edu.tecsup.lms.Lesson.domain.event.LessonCompletedEvent;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;
import pe.edu.tecsup.lms.shared.infrastructure.dlq.DeadLetterQueue;

@Slf4j
@RequiredArgsConstructor // Agregar constructor para inyección de dependencias
@Component
public class LearningAnalyticsEventHandler {

  private final Random random = new Random();
  private final DeadLetterQueue dlq; // Inyectar la DLQ

  @Async("eventExecutor")
  @EventListener
  @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.event.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.event.delay:1000}", multiplierExpression = "${retry.event.multiplier:2.0}"))
  public void handleStudentEnrolled(StudentEnrolledEvent event) throws InterruptedException {

    log.info(
        "Updating course statistics for enrollment. courseId={}, studentId={}",
        event.getCourseId(),
        event.getStudentId());
    if (this.random.nextBoolean()) {
      log.error("Processing updating course statistics for enrollment take longer times ........ : {}", event);
      throw new RuntimeException("Updating course statistics for enrollment failed");
    } else {
      log.info("Course statistics for enrollment successfully processed");
    }
    // log.info("Course statistics for enrollment successfully processed");
  }

  @Async("eventExecutor")
  @EventListener
  @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.event.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.event.delay:1000}", multiplierExpression = "${retry.event.multiplier:2.0}"))
  public void handleLessonCompleted(LessonCompletedEvent event) throws InterruptedException {

    log.info(
        "Updating progress for student {} in lesson {} of course {}",
        event.getStudentId(),
        event.getLessonId(),
        event.getCourseId());
    if (this.random.nextBoolean()) {
      log.error("Processing updating progress for lesson completion take longer times ........ : {}", event);
      throw new RuntimeException("Updating progress for lesson completion failed");
    } else {
      log.info("Progress update for lesson completion successfully processed");
    }
    // log.info("Progress update for lesson completion successfully processed");

  }

  @Async("eventExecutor")
  @EventListener
  @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.event.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.event.delay:1000}", multiplierExpression = "${retry.event.multiplier:2.0}"))
  public void handleCourseCompletionValidation(LessonCompletedEvent event) throws InterruptedException {

    log.info(
        "Checking if student {} completed course {} after lesson {}",
        event.getStudentId(),
        event.getCourseId(),
        event.getLessonId());
    if (this.random.nextBoolean()) {
      log.error("Processing Checking if student take longer times ........ : {}", event);
      throw new RuntimeException("Checking if student failed");
    } else {
      log.info("Course completion validation successfully processed");
    }
    // log.info("Course completion validation successfully processed");
  }

  @Recover
  public void recover(Exception exception, DomainEvent event) {
    log.error("Learning analytics event processing failed after retries. event={}", event, exception);
    dlq.add(event, exception);
  }

}
