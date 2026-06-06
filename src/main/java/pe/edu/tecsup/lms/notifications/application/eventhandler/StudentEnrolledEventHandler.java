package pe.edu.tecsup.lms.notifications.application.eventhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pe.edu.tecsup.lms.courses.domain.event.StudentEnrolledEvent;

import java.util.Random;

@Slf4j
@Component
public class StudentEnrolledEventHandler {

  private final Random random = new Random();

  @Async("eventExecutor")
  @EventListener
  @Retryable(maxAttempts = 2, backoff = @Backoff(delay = 1000, multiplier = 2))
  public void handleStudentEnrolled(StudentEnrolledEvent event) {
    log.info("Sending welcome email to student {} for course {}", event.getStudentEmail(), event.getCourseId());

    if (random.nextBoolean()) {
      throw new RuntimeException("Welcome email sending failed");
    }

    log.info("Welcome email sent successfully to {}", event.getStudentEmail());
  }

  @Recover
  public void recover(RuntimeException exception, StudentEnrolledEvent event) {
    log.error("Welcome email failed after retries for student {}: {}", event.getStudentId(), exception.getMessage());
  }
}
