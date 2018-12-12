package com.qa.mohan.anita.garageApp.model;

public enum VehicleType {
	CAR{
		@Override
		public String toString() {
			return "CAR";
		}
	},
	VAN{
		@Override
		public String toString() {
			return "VAN";
		}
	},
	MOTORBIKE{
		@Override
		public String toString() {
			return "MOTORBIKE";
		}
	}

}
