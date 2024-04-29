package com.designpattern.behavioral;

import java.util.ArrayList;
import java.util.List;

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

interface Observable {

	void add(Observer observer);
	void remove(Observer observer);
	void notifyObs();
	void setData(int count);
	int getData();

}
interface Observer {
	void update();
	void sendNotification();
}

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
