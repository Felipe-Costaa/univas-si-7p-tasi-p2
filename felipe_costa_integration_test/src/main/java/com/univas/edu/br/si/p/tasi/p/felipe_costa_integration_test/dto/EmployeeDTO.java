package com.univas.edu.br.si.p.tasi.p.felipe_costa_integration_test.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

	
	
	private long id;
	private String cpf;
	private String name;
	private float extraWorkHours;
	private String lastTrainingDate;
	private float payrollTotalValue;
	private boolean active;
	
	public EmployeeDTO(Long id2, String string, String string2, double d, String string3, double e, boolean b) {
		// TODO Auto-generated constructor stub
	}
}