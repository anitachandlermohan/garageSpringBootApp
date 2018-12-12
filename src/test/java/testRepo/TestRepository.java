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
import com.qa.mohan.anita.garageApp.model.garageAppVehicle;
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
		garageAppVehicle vehicle1 = new garageAppVehicle("CAR", "big", "blue");
		entityManager.persist(vehicle1);
		entityManager.flush();
		assertTrue(myRepo.findById(vehicle1.getId()).isPresent());
	}
	
	@Test
	public void retrieveByTypeTest() {
		garageAppVehicle car1 = new garageAppVehicle("CAR","big","pink");
		entityManager.persist(car1);
		entityManager.flush();
		List<garageAppVehicle> vehicles = myRepo.findByType(VehicleType.CAR);
		for(garageAppVehicle vehicle :vehicles) {
		assertEquals("not the right type", vehicle.getType(), "CAR");
		}
			
	}
}
