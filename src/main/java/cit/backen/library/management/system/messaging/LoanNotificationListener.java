package cit.backen.library.management.system.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
public class LoanNotificationListener {

    private static final Logger log =
            LoggerFactory.getLogger(LoanNotificationListener.class);

    @KafkaListener(topics = "library.loan-created", groupId = "notification-service")
    public void onLoanCreated(String message) {
        log.info("Sending SMS: {}", message);   // the real SMS call comes later
    }
}
