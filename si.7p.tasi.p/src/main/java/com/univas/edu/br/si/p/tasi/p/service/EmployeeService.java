package com.univas.edu.br.si.p.tasi.p.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.univas.edu.br.si.p.tasi.p.dto.EmployeeDTO;
import com.univas.edu.br.si.p.tasi.p.entities.EmployeeEntity;
import com.univas.edu.br.si.p.tasi.p.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository repo;
	
	@Autowired
	public EmployeeService(EmployeeRepository del) {
		this.repo = del;
	}
	
	public EmployeeEntity toEntity(EmployeeDTO dto) {
	    return new EmployeeEntity( dto.getId(), dto.getCpf(), dto.getName(), dto.getExtraWorkHours(), dto.getLastTrainingDate(), dto.getPayrollTotalValue(), dto.isActive());
	}

	public void createEmployee(EmployeeDTO employee) {
	    repo.save(toEntity(employee));
	}

	public EmployeeEntity findById(long id) {
	    Optional<EmployeeEntity> obj = repo.findById(id);
	    EmployeeEntity entity = obj.orElse(null);
	    return entity;
	}

	public void activateEmployee(Long id) {
	    Optional<EmployeeEntity> optionalEmployee = repo.findById(id);
	    if (optionalEmployee.isEmpty()) {
	        throw new RuntimeException("Employee with id " + id + " not found.");
	    }
	    EmployeeEntity employee = optionalEmployee.get();
	    employee.setActive(!employee.isActive());
	    repo.save(employee);
	}



}
