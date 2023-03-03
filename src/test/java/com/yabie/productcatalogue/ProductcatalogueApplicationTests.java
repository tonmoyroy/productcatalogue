package com.yabie.productcatalogue;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@AutoConfigureTestDatabase
class ProductcatalogueApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext applicationContext;

	@Autowired
	ObjectMapper objectMapper;

	@BeforeEach
	public void setup() {
		this.mockMvc = webAppContextSetup(this.applicationContext)
				.apply(springSecurity())
				.build();
	}


	@Test
	public void getAllVehicles() throws Exception {
		this.mockMvc
				.perform(
						get("/v1/products")
								.accept(MediaType.APPLICATION_JSON)
				)
				.andExpect(status().isOk());
	}
}
