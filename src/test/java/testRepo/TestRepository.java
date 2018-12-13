package testRepo;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.mohan.anita.garageApp.GarageAppApplication;
import com.qa.mohan.anita.garageApp.model.VehicleType;
import com.qa.mohan.anita.garageApp.model.GarageAppVehicle;
import com.qa.mohan.anita.garageApp.repository.VehicleRepository;

@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {GarageAppApplication.class})

@ContextConfiguration(classes = {GarageAppApplication.class})
@DataJpaTest
public class TestRepository { 

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired 
	private VehicleRepository myRepo;
	 
	@Test 
	public void retrieveByIdTest() {
		GarageAppVehicle vehicle1 = new GarageAppVehicle("CAR", "big", "blue");
		entityManager.persist(vehicle1);
		entityManager.flush();
		assertTrue(myRepo.findById(vehicle1.getId()).isPresent());
	}
	
	@Test
	public void retrieveByTypeTest() {
		GarageAppVehicle car1 = new GarageAppVehicle("CAR","big","pink");
		entityManager.persist(car1);
		entityManager.flush();
		List<GarageAppVehicle> vehicles = myRepo.findByType(VehicleType.CAR);
		for(GarageAppVehicle vehicle :vehicles) { 
		assertEquals("not the right type", vehicle.getType(), "CAR");
		}
			
	}
} 
	