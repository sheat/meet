package com.example.meet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.meet.dao")
public class MeetApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetApplication.class, args);
	}
}
