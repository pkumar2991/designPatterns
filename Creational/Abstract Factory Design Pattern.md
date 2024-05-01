#creationalDP 

> It's like a factory of factories.

### Problem Statement
While building an application, let's suppose we need to take some actions based on the type of operating systems. It means objects of the operating systems should be created based on some logic. For instance, if the OS is Android, the object of Android class should be used to perform some business logic. There could be different type of operating systems category like Computer OS or Mobile OS.

### Naive Approach
Create classes following all the permutation and combinations. This will lead to class explosion.

Let's fix this, following the Abstract Factory Design Pattern.

### Better Approach

Create OS interface and their implementation classes. There are two different types of OS category defined - Computer OS (Linux, Ubuntu..) or Mobile OS (Android ...)

```java
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
```

Create AbstractFactory Interface and its implementation classes.

```java
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
```

Create FactoryProducer class which returns the specific OSFactory based on the input type.

```java
class FactoryProducer{
	
	public static OSAbstractFactory getFactory(String type) {
		
		if(type.equalsIgnoreCase("Computer")) {
			return new ComputerOsFactory();
		}else {
			return new MobileOsFactory();
		}
	}
}
```

Client class

```java
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
```

### Summary
Use this design pattern where objects can be grouped in categories as mentioned in the aforementioned example.