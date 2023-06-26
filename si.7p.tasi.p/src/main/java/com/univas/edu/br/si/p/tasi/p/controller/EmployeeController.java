package com.univas.edu.br.si.p.tasi.p.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.univas.edu.br.si.p.tasi.p.dto.EmployeeDTO;
import com.univas.edu.br.si.p.tasi.p.entities.EmployeeEntity;
import com.univas.edu.br.si.p.tasi.p.service.EmployeeService;



@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.CREATED)
	public void createEmployee(@RequestBody EmployeeDTO employee) {
		service.createEmployee(employee);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
	    EmployeeEntity employee = service.findById(id);
	    if (employee == null) {
	        return ResponseEntity.notFound().build();
	    } else {
	    	EmployeeDTO ent = new EmployeeDTO(employee);
	        return ResponseEntity.ok().body(ent);
	    }
	}

	
	@PutMapping("/active/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void activeEmployee(@PathVariable Long id) {
		service.activateEmployee(id);
	}

}