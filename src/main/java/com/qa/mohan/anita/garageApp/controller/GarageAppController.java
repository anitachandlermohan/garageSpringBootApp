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
import com.qa.mohan.anita.garageApp.model.garageAppVehicle;
import com.qa.mohan.anita.garageApp.repository.VehicleRepository;

@RestController
@RequestMapping("/api")
public class GarageAppController {
	@Autowired
	
	VehicleRepository myRepository;
	
	//create vehicle
	@PostMapping("/GarageAppVehicle")
	public garageAppVehicle createVehicle(@Valid @RequestBody garageAppVehicle gAV) {
		return myRepository.save(gAV);
	}
	
	//get single vehicle
	@GetMapping("vehicle/{id}")
	public garageAppVehicle getVehiclebyID(@PathVariable(value = "id")Long vehicleID) {
		return myRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("garageAppVehicle","id",vehicleID));
		
	}
	//get all vehicles
	@GetMapping("/vehicle")
	public List<garageAppVehicle> getAllVehicles(){
		return myRepository.findAll();
	}
	
	//update a vehicle
//	@PutMapping("/vehicle/{id}")
//	public garageAppVehicle updateVehicle(@PathVariable(value = "id")Long vehicleID,
//			@Valid @RequestBody garageAppVehicle vehicleDetails) {
//		garageAppVehicle gAV = myRepository.findById(vehicleID).orElseThrow(() -> new ResourceNotFoundException("vehicle","id",vehicleID));
//		gAV.setType(VehicleType.valueOf(vehicleDetails.getType().toUpperCase()));
//		gAV.setSize(VehicleSize.valueOf(vehicleDetails.getSize().toUpperCase()));
//		gAV.setColour(vehicleDetails.getColour());
//		
//		garageAppVehicle updateData = myRepository.save(gAV);
//		return updateData;
		
//	}
	
	@PutMapping("/vehicle/{id}")
	public garageAppVehicle updateVehicle(@PathVariable(value = "id")Long vehicleID,
	@Valid @RequestBody garageAppVehicle vehicleDetails){
		garageAppVehicle gAV = myRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("vehicle","id",vehicleID));
		gAV.setType(vehicleDetails.getType());
		gAV.setSize(vehicleDetails.getSize());
		gAV.setColour(vehicleDetails.getColour());
		
		garageAppVehicle updateData = myRepository.save(gAV);
		return updateData;
	}
	@DeleteMapping("/vehicle/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id")Long vehicleID){
		garageAppVehicle gAV = myRepository.findById(vehicleID).orElseThrow(()-> new ResourceNotFoundException("vehicle","id",vehicleID));
		
		myRepository.delete(gAV);
		return ResponseEntity.ok().build();
	}
}
