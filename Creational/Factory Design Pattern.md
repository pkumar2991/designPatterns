#creationalDP
### Definition

>The Factory design pattern is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created. It encapsulates the object creation logic, providing flexibility and decoupling the client code from the concrete classes being instantiated.

### Problem Statement
While building an application, let's suppose we need to take some actions based on the type of operating systems. It means objects of the operating systems should be created based on some logic. For instance, if the OS is Android, the object of Android class should be used to perform some business logic.


### Naive Approach
Interface and implementation classes of operating systems.
```java
interface OS {}
class IOS implements OS{
	
}
class Android implements OS{
	
}
class Windows implements OS{
	
}
```

Client class

```java
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
```

**What's the problem in this code?**  
Client is tightly coupled with the logic of creation of objects. Any future change will add another case in the client class. Moreover, these objects may be needed in many classes to get the required objects of operating system. This means there would be redundant codes which smells bad. 

Let's fix this.

### Better Approach
Factory Design Pattern avoids the redundant code along with makes client loosely coupled with the creation of objects.

OS interface and implementation classes.

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
```

Factory class

```java
class OSFactory{
	
	public OS getOS(String os) {
		switch(os) {
			case "IOS" : return new IOS();
			case "Android" : return new Android();
			default : return null;
		}
	}
}
```

Client Class

```java
public class WithFactoryDesignPattern {
	public static void main(String[] args) {
		OSFactory osfactory = new OSFactory();
		OS os = osfactory.getOS("Android");
		os.print();
	}
}
```

Now, the client class is loosely coupled with the logic of creation of objects. Any change in the object creation logic would not impact the client. Moreover, OSFactory class is only place where we need to change the way of creation of objects if needed in future, therefore only one class would be required to test.

### Summary
When there is need to create type of objects based on some condition, then we should use Factory design pattern.