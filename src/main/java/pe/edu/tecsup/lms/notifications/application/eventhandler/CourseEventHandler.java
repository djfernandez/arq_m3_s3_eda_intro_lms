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
import pe.edu.tecsup.lms.courses.domain.event.CourseCreatedEvent;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;
import pe.edu.tecsup.lms.shared.infrastructure.dlq.DeadLetterQueue;

/**
 * Es el consumidor de eventos
 */
@Slf4j
@RequiredArgsConstructor // Agregar constructor para inyección de dependencias
@Component
public class CourseEventHandler {

    private final Random random = new Random();
    private final DeadLetterQueue dlq; // Inyectar la DLQ

    @Async("eventExecutor")
    @EventListener
    @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.event.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.event.delay:1000}", multiplierExpression = "${retry.event.multiplier:2.0}"))
    public void handleCourseCreated(CourseCreatedEvent event) {
        log.info("Course created event received: {}", event);
        // log.info("Course successfully processed");
        if (this.random.nextBoolean()) {
            log.error("Processing Course created take longer times ........ : {}", event);
            throw new RuntimeException("Course created processing failed");
        } else {
            log.info("Course created successfully processed");
        }
    }

    @Recover
    public void recover(Exception exception, DomainEvent event) {
        log.error("Course notification event processing failed after retries. event={}", event, exception);
        // Add event failed to DLQ
        dlq.add(event, exception);
    }

}
