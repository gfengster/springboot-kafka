package net.gfeng.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class GFConsumer {

	@KafkaListener(topics = "test", groupId = "group-id")
	public void listen(ConsumerRecord<String, String> record) {
		System.out.println("Received Key in group - group-id: " + record.key());
		System.out.println("Received Messasge in group - group-id: " + record.value());
	}
}
