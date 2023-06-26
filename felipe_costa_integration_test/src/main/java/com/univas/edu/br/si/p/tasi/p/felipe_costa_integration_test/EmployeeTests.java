package com.univas.edu.br.si.p.tasi.p.felipe_costa_integration_test;

import static org.junit.Assert.assertEquals;

import org.apache.http.HttpStatus;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.univas.edu.br.si.p.tasi.p.felipe_costa_integration_test.dto.EmployeeDTO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EmployeeTests {
	
	private ObjectMapper mapper = new ObjectMapper();
	private final String employeeURL = "http://localhost:8080/employee";
	
	private Response createEmployeeWithId(Long id) {
		EmployeeDTO employee = new EmployeeDTO(id, "13790324663", "Felipe Costa", 1.55, "25/06/2023", 1200.00, true);

		Response resp = RestAssured.given().body(employee).contentType(ContentType.JSON).post(employeeURL);
		return resp;
	}

	// Post success
	@Test
	public void testCreateEmployee_withSuccess() {
		Long id = 1001L;
		Response resp = createEmployeeWithId(id);
		resp.then().assertThat().statusCode(HttpStatus.SC_CREATED);
	}
	
	// Post fail with existing id
		@Test
		public void testCreateEmployee_withExistId() {
			Long id = 1L;
			Response resp = RestAssured.get(employeeURL + "/" + id);

			if (resp.getStatusCode() == HttpStatus.SC_OK) {
				RestAssured.given().contentType(ContentType.JSON).post(employeeURL).then().assertThat()
						.statusCode(HttpStatus.SC_BAD_REQUEST);
			} else {
				Response write = createEmployeeWithId(id);
				write.then().assertThat().statusCode(HttpStatus.SC_CREATED);
			}
		}
		
		// Post fail badrequest
		@Test
		public void testCreateEmployee_withWrongJson() {
			RestAssured.given().contentType(ContentType.JSON).post(employeeURL).then().assertThat()
					.statusCode(HttpStatus.SC_BAD_REQUEST);
		}
		
		// Get success
		@Test
		public void testGetEmployeeById_withSuccessfull() {
			Long id = 1L;
			createEmployeeWithId(id);
			Response resp = RestAssured.get(employeeURL + "/" + id);
			resp.then().assertThat().statusCode(HttpStatus.SC_OK);
		}
		
		// Get fail object not found
		@Test
		public void testGetEmployeeByid_withFail() {
			Long nonExistingDeliveryNumber = 600L;
			Response resp = RestAssured.get(employeeURL + nonExistingDeliveryNumber);
			resp.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
		}

		// Put success
		@Test
		public void testPutEmployeeById_withSuccess() {
			Long id = 1L;

			Response action = RestAssured.put(employeeURL + "active/" + id);
			action.then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
			assertEquals(HttpStatus.SC_NO_CONTENT, action.getStatusCode());

			Response resp = RestAssured.get(employeeURL + id);
			resp.then().assertThat().statusCode(HttpStatus.SC_OK);
		}
		
		// Put fail not found
		@Test
		public void testPutEmployee_withNoExistCode() {
			Long id = 700L;

			Response resp = RestAssured.get(employeeURL + id);
			if (resp.getStatusCode() == HttpStatus.SC_OK) {
				Response action = RestAssured.put(employeeURL + "active/" + id);
				action.then().assertThat().statusCode(HttpStatus.SC_NO_CONTENT);
				assertEquals(HttpStatus.SC_NO_CONTENT, action.getStatusCode());
			} else {
				resp.then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND);
			}
		}

	
}
