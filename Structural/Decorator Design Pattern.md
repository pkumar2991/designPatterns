#StructuralDP
### Definition

>   The Decorator design pattern is a structural design pattern that allows behavior to be added to individual objects, either statically or dynamically, without affecting the behavior of other objects from the same class. It's useful when you need to add or alter the functionality of objects during runtime, avoiding the need to create multiple subclasses to accommodate all combinations of features.

### Problem Statement
Jack has to buy a family car having fog light and tracking device, so he needs to get the estimated price of the car. Later, he requested to install Music Player in the car. Now, Jack is expecting the revised price. Unlike Jack, Terry is interested in Sports car with Music Player with Fog Light. 

As a developer, we need to build a software solution to this problem statement where requirement can change anytime and any combination of features could be requested by the user. Moreover, system should be flexible enough to accommodate new features arrive if any.

### Naive Approach

Create a base class
```java
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
```

Create subclasses 

```java
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
```

These subclasses are created possible combination of features of a car.

**What the problem with this code?**  
Above solution will work fine if we have limited number of features but it won't work for more than two features or unknown features. This will lead to Class explosion if we try to create subclasses considering all possible combination of features.

Let's fix this.

### Better Approach
Decorator Design pattern avoids class explosion. Let's see how it helps.

Base Class
```java
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
```

Subclasses

```java
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
```

Decorator Class

```java
class CarDecorator extends BaseCar{

}
```

Subclasses of Decorator class having is a and has a relationship with BaseCar.

```java
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
```

Client class to build a car by adding features dynamically.

```java
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
```

### Summary
Use this design pattern to avoid class explosion.