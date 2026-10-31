package cit.backen.library.management.system.messaging;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.json.JsonMapper;

@RequiredArgsConstructor
@Component
public class LoanEventProducer {


    private final KafkaTemplate<String, String> kafkaTemplate;
    private final JsonMapper jsonMapper;


    public void loanCreated(LoanCreatedEvent event) {
        String payload = jsonMapper.writeValueAsString(event);//record -> Json Text
        kafkaTemplate.send("library.loan-created", String.valueOf(event.data().memberId()) ,payload);
    }
}