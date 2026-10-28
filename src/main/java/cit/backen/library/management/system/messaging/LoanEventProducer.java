package cit.backen.library.management.system.messaging;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class LoanEventProducer {


    private final KafkaTemplate<String, String> kafkaTemplate;

    public LoanEventProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void loanCreated(String memberId, String bookTitle) {
        String message = "Member " + memberId + " borrowed " + bookTitle;
        kafkaTemplate.send("library.loan-created", memberId, message);
    }
}