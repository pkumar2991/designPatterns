package com.designpattern.creational.abstractfactory;

public class AbstractFactoryDesignPattern {

	public static void main(String[] args) {
		OSAbstractFactory osAbstractFactory = FactoryProducer.getFactory("Computer");
		OS os = osAbstractFactory.getOS("Linux");
		os.print();
		
		osAbstractFactory = FactoryProducer.getFactory("Mobile");
		OS os1 = osAbstractFactory.getOS("Android");
		os1.print();
		
	}
}

class FactoryProducer{
	
	public static OSAbstractFactory getFactory(String type) {
		
		if(type.equalsIgnoreCase("Computer")) {
			return new ComputerOsFactory();
		}else {
			return new MobileOsFactory();
		}
	}
}

interface OSAbstractFactory{
	OS getOS(String os);
}

class MobileOsFactory implements OSAbstractFactory{
	
	public OS getOS(String os) {
		switch(os) {
			case "IOS" : return new IOS();
			case "Android" : return new Android();
			default : return null;
		}
	}
	
}

class ComputerOsFactory implements OSAbstractFactory{
	
	public OS getOS(String os) {
		switch(os) {
			case "Windows" : return new Windows();
			case "Linux" : return new Linux();
			default : return null;
		}
	}
	
}

interface OS {
	void  print();
}
class IOS implements OS{

	@Override
	public void print() {
		System.out.println(this.getClass().getSimpleName()+" - Object Created.");
	}
	
}
class Android implements OS{

	@Override
	public void print() {
		System.out.println(this.getClass().getSimpleName()+" - Object Created.");
	}
	
}
class Windows implements OS{

	@Override
	public void print() {
		System.out.println(this.getClass().getSimpleName()+" - Object Created.");
	}
	
}
class Linux implements OS{
	
	@Override
	public void print() {
		System.out.println(this.getClass().getSimpleName()+" - Object Created.");
	}
}
class Ubuntu implements OS{
	
	@Override
	public void print() {
		System.out.println(this.getClass().getSimpleName()+" - Object Created.");
	}
}