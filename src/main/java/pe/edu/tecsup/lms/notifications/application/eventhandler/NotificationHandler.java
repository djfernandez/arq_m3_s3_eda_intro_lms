package pe.edu.tecsup.lms.notifications.application.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.courses.domain.event.CourseCreatedEvent;

import java.util.Random;

@Slf4j
@Component
public class NotificationHandler {

  private final Random random = new Random();

  @Async("eventExecutor")
  @EventListener
  @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 1000, multiplier = 2))
  public void handleCourseCreated(CourseCreatedEvent event) {
    log.info("Sending notification for course creation: {}", event);

    if (random.nextBoolean()) {
      throw new RuntimeException("Notification sending failed");
    }

    log.info("Notification sent successfully for course: {}", event.getTitle());
  }

  @Recover
  public void recover(RuntimeException exception, CourseCreatedEvent event) {
    log.error("Notification failed after retries for course {}: {}", event.getCourseId(), exception.getMessage());
  }
}
