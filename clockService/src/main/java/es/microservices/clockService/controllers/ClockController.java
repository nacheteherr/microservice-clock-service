package es.microservices.clockService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.microservices.clockService.services.ClockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api
@RestController
@RequestMapping("/clock")
public class ClockController {

	@Autowired
	private ClockService clockService;
	
	@RequestMapping(method=RequestMethod.GET, path="/time")
	@ApiOperation(value="getTime", response=String.class)
	@ApiResponses(value={
			@ApiResponse(code=200, message="Sucess", response=String.class),
			@ApiResponse(code = 500, message = "Failure")
	})
	public String getTime() {
		return clockService.getTime();
	}
	
}
