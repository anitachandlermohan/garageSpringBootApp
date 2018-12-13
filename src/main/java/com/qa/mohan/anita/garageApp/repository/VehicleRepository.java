package com.qa.mohan.anita.garageApp.repository;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.qa.mohan.anita.garageApp.model.GarageAppVehicle;
import com.qa.mohan.anita.garageApp.model.VehicleType;

@Repository
public interface VehicleRepository extends JpaRepository<GarageAppVehicle,Long> {
	List<GarageAppVehicle> findByType(VehicleType type);
}
