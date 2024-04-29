package com.designpattern.behavioral;

public class DecoratorDesignPattern {
	public static void main(String[] args) {
		BaseCar myCarWithFogLightAndMusicPlayer =  new MusicPlayer(new FogLight(new FamilyCar()));
		double price = myCarWithFogLightAndMusicPlayer.totalPrice();
		System.out.println("Family Car with Fog Light and Music Player: " + price);
		
		BaseCar mySportsCarWithFogLightAndTrackingDevice = new TrackingDevice(new FogLight(new SportsCar()));
		double price1 = mySportsCarWithFogLightAndTrackingDevice.totalPrice();
		System.out.println("My Sports car with Fog Light and Tracking Device: " + price1);
	}
}


class BaseCar{
	
	double steering() {
		return 200;
	}
	double wheels() {
		return 500;
	}
	double engine() {
		return 1000;
	}
	double body() {
		return 1500;
	}
	
	double totalPrice() {
		return steering() + wheels() + engine() + body();
	}
}

class FamilyCar extends BaseCar{
	
	double totalPrice() {
		
		return super.totalPrice() + 1000;
	}
}

class SportsCar extends BaseCar{
	
	double totalPrice() {
		return super.totalPrice() + 5000;
	}
}

class CarDecorator extends BaseCar{
	
}

class FogLight extends CarDecorator{
	
	private BaseCar baseCar;

	public FogLight(BaseCar baseCar) {
		super();
		this.baseCar = baseCar;
	}
	
	double totalPrice() {
		return baseCar.totalPrice() + 5000;
	}
}

class MusicPlayer extends CarDecorator{
	
	private BaseCar baseCar;

	public MusicPlayer(BaseCar baseCar) {
		super();
		this.baseCar = baseCar;
	}
	
	double totalPrice() {
		return baseCar.totalPrice() + 1000;
	}
}

class TrackingDevice extends CarDecorator{
	
	private BaseCar baseCar;

	public TrackingDevice(BaseCar baseCar) {
		super();
		this.baseCar = baseCar;
	}
	
	double totalPrice() {
		return baseCar.totalPrice() + 2000;
	}
}
