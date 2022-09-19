package com.example.kafka;

import com.example.kafka.jsonListener.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageKafka
{
    Car car;
    String message;
    String key;
    Date timeStamp;
    int partition;
    String topic;
    boolean received=false;
}
