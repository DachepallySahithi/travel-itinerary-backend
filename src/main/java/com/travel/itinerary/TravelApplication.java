package com.travel.itinerary;

import com.travel.itinerary.service.TextProcessingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

}
//public class TravelApplication {
//	public static void main(String[] args) throws IOException{
//		TextProcessingService textProcessingService = new TextProcessingService();
//		String userQuery = "United states of America";
//		String databaseDate = "United State of America";
//		String ans = textProcessingService.processText(userQuery);
//		String ans1 = textProcessingService.processText(databaseDate);
//		System.out.println(ans);
//		System.out.println(databaseDate);
//	}
//}