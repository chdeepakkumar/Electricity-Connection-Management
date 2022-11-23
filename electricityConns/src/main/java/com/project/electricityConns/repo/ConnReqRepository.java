package com.project.electricityConns.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.electricityConns.entity.ConnReq;

public interface ConnReqRepository extends JpaRepository<ConnReq, Integer> {
	@Query(value = "SELECT * FROM ConnReq c WHERE c.date_of_application BETWEEN ?1 AND ?2", nativeQuery = true)
	public List<ConnReq> findByDateOfApplicationBetween(LocalDate start, LocalDate end);
	public Optional<ConnReq> findByIdNumber(String idNumber);
}
