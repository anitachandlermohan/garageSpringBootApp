package testIntegration;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.mohan.anita.garageApp.GarageAppApplication;
import com.qa.mohan.anita.garageApp.model.GarageAppVehicle;
import com.qa.mohan.anita.garageApp.repository.VehicleRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GarageAppApplication.class})
@AutoConfigureMockMvc
public class TestIntegration {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private VehicleRepository repository;
	
	@Before
	public void clearDB() {
		repository.deleteAll();
	}
	
	@Test
	public void findAndRetrieveVehicleTest() throws Exception{
		repository.save(new GarageAppVehicle ("CAR", "big", "blue"));
		mvc.perform(get("/api/vehicle")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(content()
		.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$[0].size", is("big")));
	}
	 
	@Test
	public void addVehicleToDatabaseTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		GarageAppVehicle vehicle1 = new GarageAppVehicle("CAR", "medium", "silver");
		String vehicleJsonString = mapper.writeValueAsString(vehicle1); 
		mvc.perform(MockMvcRequestBuilders.post("/api/GarageAppVehicle")
				.contentType(MediaType.APPLICATION_JSON)
				.content(vehicleJsonString))
				.andExpect(status() 
				.isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.size", is("medium")));
	} 
	
	@Test 
	public void deleteVehicleFromDatabaseTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		GarageAppVehicle vehicle2 = new GarageAppVehicle("MOTORBIKE", "huge", "purple");
		repository.save(vehicle2);
		mvc.perform(MockMvcRequestBuilders.delete("/api/vehicle/{id}",vehicle2.getId())
		.contentType(MediaType.APPLICATION_JSON))
 		.andExpect(status().isOk());   
		 
		} 
	
 
}
