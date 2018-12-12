package com.qa.mohan.anita.garageApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.mohan.anita.garageApp.model.VehicleType;
import com.qa.mohan.anita.garageApp.model.garageAppVehicle;

public interface VehicleRepository extends JpaRepository<garageAppVehicle,Long> {
	List<garageAppVehicle> findByType(VehicleType type);
}
