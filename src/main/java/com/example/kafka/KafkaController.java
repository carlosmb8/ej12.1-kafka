package com.example.kafka;


import com.example.kafka.jsonListener.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaController {
	@Autowired
	KafkaMessageProducer kafkaMessageProducer;
	@Autowired
	MessageKafka messageKafka;
	@Autowired
	private KafkaTemplate<String, Car> kafkaTemplate;

	@PostMapping("/add/{topic}")
	public MessageKafka addTopic( @PathVariable String topic,@RequestBody  String body) throws InterruptedException {
		kafkaMessageProducer.sendMessage(topic,null,body);
		waitMessage();
		return messageKafka;
	}

	@PostMapping("/add/{topic}/{key}")
	public MessageKafka addTopicAndKey( @PathVariable String topic,@PathVariable String key,
							   @RequestBody  String body) throws InterruptedException
	{
		kafkaMessageProducer.sendMessage(topic,key,body);
		waitMessage();
		return messageKafka;
	}

	private void waitMessage() throws InterruptedException
	{
		long initialDate=System.currentTimeMillis();
		long finalDate=initialDate+1000*10; // Add 10 seconds
		while (!messageKafka.isReceived() && finalDate>System.currentTimeMillis())
		{
			Thread.currentThread().sleep(500);
		}
	}
}
