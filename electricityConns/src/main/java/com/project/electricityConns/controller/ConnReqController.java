package com.project.electricityConns.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.electricityConns.dto.ConnReqDTO;
import com.project.electricityConns.service.ConnReqService;

import lombok.Data;

@RestController
@RequestMapping(ControllerConstants.REQUESTS)
@CrossOrigin
public class ConnReqController {
	
	@Autowired
	ConnReqService connReqService;
	
	@GetMapping
	public ResponseEntity<List<ConnReqDTO>> getAllRequests() {
		return new ResponseEntity<>(connReqService.getAllRequests(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ConnReqDTO> getRequestById(@PathVariable Integer id) throws Exception {
		return new ResponseEntity<>(connReqService.getRequestById(id), HttpStatus.OK);
	}
	
	@GetMapping("/{start}/{end}")
	public ResponseEntity<List<ConnReqDTO>> getAllRequestsBetween(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start, 
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) throws Exception {
		return new ResponseEntity<>(connReqService.getAllRequestsBetween(start, end), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Message> addConnectionRequest(@RequestBody ConnReqDTO connReqDTO) throws Exception {
		connReqService.addConnectionRequest(connReqDTO);
		return new ResponseEntity<>(new Message(ControllerConstants.REQUEST_SENT), HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Message> updateConnectionRequest(@RequestBody ConnReqDTO connReqDTO) throws Exception {
		connReqService.updateConnectionRequest(connReqDTO);
		return new ResponseEntity<>(new Message(ControllerConstants.UPDATE_SUCCESS), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Message> deleteConnectionRequest(@PathVariable Integer id) throws Exception {
		connReqService.deleteConnectionRequest(id);
		return new ResponseEntity<>(new Message(ControllerConstants.DELETE_SUCCESS), HttpStatus.OK);
	}
	
	@PostMapping(ControllerConstants.ADD_ALL)
	public ResponseEntity<Message> addRecordsFromCSV() throws IOException {
		connReqService.addRecordsFromCSV();
		return new ResponseEntity<>(new Message(ControllerConstants.ADD_SUCCESS), HttpStatus.OK);
	}
}

@Data
class Message{
	private String message;
	public Message(String message) {
		this.message = message;
	}
}
