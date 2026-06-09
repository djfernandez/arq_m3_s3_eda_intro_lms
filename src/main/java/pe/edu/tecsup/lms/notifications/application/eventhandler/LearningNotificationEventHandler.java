package pe.edu.tecsup.lms.notifications.application.eventhandler;

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
public class LearningNotificationEventHandler {

  private final Random random = new Random();
  private final DeadLetterQueue dlq; // Inyectar la DLQ

  @Async("eventExecutor")
  @EventListener
  @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.event.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.event.delay:1000}", multiplierExpression = "${retry.event.multiplier:2.0}"))
  public void handleWelcomeEmail(StudentEnrolledEvent event) {

    log.info(
        "Sending welcome email to {} ({}) for course {}",
        event.getStudentName(),
        event.getStudentEmail(),
        event.getCourseTitle());
    if (this.random.nextBoolean()) {
      log.error("Processing sending welcome email take longer times ........ : {}", event);
      throw new RuntimeException("Sending welcome email failed");
    } else {
      log.info("Sending welcome email successfully processed");
    }
    // log.info("Sending email successfully processed");
  }

  @Async("eventExecutor")
  @EventListener
  @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.event.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.event.delay:1000}", multiplierExpression = "${retry.event.multiplier:2.0}"))
  public void handleAchievementNotification(LessonCompletedEvent event) {

    log.info(
        "Sending achievement notification to student {} for lesson {}",
        event.getStudentId(),
        event.getLessonTitle());
    if (this.random.nextBoolean()) {
      log.error("Processing sending achievement notification take longer times ........ : {}", event);
      throw new RuntimeException("Sending achievement notification failed");
    } else {
      log.info("Sending achievement notification successfully processed");
    }
    // log.info("Sending achievement notification successfully processed");
  }

  @Recover
  public void recover(Exception exception, DomainEvent event) {
    log.error("Learning notification event processing failed after retries. event={}", event, exception);
    dlq.add(event, exception);
  }

}
