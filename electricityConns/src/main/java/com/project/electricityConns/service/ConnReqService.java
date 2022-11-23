package com.project.electricityConns.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import com.project.electricityConns.dto.ConnReqDTO;

public interface ConnReqService {
	public List<ConnReqDTO> getAllRequests();
	public ConnReqDTO getRequestById(Integer id) throws Exception;
	public List<ConnReqDTO> getAllRequestsBetween(LocalDate start, LocalDate end);
	public void addConnectionRequest(ConnReqDTO connReqDTO) throws Exception;
	public void updateConnectionRequest(ConnReqDTO connReqDTO) throws Exception;
	public void deleteConnectionRequest(Integer id) throws Exception;
	public void addRecordsFromCSV() throws IOException;
}