package integration;

import org.aspectj.lang.annotation.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.qa.mohan.anita.garageApp.model.garageAppVehicle;
import com.qa.mohan.anita.garageApp.repository.VehicleRepository;

//public class TestIntegration {
//	@RunWith(SpringRunner.class)
//	@SpringBootTest(classes= {garageAppVehicle.class})
//	@AutoConfigureMockMvc
//	public class IntegrationTest{
//		@Autowired
//		private MockMvc mvc;
//		private VehicleRepository repository;
//		
//		@Before
//		public void clearDB() {
//			repository.deleteAll();
//		}
//		
//	}
//
//}
