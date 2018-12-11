package com.qa.mohan.anita.garageApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qa.mohan.anita.garageApp.model.garageAppVehicle;

public interface VehicleRepository extends JpaRepository<garageAppVehicle,Long> {

}
