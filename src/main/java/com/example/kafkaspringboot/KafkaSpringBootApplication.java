package com.example.kafkaspringboot;

import com.example.kafkaspringboot.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class KafkaSpringBootApplication implements CommandLineRunner {

    @Autowired
    private Producer producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaSpringBootApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String message = scanner.nextLine();
            if ("wq".equals(message)) {
                break;
            }
            producer.send("purchases", message);
        }
        scanner.close();
    }
}
