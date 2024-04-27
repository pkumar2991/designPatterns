#BehavioralDP 
### Definition
>The Observer design pattern is a behavioral design pattern where an object, known as the subject or observable, maintains a list of its dependents, called observers, and notifies them automatically of any state changes, usually by calling one of their methods. This pattern is commonly used to implement distributed event handling systems, where a subject needs to notify multiple observers about changes in its state.

### Problem Statement
Build a system which should notify users when the stock of the asked product is available. For instance, an user is looking for a smart tv and AC to purchase it online from E-commerce website but it's not available in the stock. The website provides a feature to allow user to subscribe a notification channel. It sends notification to the user when the product is available in the stock.

### Naive Approach
It would be very complex solution we try to build the system with this approach. Moreover, it would not be scalable on demand.

Let's build this system using Observer Design Pattern.  

Create Observable interface.
```java
interface Observable {
	void add(Observer observer);
	void remove(Observer observer);
	void notifyObs();
	void setData(int count);
	int getData();
}
```

Create concrete classes of the interface - AC observable
```java
class AcObservable implements Observable {

	List<Observer> obsList = new ArrayList<>();
	int stockCount = 0;

	@Override
	public void add(Observer observer) {
		obsList.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		obsList.remove(observer);
	}

	@Override
	public void notifyObs() {
		obsList.stream()
				.forEach(Observer::update);
	}

	@Override
	public void setData(int count) {
		this.stockCount = count;
		if (count > 0) {
			notifyObs();
		}
	}

	@Override
	public int getData() {
		return stockCount;
	}

}
```

SmartTv Observable

```java
class SmartTvObservable implements Observable {

	List<Observer> obsList = new ArrayList<>();
	int stockCount = 0;

	@Override
	public void add(Observer observer) {
		obsList.add(observer);
	}

	@Override
	public void remove(Observer observer) {
		obsList.remove(observer);
	}

	@Override
	public void notifyObs() {
		obsList.stream()
				.forEach(Observer::update);
	}

	@Override
	public void setData(int count) {
		this.stockCount = count;
		if (count > 0) {
			notifyObs();
		}
	}

	@Override
	public int getData() {
		return stockCount;
	}

}
```

Concrete Observer classes - AC Observer

```java
class AcObserver implements Observer {

	private AcObservable acObs;
	private String emailId;

	public AcObserver(String emailId, AcObservable acObs) {
		this.acObs = acObs;
		this.emailId = emailId;
	}

	@Override
	public void update() {
		sendNotification();
		// Add business logic to execute post sending notification
	}

	@Override
	public void sendNotification() {
		System.out.printf("Email Notification sent to - %s: \nAvailable AC count: %d\n", emailId, acObs.getData());
	}

}
```

SmartTv Observer

```java
class SmartTvObserver implements Observer {

	private SmartTvObservable tvOsb;
	private String emailId;

	public SmartTvObserver(SmartTvObservable tvOsb, String emailId) {
		this.tvOsb = tvOsb;
		this.emailId = emailId;
	}

	@Override
	public void update() {
		sendNotification();
		// Add business logic to execute post sending notification.
	}

	@Override
	public void sendNotification() {
		System.out.printf("Email Notification sent to - %s: \nAvailable Smart TV count: %d\n", emailId, tvOsb.getData());
	}

}
```

Client Class

```java
public class ObserverDesignPattern {
	public static void main(String[] args) {
		AcObservable acObservable = new AcObservable();
		
		AcObserver acObserver1 = new AcObserver("user1@gmail.com", acObservable);
		AcObserver acObserver2 = new AcObserver("user2@gmail.com", acObservable);
		AcObserver acObserver3 = new AcObserver("user3@gmail.com", acObservable);
		
		SmartTvObservable smartTvObservable = new SmartTvObservable();
		
		SmartTvObserver smartTvObserver1 = new SmartTvObserver(smartTvObservable, "user1@gmail.com");
		SmartTvObserver smartTvObserver2 = new SmartTvObserver(smartTvObservable, "user12@gmail.com");
		SmartTvObserver smartTvObserver3 = new SmartTvObserver(smartTvObservable, "user3@gmail.com");
		SmartTvObserver smartTvObserver4 = new SmartTvObserver(smartTvObservable, "user20@gmail.com");
		
		acObservable.add(acObserver1);
		acObservable.add(acObserver2);
		acObservable.add(acObserver3);
		
		smartTvObservable.add(smartTvObserver1);
		smartTvObservable.add(smartTvObserver2);
		smartTvObservable.add(smartTvObserver3);
		smartTvObservable.add(smartTvObserver4);
		
		System.out.println("================ AC Observers ==============");
		acObservable.setData(10);
		System.out.println("============= Smart TV Observers ===========");
		smartTvObservable.setData(20);
		
		
	}
}
```

`Output:`

```java
================ AC Observers ==============
Email Notification sent to - user1@gmail.com: 
Available AC count: 10
Email Notification sent to - user2@gmail.com: 
Available AC count: 10
Email Notification sent to - user3@gmail.com: 
Available AC count: 10
============= Smart TV Observers ===========
Email Notification sent to - user1@gmail.com: 
Available Smart TV count: 20
Email Notification sent to - user12@gmail.com: 
Available Smart TV count: 20
Email Notification sent to - user3@gmail.com: 
Available Smart TV count: 20
Email Notification sent to - user20@gmail.com: 
Available Smart TV count: 20
```

All observers of Smart TV and AC observers are notified as new arrivals of the product.

### Summary
I believe we can use this design pattern wherever we need to perform some action based on some event. Event could be the availability of stock, completion of any business logic like payment or booking ticket and action could be send notification or trigger an event for other downstream application to proceed with workflow.