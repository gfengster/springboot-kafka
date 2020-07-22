package net.gfeng.kafka.producer;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class GFProducer {
	private static final Logger logger = LoggerFactory.getLogger(GFProducer.class);
	
	@Value(value = "${kafka.topic}")
	private String topic;
	
	private static int count = 1;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String message) {
		logger.info(String.format("$$ -> Producing message --> %s", message));
		//this.kafkaTemplate.send(TOPIC, "key " + (count++), message);
		
		ProducerRecord<String, String> record = new ProducerRecord<>(topic, "key " + (count++), message);
		this.kafkaTemplate.send(record);
	}
	
	public void sendMessage(Map<String, String> headers, String message) {
		logger.info(String.format("$$ -> Producing message --> %s", message));
	
		Gson gson = new Gson();
		
		ProducerRecord<String, String> record = new ProducerRecord<>(topic, gson.toJson(headers), message);
		this.kafkaTemplate.send(record);
	}
}
