package pe.edu.tecsup.lms.shared.domain.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class EventPublisher {

    private final ApplicationEventPublisher publisher;

    /**
     * Método que publica el evento
     * 
     * @param event
     */
    public void publish(DomainEvent event) {
        log.info("Publishing event: {}", event);
        if (event != null) {
            this.publisher.publishEvent(event);
        }
    }

}
