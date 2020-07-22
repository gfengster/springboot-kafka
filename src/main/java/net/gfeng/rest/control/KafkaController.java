package net.gfeng.rest.control;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.gfeng.kafka.producer.GFProducer;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
	private final GFProducer producer;

	@Autowired
	public KafkaController(GFProducer producer) {
		this.producer = producer;
	}

	@PostMapping(value = "/publish")
	public void sendMessageToKafkaTopic(final RestTemplate restTemplate, 
			@RequestParam("message") String message,
			@RequestHeader Map<String, String> headers) {
		
		this.producer.sendMessage(headers, message);
		
	}
	
	@PutMapping(value = "/publish")
	public void sendMessageToKafkaTopic(final RestTemplate restTemplate, 
			@RequestParam("message") String message) {
		
		this.producer.sendMessage(message);
		
	}
}