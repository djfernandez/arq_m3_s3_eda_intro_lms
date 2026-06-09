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
import pe.edu.tecsup.lms.courses.domain.event.CourseCreatedEvent;
import pe.edu.tecsup.lms.courses.domain.event.CoursePublishedEvent;
import pe.edu.tecsup.lms.shared.domain.event.DomainEvent;
import pe.edu.tecsup.lms.shared.infrastructure.dlq.DeadLetterQueue;

/**
 * Es el consumidor de eventos
 */
@Slf4j
@RequiredArgsConstructor // Agregar constructor para inyección de dependencias
@Component
public class AnalyticsEventHandler {

    private final Random random = new Random();
    private final DeadLetterQueue dlq; // Inyectar la DLQ

    @Async("eventExecutor")
    @EventListener
    @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.event.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.event.delay:1000}", multiplierExpression = "${retry.event.multiplier:2.0}"))
    public void handleCourseCreated(CourseCreatedEvent event) throws InterruptedException {

        log.info("Starting analytics ........ : {}", event);
        Thread.sleep(4000);
        // log.info("Ending analytics ........ : {}", event.getTitle());
        if (this.random.nextBoolean()) {
            log.error("Processing Starting analytics take longer times ........ : {}", event);
            throw new RuntimeException("Starting analytics failed");
        } else {
            log.info("Ending analytics successfully processed");
        }

    }

    @Async("eventExecutor")
    @EventListener
    @Retryable(retryFor = Exception.class, maxAttemptsExpression = "${retry.event.max-attempts:3}", backoff = @Backoff(delayExpression = "${retry.event.delay:1000}", multiplierExpression = "${retry.event.multiplier:2.0}"))
    public void handleCoursePublished(CoursePublishedEvent event) throws InterruptedException {

        log.info("Starting analytics ........ : {}", event);
        // log.info("Analytics successfully processed");
        if (this.random.nextBoolean()) {
            log.error("Processing Starting analytics take longer times ........ : {}", event);
            throw new RuntimeException("Starting analytics failed");
        } else {
            log.info("Analytics successfully processed");
        }
    }

    @Recover
    public void recover(Exception exception, DomainEvent event) {
        log.error("Analytics event processing failed after retries. event={}", event, exception);
        dlq.add(event, exception);
        // if (event instanceof CourseCreatedEvent) {
        // dlq.add((CourseCreatedEvent) event, exception);
        // } else if (event instanceof CoursePublishedEvent) {
        // dlq.add((CoursePublishedEvent) event, exception);
        // }
    }

}
