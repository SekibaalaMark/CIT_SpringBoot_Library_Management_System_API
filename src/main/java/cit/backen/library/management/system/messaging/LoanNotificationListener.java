package cit.backen.library.management.system.messaging;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@RequiredArgsConstructor
@Component
public class LoanNotificationListener {

    private static final Logger log =
            LoggerFactory.getLogger(LoanNotificationListener.class);
    private final JsonMapper jsonMapper;

    @KafkaListener(topics = "library.loan-created", groupId = "notification-service")
    public void onLoanCreated(String message) {
        LoanCreatedEvent event = jsonMapper.readValue(message, LoanCreatedEvent.class);
        log.info("Loan {} is due {}",event.data().loanId(),event.data().dueDate());
    }
}
