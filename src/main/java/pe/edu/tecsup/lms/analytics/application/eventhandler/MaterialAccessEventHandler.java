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
import pe.edu.tecsup.lms.shared.infrastructure.dlq.DeadLetterQueue;

@Slf4j
@RequiredArgsConstructor // Agregar constructor para inyección de dependencias
@Component
public class MaterialAccessEventHandler {

  private final Random random = new Random();
  private final DeadLetterQueue dlq; // Inyectar la DLQ

  @Async("eventExecutor")
  @EventListener
  @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.event.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.event.delay:1000}", multiplierExpression = "${retry.event.multiplier:2.0}"))
  public void handleStudentEnrolled(StudentEnrolledEvent event) throws InterruptedException {

    log.info(
        "Creating material access for student {} in course {}",
        event.getStudentId(),
        event.getCourseId());
    if (this.random.nextBoolean()) {
      log.error("Processing creating material access take longer times ........ : {}", event);
      throw new RuntimeException("Creating material access failed");
    } else {
      log.info("Creating material access successfully processed");
    }
    // log.info("Creating material access successfully processed");
  }

  @Recover
  public void recover(Exception exception, StudentEnrolledEvent event) {
    log.error("Material access event processing failed after retries. event={}", event, exception);
    dlq.add(event, exception);
  }

}
