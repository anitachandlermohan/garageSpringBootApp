package com.qa.mohan.anita.garageApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.mohan.anita.garageApp.exception.ResourceNotFoundException;
import com.qa.mohan.anita.garageApp.model.VehicleType;
import com.qa.mohan.anita.garageApp.model.GarageAppVehicle;
import com.qa.mohan.anita.garageApp.repository.VehicleRepository;

@RestController
@RequestMapping("/api")
public class GarageAppController {
	@Autowired
	 
	VehicleRepository myRepository;
	
	//create vehicle
	@PostMapping("/GarageAppVehicle")
	public GarageAppVehicle createVehicle(@Valid @RequestBody GarageAppVehicle gAV) {
		return myRepository.save(gAV);
	}
	
	//get single vehicle
	@GetMapping("vehicle/{id}")
	public GarageAppVehicle getVehiclebyID(@PathVariable(value = "id")Long vehicleID) {
		return myRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("garageAppVehicle","id",vehicleID));
		
	}
	//get all vehicles
	@GetMapping("/vehicle")
	public List<GarageAppVehicle> getAllVehicles(){
		return myRepository.findAll();
	}
	// find vehicle by type
	@GetMapping("vehicle/type/{type}")
	public List<GarageAppVehicle> findVehicleByType(@PathVariable(value = "type")VehicleType type){
		return myRepository.findByType(type);
	}
	

	@PutMapping("/vehicle/{id}")
	public GarageAppVehicle updateVehicle(@PathVariable(value = "id")Long vehicleID,
	@Valid @RequestBody GarageAppVehicle vehicleDetails){
		GarageAppVehicle gAV = myRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("vehicle","id",vehicleID));
		gAV.setType(vehicleDetails.getType());
		gAV.setSize(vehicleDetails.getSize());
		gAV.setColour(vehicleDetails.getColour());
		
		GarageAppVehicle updateData = myRepository.save(gAV);
		return updateData;
	}
	
	
	@DeleteMapping("/vehicle/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id")Long vehicleID){
		GarageAppVehicle gAV = myRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("vehicle","id",vehicleID));
		
		myRepository.delete(gAV);
		return ResponseEntity.ok().build();
	}
//	@DeleteMapping("/vehicle/type/{type}")
//	public
}
