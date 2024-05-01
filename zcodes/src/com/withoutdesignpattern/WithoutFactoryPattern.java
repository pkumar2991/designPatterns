package com.withoutdesignpattern;

public class WithoutFactoryPattern {
	public static void main(String[] args) {
		String os = "IOS";
		getOS(os);
	}
	public static OS getOS(String os) {
		switch(os) {
			case "IOS" : return new IOS();
			case "Android" : return new Android();
			default : return null;
		}
	}
}
interface OS {}
class IOS implements OS{
	
}
class Android implements OS{
	
}
class Windows implements OS{
	
}