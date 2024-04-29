package com.withoutdesignpattern;

public class WithoutDecoratorPattern {
	public static void main(String[] args) {
		SuvWithMusic swm = new SuvWithMusic();
		SuvWithPowerfulEngine swpe = new SuvWithPowerfulEngine();
		double priceSuvMusic = swm.steering() + swm.wheels()+swm.body() + swm.music();
		double totalPrice = priceSuvMusic + swpe.engine();
		System.out.println("Price of car with music and powerful engine costs " + totalPrice);
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
}

class SuvWithPowerfulEngine extends BaseCar{
	// Powerful engine 
	double engine() {
		return 1500;
	}
}
class SuvWithMusic extends BaseCar{
	
	double music() {
		return 300;
	}
}
class SuvWithMusicFogLight extends SuvWithMusic{
	
	double fogLight() {
		return 800;
	}
}