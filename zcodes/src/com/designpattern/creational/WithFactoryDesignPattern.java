package com.designpattern.creational;

public class WithFactoryDesignPattern {
	public static void main(String[] args) {
		OSFactory osfactory = new OSFactory();
		OS os = osfactory.getOS("Android");
		os.print();
	}
}
class OSFactory{
	
	public OS getOS(String os) {
		switch(os) {
			case "IOS" : return new IOS();
			case "Android" : return new Android();
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