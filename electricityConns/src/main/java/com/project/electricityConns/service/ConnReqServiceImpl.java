package com.project.electricityConns.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.project.electricityConns.dto.ConnReqDTO;
import com.project.electricityConns.entity.ConnReq;
import com.project.electricityConns.repo.ConnReqRepository;
import com.project.electricityConns.utils.enums.Category;
import com.project.electricityConns.utils.enums.Gender;
import com.project.electricityConns.utils.enums.GovtId;
import com.project.electricityConns.utils.enums.Ownership;
import com.project.electricityConns.utils.enums.Status;

@Service(value = "connReqService")
@Transactional
public class ConnReqServiceImpl implements ConnReqService {
	
	@Autowired
	private ConnReqRepository connReqRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private Environment env;

	@Override
	public List<ConnReqDTO> getAllRequests() {
		List<ConnReq> allRecords = connReqRepository.findAll();
		List<ConnReqDTO> allRecordsDTO = new ArrayList<>();
		for(ConnReq record: allRecords) {
			allRecordsDTO.add(mapper.map(record, ConnReqDTO.class));
		}
		return allRecordsDTO;
	}

	@Override
	public ConnReqDTO getRequestById(Integer id) throws Exception {
		Optional<ConnReq> connReqOptional = connReqRepository.findById(id);
		connReqOptional.orElseThrow(()-> new Exception(env.getProperty("user.notFound")));
		return mapper.map(connReqOptional.get(), ConnReqDTO.class);
	}

	@Override
	public List<ConnReqDTO> getAllRequestsBetween(LocalDate start, LocalDate end) {
		List<ConnReq> allRecordsBtwn = connReqRepository.findByDateOfApplicationBetween(start, end);
		List<ConnReqDTO> allRecordsBtwnDTO = new ArrayList<>();
		for(ConnReq record: allRecordsBtwn) {
			allRecordsBtwnDTO.add(mapper.map(record, ConnReqDTO.class));
		}
		return allRecordsBtwnDTO;
	}

	@Override
	public void addConnectionRequest(ConnReqDTO connReqDTO) throws Exception {
		String idNumber = connReqDTO.getIdNumber();
		Optional<ConnReq> connReqOptional = connReqRepository.findByIdNumber(idNumber);
		if(connReqOptional.isPresent())
			throw new Exception(env.getProperty("user.alreadyExists"));
		connReqRepository.saveAndFlush(mapper.map(connReqDTO, ConnReq.class));
	}

	@Override
	public void updateConnectionRequest(ConnReqDTO connReqDTO) throws Exception {
		Integer id = connReqDTO.getId();
		Optional<ConnReq> connReqOptional = connReqRepository.findById(id);
		connReqOptional.orElseThrow(()-> new Exception(env.getProperty("user.notFound")));
		connReqRepository.saveAndFlush(mapper.map(connReqDTO, ConnReq.class));
	}

	@Override
	public void deleteConnectionRequest(Integer id) throws Exception {
		Optional<ConnReq> connReqOptional = connReqRepository.findById(id);
		connReqOptional.orElseThrow(()-> new Exception(env.getProperty("user.notFound")));
		connReqRepository.deleteById(id);
	}
	
	@Override
	public void addRecordsFromCSV() throws IOException {
		File file = new File("src/main/resources/electricity_board_case_study.csv");
		FileReader fr=new FileReader(file);   //reads the file  
		BufferedReader br=new BufferedReader(fr);  //creates a buffering character input stream  
		String line;
		Random random = new Random();
		int idNumber = random.nextInt(1000000, 9999999);
		br.readLine();
		while((line=br.readLine())!=null) {
			String[] vals = line.split(",");
			ConnReq connReq = new ConnReq();
			connReq.setId(Integer.parseInt(vals[0]));
			connReq.setName(vals[1]);
			connReq.setGender(Gender.getGender(vals[2]));
			connReq.setDistrict(vals[3]);
			connReq.setState(vals[4]);
			connReq.setPinCode(vals[5]);
			connReq.setOwnership(Ownership.getOwnership(vals[6]));
			connReq.setGovtId(GovtId.getGovtId(vals[7])); 
			connReq.setIdNumber(String.valueOf(idNumber));
			idNumber += random.nextInt(10, 100);
			connReq.setCategory(Category.getCategory(vals[9]));
			connReq.setLoadAppl(Integer.parseInt(vals[10]));
			connReq.setDateOfApplication(LocalDate.parse(vals[11]));
			if(!vals[12].isBlank())
				connReq.setDateOfApproval(LocalDate.parse(vals[12]));
			connReq.setModifiedDate(LocalDate.parse(vals[13]));
			connReq.setStatus(Status.getStatus(vals[14]));
			connReq.setReviewerId(Integer.parseInt(vals[15]));
			connReq.setReviewerName(vals[16]);
			connReq.setComments(vals[17]);
			connReqRepository.save(connReq);
		}
		br.close();
	}

}
