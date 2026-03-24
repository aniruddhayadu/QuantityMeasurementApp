package com.app.quantitymeasurement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class QuantityMeasurementControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "ani@test.com")
	public void testMeasurementComparisonSuccess() throws Exception {
		String jsonRequest = """
				{
				    "thisQuantityDTO": { "value": 1.0, "unit": "FEET", "measurementType": "LengthUnit" },
				    "thatQuantityDTO": { "value": 12.0, "unit": "INCHES", "measurementType": "LengthUnit" }
				}
				""";

		mockMvc.perform(post("/api/v1/quantities/compare").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)).andExpect(status().isOk()).andExpect(jsonPath("$.resultString").value("Equal"));
	}
}