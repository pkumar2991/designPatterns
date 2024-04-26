package com.withoutdesignpattern;

public class WithoutStrategyDesignPattern{
	public static void main(String[] args) {
		
	}
}

class Laptop{
	void processInstructions() {
		System.out.println("Default processing mechanism.");
	}
	void authentication() {
		System.out.println("Defalut authentication mechanism.");
	}
}

class SimpleLaptop extends Laptop{
		
}

class ProfessionalLaptop extends Laptop{
	
	void processInstructions() {
		System.out.println("Processing using custom mechanism- PROC1.");
	}
	
}

class GamingLaptop extends Laptop{
	
	void processInstructions() {
		System.out.println("Processing using custom mechanism- PROC1.");
	}
	
}