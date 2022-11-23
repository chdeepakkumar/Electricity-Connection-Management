package com.project.electricityConns.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthCheck {
	private static final Log logger = LogFactory.getLog(HealthCheck.class);
	
	@GetMapping
	public ResponseEntity<String> getHealth() throws Exception {
		logger.info("Calling health check API");
		return new ResponseEntity<String>("Hi, I'm GOOD", HttpStatus.OK);
	}
}
