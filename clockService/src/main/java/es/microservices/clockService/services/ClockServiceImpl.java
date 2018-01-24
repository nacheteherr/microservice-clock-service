package es.microservices.clockService.services;

import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class ClockServiceImpl implements ClockService {

	private static final Logger logger = LoggerFactory.getLogger(ClockServiceImpl.class);		
	
	@Value("${server.port}")
	private String appPort;
	
	@Value("${clock.getTime.errorEveryNCalls}")
	private Integer errorEveryNCalls;
	
	private Long numCalls = 0l;
	
	@HystrixCommand(fallbackMethod = "fallbackGetTime")
	@Override
	public String getTime() {
		logger.info("Received request on port " + appPort + ". Call number: " + (++numCalls));
		
		if (numCalls % errorEveryNCalls == 0) {
			// Throw Exception
			throw new RuntimeException("Error getting time!!!");
		}
		
		return LocalTime.now().toString();
	}
	
	
	public String fallbackGetTime() {
		return "FALLBACK: could not get the time!!!!";
	}
	
}
