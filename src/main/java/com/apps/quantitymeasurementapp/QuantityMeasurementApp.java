package com.apps.quantitymeasurementapp;

public class QuantityMeasurementApp {

	private final double value;
	
	public QuantityMeasurementApp(double value) {
		if(Double.isNaN(value)) {
			throw new IllegalArgumentException("Invalid input!");
		}
		this.value = value;
	}
	public Double getValue() {
		return value;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		
		if(obj==null || obj.getClass()!=this.getClass()) {
			return false;
		}
		return Double.compare(this.value, ((QuantityMeasurementApp)obj).getValue())==0;
	}
}