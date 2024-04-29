package com.designpattern.behavioral;

public class StrategyDesignPattern {

	public static void main(String[] args) {
		Laptop laptop = new SimpleLaptop(new NormalProcessingStrategy());
		laptop.processInstructions();
		laptop.authentication();
		
		Laptop profLaptop = new ProfessionalLaptop(new ProfessionalProcessingStrategy());
		profLaptop.processInstructions();
		
		Laptop simpleLaptop = new SimpleLaptop(new SuperProcessingStrategy());
		simpleLaptop.processInstructions();
		
	}
}
class Laptop {

	ProcessingStrategy strategy;

	public Laptop(ProcessingStrategy strategy) {
		this.strategy = strategy;
	}

	void processInstructions() {
		strategy.processInstructions();
	}
	
	void authentication() {
		System.out.println(this.getClass().getSimpleName() + "> Defalut authentication mechanism.");
	}
}

class SimpleLaptop extends Laptop {

	public SimpleLaptop(ProcessingStrategy strategy) {
		super(strategy);
	}
}

class ProfessionalLaptop extends Laptop {

	public ProfessionalLaptop(ProcessingStrategy strategy) {
		super(strategy);
	}

}

class GamingLaptop extends Laptop {

	public GamingLaptop(ProcessingStrategy strategy) {
		super(strategy);
	}
}

interface ProcessingStrategy {
	void processInstructions();
}

class ProfessionalProcessingStrategy implements ProcessingStrategy {

	@Override
	public void processInstructions() {
		System.out.println(this.getClass().getSimpleName() + "> Processing using custom mechanism- PROC1.");
	}

}

class NormalProcessingStrategy implements ProcessingStrategy {

	@Override
	public void processInstructions() {
		System.out.println(this.getClass().getSimpleName() + "> Processing using normal mechanism.");
	}

}

class SuperProcessingStrategy implements ProcessingStrategy {

	@Override
	public void processInstructions() {
		System.out.println(this.getClass().getSimpleName() + "> Processing using super fast mechanism.");
	}

}

